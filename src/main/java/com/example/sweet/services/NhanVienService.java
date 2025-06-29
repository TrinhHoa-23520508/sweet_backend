package com.example.sweet.services;

import com.example.sweet.database.repository.Loai.LoaiTrangThaiRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.TaiKhoan.VaiTroRepository;
import com.example.sweet.database.repository.ThamSoRepository;
import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.TaiKhoan.*;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.domain.request.NhanVienRequestDTO;
import com.example.sweet.domain.response.NhanVienResponseDTO;
import com.example.sweet.domain.response.ResLoginDTO;
import com.example.sweet.util.constant.StatusEnum;
import com.example.sweet.util.constant.SystemParameterEnum;
import com.example.sweet.util.constant.TypeStatusEnum;
import com.example.sweet.util.constant.VaiTroEnum;
import com.example.sweet.util.error.DuplicateResourceException;
import com.example.sweet.util.error.NotFoundException;
import com.example.sweet.util.mapper.NhanVienMapper;
import com.example.sweet.util.mapper.UserLoginMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class NhanVienService {

    private final NhanVienRepository nhanVienRepository;
    private final NhanVienMapper nhanVienMapper;
    private final ThamSoRepository thamSoRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoaiTrangThaiRepository loaiTrangThaiRepository;
    private final TrangThaiRepository trangThaiRepository;
    private final VaiTroRepository vaiTroRepository;
    private final UserLoginMapper userLoginMapper;
    private final DiaChiService diaChiService;

    public NhanVienService(NhanVienRepository nhanVienRepository,
                           NhanVienMapper nhanVienMapper,
                           ThamSoRepository thamSoRepository,
                           PasswordEncoder passwordEncoder,
                           LoaiTrangThaiRepository loaiTrangThaiRepository,
                           TrangThaiRepository trangThaiRepository,
                           VaiTroRepository vaiTroRepository,
                           UserLoginMapper userLoginMapper,
                           DiaChiService diaChiService) {
        this.nhanVienRepository = nhanVienRepository;
        this.nhanVienMapper = nhanVienMapper;
        this.thamSoRepository = thamSoRepository;
        this.passwordEncoder = passwordEncoder;
        this.loaiTrangThaiRepository = loaiTrangThaiRepository;
        this.trangThaiRepository = trangThaiRepository;
        this.vaiTroRepository = vaiTroRepository;
        this.userLoginMapper = userLoginMapper;
        this.diaChiService = diaChiService;
    }

    public boolean checkDuplicate(NhanVien nhanVien) {
        if (nhanVien.getNhanVienID() == null) {
            return this.nhanVienRepository.existsByEmailOrCccd(nhanVien.getEmail(), nhanVien.getCccd());
        } else {
            return this.nhanVienRepository.existsByCccdAndNhanVienIDNot(nhanVien.getCccd(), nhanVien.getNhanVienID())
                    || this.nhanVienRepository.existsByEmailAndNhanVienIDNot(nhanVien.getEmail(), nhanVien.getNhanVienID());
        }
    }

    public void validateAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Tuổi phải lớn hơn 0");
        }
        ThamSo thamSoTuoiNV = this.thamSoRepository.findByMaThamSo(SystemParameterEnum.MIN_AGE_EMPLOYEE.toString()).orElseThrow(
                () -> new NotFoundException("Tham số tuổi nhân viên không được tìm thấy")
        );
        int minAge = Integer.parseInt(thamSoTuoiNV.getGiaTri());
        if (age < minAge) {
            throw new IllegalArgumentException("Tuổi nhân viên phải lớn hơn hoặc bằng " + minAge);
        }
    }

    public NhanVienResponseDTO createNhanVien(NhanVienRequestDTO nhanVienRequestDTO) {
        NhanVien newNhanVien = this.nhanVienMapper.toNhanVienEntity(nhanVienRequestDTO);
        newNhanVien.setNhanVienID(null);
        if (checkDuplicate(newNhanVien)) {
            throw new DuplicateResourceException("Nhân viên với email hoặc CCCD đã tồn tại");
        }

        if (newNhanVien.getNgaySinh() != null) {
            newNhanVien.setTuoi(Period.between(newNhanVien.getNgaySinh(), LocalDate.now()).getYears());
            validateAge(newNhanVien.getTuoi());
        }
        if (newNhanVien.getDiaChiLienLac() != null) {
            DiaChi diaChiLienLac = this.diaChiService.createDiaChi(newNhanVien.getDiaChiLienLac());
            newNhanVien.setDiaChiLienLac(diaChiLienLac);
        }
        if (newNhanVien.getDiaChiThuongTru() != null) {
            DiaChi diaChiThuongTru = this.diaChiService.createDiaChi(newNhanVien.getDiaChiThuongTru());
            newNhanVien.setDiaChiThuongTru(diaChiThuongTru);
        }

        if (newNhanVien.getNgayTuyenDung() == null) {
            newNhanVien.setNgayTuyenDung(LocalDate.now());
        }
        newNhanVien.setMatKhau(passwordEncoder.encode(newNhanVien.getMatKhau()));

        if (newNhanVien.getTrangThaiTaiKhoan() == null) {
            LoaiTrangThai loaiTrangThai_TK = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(TypeStatusEnum.login_account.toString())
                    .orElseThrow(() -> new IllegalArgumentException("Loại trạng thái tài khoản không tồn tại"));
            TrangThai active_default_TK = this.trangThaiRepository.findByMaTrangThaiAndLoaiTrangThai(StatusEnum.locked.toString(), loaiTrangThai_TK)
                    .orElseThrow(() -> new IllegalArgumentException("Trạng thái HOAT_DONG không tồn tại cho loại trạng thái tài khoản"));
            newNhanVien.setTrangThaiTaiKhoan(active_default_TK);
        }

        if (newNhanVien.getVaiTro() == null) {
            VaiTro vaiTro_default = this.vaiTroRepository.findByName(VaiTroEnum.KHONG_QUYEN_NHAN_VIEN.toString()).orElseThrow(() -> new IllegalArgumentException("Vai trò không tồn tại"));
            newNhanVien.setVaiTro(vaiTro_default);
        }

        return this.nhanVienMapper.toNhanVienResponseDTO(
                this.nhanVienRepository.save(newNhanVien)
        );
    }


