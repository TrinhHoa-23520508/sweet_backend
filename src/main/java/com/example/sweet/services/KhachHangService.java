package com.example.sweet.services;


import com.example.sweet.database.repository.Loai.LoaiTrangThaiRepository;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.VaiTroRepository;
import com.example.sweet.database.repository.ThamSoRepository;
import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.domain.request.KhachHangRequestDTO;
import com.example.sweet.domain.response.KhachHangResponseDTO;
import com.example.sweet.domain.response.ResLoginDTO;
import com.example.sweet.services.GiaoDich.TaiKhoanThanhToanService;
import com.example.sweet.util.constant.StatusEnum;
import com.example.sweet.util.constant.SystemParameterEnum;
import com.example.sweet.util.constant.TypeStatusEnum;
import com.example.sweet.util.constant.VaiTroEnum;
import com.example.sweet.util.error.DuplicateResourceException;
import com.example.sweet.util.error.NotFoundException;
import com.example.sweet.util.mapper.KhachHangMapper;
import com.example.sweet.util.mapper.UserLoginMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class KhachHangService {

    private final KhachHangRepository khachHangRepository;
    private final PasswordEncoder passwordEncoder;
    private final KhachHangMapper khachHangMapper;
    private final ThamSoRepository thamSoRepository;
    private final TrangThaiRepository trangThaiRepository;
    private final LoaiTrangThaiRepository loaiTrangThaiRepository;
    private final VaiTroRepository vaiTroRepository;
    private final UserLoginMapper userLoginMapper;
    private final DiaChiService diaChiService;
    private final TaiKhoanThanhToanService taiKhoanThanhToanService;

    public KhachHangService(KhachHangRepository khachHangRepository,
                            PasswordEncoder passwordEncoder,
                            KhachHangMapper khachHangMapper,
                            ThamSoRepository thamSoRepository,
                            TrangThaiRepository trangThaiRepository,
                            LoaiTrangThaiRepository loaiTrangThaiRepository,
                            VaiTroRepository vaiTroRepository,
                            UserLoginMapper userLoginMapper,
                            DiaChiService diaChiService,
                            TaiKhoanThanhToanService taiKhoanThanhToanService) {
        this.khachHangRepository = khachHangRepository;
        this.passwordEncoder = passwordEncoder;
        this.khachHangMapper = khachHangMapper;
        this.thamSoRepository = thamSoRepository;
        this.trangThaiRepository = trangThaiRepository;
        this.loaiTrangThaiRepository = loaiTrangThaiRepository;
        this.vaiTroRepository = vaiTroRepository;
        this.userLoginMapper = userLoginMapper;
        this.diaChiService = diaChiService;
        this.taiKhoanThanhToanService = taiKhoanThanhToanService;
    }

    public boolean checkDuplicate(KhachHang khachHang) {
        if (khachHang.getKhachHangID() == null) {
            return this.khachHangRepository.existsByEmailOrCccd(khachHang.getEmail(), khachHang.getCccd());
        } else {
            return this.khachHangRepository.existsByCccdAndKhachHangIDNot(khachHang.getCccd(), khachHang.getKhachHangID())
                    || this.khachHangRepository.existsByEmailAndKhachHangIDNot(khachHang.getEmail(), khachHang.getKhachHangID());
        }
    }

    public void validateAge(int tuoi) {
        if (tuoi < 0) {
            throw new IllegalArgumentException("Tuổi không thể nhỏ hơn 0");
        }
        ThamSo thamSoTuoiKH = this.thamSoRepository.findByMaThamSo(SystemParameterEnum.MIN_AGE_CUSTOMER.toString()).orElseThrow(() -> new IllegalArgumentException("Tham số MIN_AGE_CUSTOMER không tồn tại"));
        int minAge = Integer.parseInt(thamSoTuoiKH.getGiaTri());
        if (tuoi < minAge) {
            throw new IllegalArgumentException("Khách hàng phải từ " + minAge + " tuổi trở lên");
        }
    }

    public KhachHangResponseDTO createKhachHang(KhachHangRequestDTO requestDTO) {
        KhachHang newKhachHang = this.khachHangMapper.toKhachHangEntity(requestDTO);
        newKhachHang.setKhachHangID(null);
        if (checkDuplicate(newKhachHang)) {
            throw new DuplicateResourceException("Khách hàng đã tồn tại với email hoặc CCCD: " + newKhachHang.getEmail() + " hoặc " + newKhachHang.getCccd());
        }

        if (newKhachHang.getNgaySinh() != null) {
            newKhachHang.setTuoi(Period.between(newKhachHang.getNgaySinh(), LocalDate.now()).getYears());
            validateAge(newKhachHang.getTuoi());

        }
        //create dia chi thuong tru and lien lac
        if (newKhachHang.getDiaChiThuongTru() != null) {
            DiaChi diaChiThuongTru = this.diaChiService.createDiaChi(newKhachHang.getDiaChiThuongTru());
            newKhachHang.setDiaChiThuongTru(diaChiThuongTru);
        }
        if (newKhachHang.getDiaChiLienLac() != null) {
            DiaChi diaChiLienLac = this.diaChiService.createDiaChi(newKhachHang.getDiaChiLienLac());
            newKhachHang.setDiaChiLienLac(diaChiLienLac);
        }
        if (newKhachHang.getNgayDangKy() == null) {
            newKhachHang.setNgayDangKy(LocalDate.now());
        }
        newKhachHang.setMatKhau(passwordEncoder.encode(newKhachHang.getMatKhau()));

        if (newKhachHang.getTrangThaiTaiKhoan() == null) {
            LoaiTrangThai loaiTrangThai_TK = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(TypeStatusEnum.login_account.toString())
                    .orElseThrow(() -> new IllegalArgumentException("Loại trạng thái tài khoản không tồn tại"));
            TrangThai active_default_TK = this.trangThaiRepository.findByMaTrangThaiAndLoaiTrangThai(StatusEnum.locked.toString(), loaiTrangThai_TK)
                    .orElseThrow(() -> new IllegalArgumentException("Trạng thái HOAT_DONG không tồn tại cho loại trạng thái tài khoản"));
            newKhachHang.setTrangThaiTaiKhoan(active_default_TK);
        }

        if (newKhachHang.getTrangThaiKhachHang() == null) {
            LoaiTrangThai loaiTrangThai_KH = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(TypeStatusEnum.customer.toString())
                    .orElseThrow(() -> new IllegalArgumentException("Loại trạng thái khách hàng không tồn tại"));

            TrangThai active_default_KH = this.trangThaiRepository.findByMaTrangThaiAndLoaiTrangThai(StatusEnum.locked.toString(), loaiTrangThai_KH)
                    .orElseThrow(() -> new IllegalArgumentException("Trạng thái HOAT_DONG không tồn tại cho loại trạng thái khách hàng"));

            newKhachHang.setTrangThaiKhachHang(active_default_KH);
        }

        if (newKhachHang.getVaiTro() == null) {
            VaiTro vaiTro_default = this.vaiTroRepository.findByName(VaiTroEnum.QUYEN_TIET_KIEM.toString()).orElseThrow(() -> new IllegalArgumentException("Vai trò KHONG_QUYEN không tồn tại"));
            newKhachHang.setVaiTro(vaiTro_default);
        }
        //save khach hang
        newKhachHang = this.khachHangRepository.save(newKhachHang);
        //create tai khoan thanh toan
        TaiKhoanThanhToan tktt = new TaiKhoanThanhToan();
        tktt.setKhachHang(newKhachHang);
        LoaiTrangThai loaiTrangThai = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(TypeStatusEnum.payment_account.toString())
                .orElseThrow(
                        () -> new NotFoundException("Loai trang thai khong ton tai")
                );
        TrangThai activeAccount = this.trangThaiRepository.findByMaTrangThaiAndLoaiTrangThai(StatusEnum.active.toString(), loaiTrangThai)
                .orElseThrow(
                        () -> new NotFoundException("Trang thai khong ton tai")
                );
        tktt.setTrangThai(activeAccount);
        tktt.setNgayTao(Instant.now());
        tktt.setSoDu(0L);
        this.taiKhoanThanhToanService.save(tktt);
        return this.khachHangMapper.toKhachHangResponseDTO(newKhachHang);

    }

    public KhachHangResponseDTO getKhachHangById(Long id) {
        KhachHang khachHang = this.khachHangRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Khách hàng không tồn tại với ID: " + id)
        );
        return this.khachHangMapper.toKhachHangResponseDTO(khachHang);
    }

    public List<KhachHangResponseDTO> getAllKhachHang(Specification<KhachHang> specification) {
        List<KhachHang> khachHangs = this.khachHangRepository.findAll(specification);
        return khachHangs.stream()
                .map(this.khachHangMapper::toKhachHangResponseDTO)
                .toList();
    }

    public KhachHangResponseDTO updateKhachHang(Long id, KhachHangRequestDTO requestDTO) {
        KhachHang updateKhachHang = this.khachHangMapper.toKhachHangEntity(requestDTO);
        updateKhachHang.setKhachHangID(id);
        if (checkDuplicate(updateKhachHang)) {
            throw new DuplicateResourceException("Khách hàng đã tồn tại với email hoặc CCCD: " + updateKhachHang.getEmail() + " hoặc " + updateKhachHang.getCccd());
        }
        KhachHang existingKhachHang = this.khachHangRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Khách hàng không tồn tại với ID: " + id)
        );
        existingKhachHang.setHoTen(updateKhachHang.getHoTen());
        existingKhachHang.setEmail(updateKhachHang.getEmail());
        existingKhachHang.setCccd(updateKhachHang.getCccd());
        existingKhachHang.setSoDienThoai(updateKhachHang.getSoDienThoai());
        existingKhachHang.setNgaySinh(updateKhachHang.getNgaySinh());
        if (updateKhachHang.getMatKhau() != null) {
            existingKhachHang.setMatKhau(passwordEncoder.encode(updateKhachHang.getMatKhau()));
        }
        if (existingKhachHang.getNgaySinh() != null) {
            existingKhachHang.setTuoi(Period.between(existingKhachHang.getNgaySinh(), LocalDate.now()).getYears());
            validateAge(existingKhachHang.getTuoi());
        }
        if (updateKhachHang.getDiaChiLienLac() != null) {
            if (updateKhachHang.getDiaChiLienLac().getDiaChiID() == null) {
                DiaChi newDiaChiLienLac = this.diaChiService.createDiaChi(updateKhachHang.getDiaChiLienLac());
                existingKhachHang.setDiaChiLienLac(newDiaChiLienLac);
            }
        }
        if (updateKhachHang.getDiaChiThuongTru() != null) {
            if (updateKhachHang.getDiaChiThuongTru().getDiaChiID() == null) {
                DiaChi newDiaChiThuongTru = this.diaChiService.createDiaChi(updateKhachHang.getDiaChiThuongTru());
                existingKhachHang.setDiaChiThuongTru(newDiaChiThuongTru);
            }
        }
        if (updateKhachHang.getTrangThaiTaiKhoan() != null) {
            existingKhachHang.setTrangThaiTaiKhoan(updateKhachHang.getTrangThaiTaiKhoan());
        }
        if (updateKhachHang.getTrangThaiKhachHang() != null) {
            existingKhachHang.setTrangThaiKhachHang(updateKhachHang.getTrangThaiKhachHang());
        }
        if (updateKhachHang.getVaiTro() != null) {
            existingKhachHang.setVaiTro(updateKhachHang.getVaiTro());
        }

        return this.khachHangMapper.toKhachHangResponseDTO(this.khachHangRepository.save(existingKhachHang));


    }

    public void deactivateKhachHang(Long id) {

        KhachHang existingKhachHang = this.khachHangRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Khách hàng không tồn tại với ID: " + id)
        );
        LoaiTrangThai loaiTrangThai_TK = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(TypeStatusEnum.login_account.toString())
                .orElseThrow(() -> new IllegalArgumentException("Loại trạng thái không tồn tại"));
        TrangThai inactiveTrangThai = this.trangThaiRepository.findByMaTrangThaiAndLoaiTrangThai(StatusEnum.locked.toString(), loaiTrangThai_TK)
                .orElseThrow(() -> new IllegalArgumentException("Trạng thái không tồn tại cho loại trạng thái tài khoản"));
        existingKhachHang.setTrangThaiTaiKhoan(inactiveTrangThai);
        this.khachHangRepository.save(existingKhachHang);

    }

    public void activateKhachHang(Long id) {
        KhachHang existingKhachHang = this.khachHangRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Khách hàng không tồn tại với ID: " + id)
        );
        LoaiTrangThai loaiTrangThai_TK = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(TypeStatusEnum.login_account.toString())
                .orElseThrow(() -> new IllegalArgumentException("Loại trạng thái không tồn tại"));
        TrangThai activeTrangThai = this.trangThaiRepository.findByMaTrangThaiAndLoaiTrangThai(StatusEnum.active.toString(), loaiTrangThai_TK)
                .orElseThrow(() -> new IllegalArgumentException("Trạng thái không tồn tại cho loại trạng thái tài khoản"));
        existingKhachHang.setTrangThaiTaiKhoan(activeTrangThai);
        this.khachHangRepository.save(existingKhachHang);
    }

    public ResLoginDTO.UserGetAccount getUserAccountByEmail(String email) {
        KhachHang khachHang = this.khachHangRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Khách hàng không tồn tại với email: " + email));
        ResLoginDTO.UserGetAccount userGetAccount = this.userLoginMapper.KhachHangResToUserGetAccount(
                this.khachHangMapper.toKhachHangResponseDTO(khachHang)
        );
        return userGetAccount;
    }

}