//    public NhanVien createNhanVienForInitializer(NhanVienRequestDTO nhanVienRequestDTO) {
//        NhanVien newNhanVien = this.nhanVienMapper.toNhanVienEntity(nhanVienRequestDTO);
//        newNhanVien.setNhanVienID(null);
//        if (checkDuplicate(newNhanVien)) {
//            throw new DuplicateResourceException("Nhân viên với email hoặc CCCD đã tồn tại");
//        }
//
//        if (newNhanVien.getNgaySinh() != null) {
//            newNhanVien.setTuoi(Period.between(newNhanVien.getNgaySinh(), LocalDate.now()).getYears());
//            validateAge(newNhanVien.getTuoi());
//        }
//        if (newNhanVien.getDiaChiLienLac() != null) {
//            DiaChi diaChiLienLac = this.diaChiService.createDiaChi(newNhanVien.getDiaChiLienLac());
//            newNhanVien.setDiaChiLienLac(diaChiLienLac);
//        }
//        if (newNhanVien.getDiaChiThuongTru() != null) {
//            DiaChi diaChiThuongTru = this.diaChiService.createDiaChi(newNhanVien.getDiaChiThuongTru());
//            newNhanVien.setDiaChiThuongTru(diaChiThuongTru);
//        }
//
//        if (newNhanVien.getNgayTuyenDung() == null) {
//            newNhanVien.setNgayTuyenDung(LocalDate.now());
//        }
//        newNhanVien.setMatKhau(passwordEncoder.encode(newNhanVien.getMatKhau()));
//
//        if (newNhanVien.getTrangThaiTaiKhoan() == null) {
//            LoaiTrangThai loaiTrangThai_TK = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(TypeStatusEnum.login_account.toString())
//                    .orElseThrow(() -> new IllegalArgumentException("Loại trạng thái tài khoản không tồn tại"));
//            TrangThai active_default_TK = this.trangThaiRepository.findByMaTrangThaiAndLoaiTrangThai(StatusEnum.active.toString(), loaiTrangThai_TK)
//                    .orElseThrow(() -> new IllegalArgumentException("Trạng thái HOAT_DONG không tồn tại cho loại trạng thái tài khoản"));
//            newNhanVien.setTrangThaiTaiKhoan(active_default_TK);
//        }
//
//        if (newNhanVien.getVaiTro() == null) {
//            VaiTro vaiTro_default = this.vaiTroRepository.findByName(VaiTroEnum.KHONG_QUYEN_NHAN_VIEN.toString()).orElseThrow(() -> new IllegalArgumentException("Vai trò không tồn tại"));
//            newNhanVien.setVaiTro(vaiTro_default);
//        }
//
//        return this.nhanVienRepository.save(newNhanVien);
//    }

    public NhanVienResponseDTO getNhanVienById(Long id) {
        NhanVien nhanVien = this.nhanVienRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nhân viên không tồn tại với ID: " + id));
        return this.nhanVienMapper.toNhanVienResponseDTO(nhanVien);
    }

    public List<NhanVienResponseDTO> getAllNhanVien(Specification<NhanVien> specification) {
        List<NhanVien> nhanVienList = this.nhanVienRepository.findAll(specification);

        return nhanVienList.stream()
                .map(this.nhanVienMapper::toNhanVienResponseDTO)
                .toList();
    }

    public NhanVienResponseDTO updateNhanVien(Long id, NhanVienRequestDTO nhanVienRequestDTO) {
        NhanVien updateNhanVien = this.nhanVienMapper.toNhanVienEntity(nhanVienRequestDTO);
        updateNhanVien.setNhanVienID(id);
        if (checkDuplicate(updateNhanVien)) {
            throw new DuplicateResourceException("Nhân viên đã tồn tại với email hoặc CCCD: " + updateNhanVien.getEmail() + " hoặc " + updateNhanVien.getCccd());
        }
        NhanVien existingNhanVien = this.nhanVienRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Nhân viên không tồn tại với ID: " + id)
        );
        existingNhanVien.setHoTen(updateNhanVien.getHoTen());
        existingNhanVien.setEmail(updateNhanVien.getEmail());
        existingNhanVien.setCccd(updateNhanVien.getCccd());
        existingNhanVien.setSoDienThoai(updateNhanVien.getSoDienThoai());
        existingNhanVien.setNgaySinh(updateNhanVien.getNgaySinh());
        if (updateNhanVien.getMatKhau() != null) {
            existingNhanVien.setMatKhau(passwordEncoder.encode(updateNhanVien.getMatKhau()));
        }
        if (existingNhanVien.getNgaySinh() != null) {
            existingNhanVien.setTuoi(Period.between(existingNhanVien.getNgaySinh(), LocalDate.now()).getYears());
            validateAge(existingNhanVien.getTuoi());
        }
        if (updateNhanVien.getDiaChiLienLac() != null) {
            if (updateNhanVien.getDiaChiLienLac().getDiaChiID() == null) {
                DiaChi newDiaChiLienLac = this.diaChiService.createDiaChi(updateNhanVien.getDiaChiLienLac());
                existingNhanVien.setDiaChiLienLac(newDiaChiLienLac);
            }
        }
        if (updateNhanVien.getDiaChiThuongTru() != null) {
            if (updateNhanVien.getDiaChiThuongTru().getDiaChiID() == null) {
                DiaChi newDiaChiThuongTru = this.diaChiService.createDiaChi(updateNhanVien.getDiaChiThuongTru());
                existingNhanVien.setDiaChiThuongTru(newDiaChiThuongTru);
            }
        }
        if (updateNhanVien.getTrangThaiTaiKhoan() != null) {
            existingNhanVien.setTrangThaiTaiKhoan(updateNhanVien.getTrangThaiTaiKhoan());
        }
        if (updateNhanVien.getVaiTro() != null) {
            existingNhanVien.setVaiTro(updateNhanVien.getVaiTro());
        }

        return this.nhanVienMapper.toNhanVienResponseDTO(this.nhanVienRepository.save(existingNhanVien));
    }

    public void deactivateNhanVien(Long id) {
        NhanVien existingNhanVien = this.nhanVienRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Nhân viên không tồn tại với ID: " + id)
        );
        LoaiTrangThai loaiTrangThai_TK = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(TypeStatusEnum.login_account.toString())
                .orElseThrow(() -> new IllegalArgumentException("Loại trạng thái không tồn tại"));
        TrangThai inactiveTrangThai = this.trangThaiRepository.findByMaTrangThaiAndLoaiTrangThai(StatusEnum.locked.toString(), loaiTrangThai_TK)
                .orElseThrow(() -> new IllegalArgumentException("Trạng thái không tồn tại cho loại trạng thái tài khoản"));
        existingNhanVien.setTrangThaiTaiKhoan(inactiveTrangThai);
        this.nhanVienRepository.save(existingNhanVien);
    }

    public void activateNhanVien(Long id) {
        NhanVien existingNhanVien = this.nhanVienRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Nhân viên không tồn tại với ID: " + id)
        );
        LoaiTrangThai loaiTrangThai_TK = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(TypeStatusEnum.login_account.toString())
                .orElseThrow(() -> new IllegalArgumentException("Loại trạng thái không tồn tại"));
        TrangThai activeTrangThai = this.trangThaiRepository.findByMaTrangThaiAndLoaiTrangThai(StatusEnum.active.toString(), loaiTrangThai_TK)
                .orElseThrow(() -> new IllegalArgumentException("Trạng thái không tồn tại cho loại trạng thái tài khoản"));
        existingNhanVien.setTrangThaiTaiKhoan(activeTrangThai);
        this.nhanVienRepository.save(existingNhanVien);

    }

    public ResLoginDTO.UserGetAccount getUserAccountByEmail(String email) {
        NhanVien nhanVien = this.nhanVienRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Nhân viên không tồn tại với email: " + email));
        ResLoginDTO.UserGetAccount userGetAccount = this.userLoginMapper.NhanVienResToUserGetAccount(
                this.nhanVienMapper.toNhanVienResponseDTO(nhanVien)
        );
        return userGetAccount;

    }
}
