package com.example.sweet.services.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_TKTTRepository;
import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_PhieuGuiTienRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.TaiKhoan.TaiKhoanThanhToanRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.Loai.LoaiTaiKhoanRepository;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_TKTT;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.domain.request.GiaoDich.GiaoDichRequestDTO;
import com.example.sweet.domain.response.GiaoDich.GiaoDichResponseDTO;
import com.example.sweet.util.mapper.GiaoDich.GiaoDichMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GiaoDichService {
    private final GiaoDichRepository giaoDichRepo;
    private final LichSuGiaoDich_TKTTRepository lichSuTKTTRepo;
    private final LichSuGiaoDich_PhieuGuiTienRepository lichSuPGTRepo;
    private final TaiKhoanThanhToanRepository taiKhoanThanhToanRepo;
    private final PhieuGuiTienRepository phieuGuiTienRepo;
    private final LoaiTaiKhoanRepository loaiTaiKhoanRepo;
    private final NhanVienRepository nhanVienRepository;
    private final GiaoDichMapper giaoDichMapper;

    public List<GiaoDichResponseDTO> findAll() {
        return giaoDichRepo.findAll().stream().map(giaoDichMapper::toGiaoDichResponseDTO).toList();
    }

    public Optional<GiaoDichResponseDTO> findById(Long id) {
        return giaoDichRepo.findById(id).map(giaoDichMapper::toGiaoDichResponseDTO);
    }

    public List<GiaoDichResponseDTO> findByTaiKhoan(Long id) {
        return giaoDichRepo.findByTaiKhoan(id).stream().map(giaoDichMapper::toGiaoDichResponseDTO).toList();
    }

    public List<GiaoDichResponseDTO> findByNhanVienGiaoDich(Long id) {
        NhanVien nhanVienGiaoDich = nhanVienRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Không tìm thấy nhân viên giao dịch với ID: " + id));
        return giaoDichRepo.findByNhanVienGiaoDich(nhanVienGiaoDich).stream().map(giaoDichMapper::toGiaoDichResponseDTO)
                .toList();
    }

    public GiaoDich createGiaoDich(GiaoDichRequestDTO giaoDichRequest) {
        GiaoDich giaoDich = giaoDichMapper.toGiaoDich(giaoDichRequest);

        return createGiaoDich(giaoDich);
    }

    @Transactional
    public GiaoDich createGiaoDich(GiaoDich giaoDich) {
        if (giaoDich.getSoTienGiaoDich() <= 0) {
            throw new RuntimeException("Số tiền giao dịch phải lớn hơn 0");
        }
        // Xác định loại tài khoản nguồn
        var loaiTKNguon = giaoDich.getLoaiTaiKhoanNguon();

        // Xác định loại tài khoản đích
        var loaiTKDich = giaoDich.getLoaiTaiKhoanDich();

        // Lấy tài khoản nguồn dựa vào loại
        Object taiKhoanNguon = null;
        switch (loaiTKNguon.getTenLoaiTaiKhoan()) {
            case "Tài khoản thanh toán":
                taiKhoanNguon = taiKhoanThanhToanRepo.findById(giaoDich.getTaiKhoanNguon())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản thanh toán nguồn"));
                break;
            case "Phiếu gửi tiền":
                taiKhoanNguon = phieuGuiTienRepo.findById(giaoDich.getTaiKhoanNguon())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu gửi tiền nguồn"));
                break;

            case "Tiền mặt tại quầy":
                break; // Không cần xử lý gì thêm cho loại này
            default:
                throw new RuntimeException("Loại tài khoản nguồn không hợp lệ");
        }

        // Lấy tài khoản đích dựa vào loại
        Object taiKhoanDich = null;
        switch (loaiTKDich.getTenLoaiTaiKhoan()) {
            case "Tài khoản thanh toán":
                taiKhoanDich = taiKhoanThanhToanRepo.findById(giaoDich.getTaiKhoanDich())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản thanh toán đích"));
                break;
            case "Phiếu gửi tiền":
                taiKhoanDich = phieuGuiTienRepo.findById(giaoDich.getTaiKhoanDich())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu gửi tiền đích"));
                break;
            case "Tiền mặt tại quầy":
            case "Ngân hàng":
                break; // Không cần xử lý gì thêm cho các loại này
            default:
                throw new RuntimeException("Loại tài khoản đích không hợp lệ");
        }

        // Kiểm tra số dư nếu tài khoản nguồn là TKTT
        // TKTT -> TKTT và ngược lại ko duoc

        // null -> TKTT
        // null -> PGT
        // TKTT -> PGT
        // TKTT -> null
        // PGT -> null
        // PGT -> PGT
        if (taiKhoanNguon instanceof TaiKhoanThanhToan && taiKhoanDich instanceof TaiKhoanThanhToan) {
            throw new RuntimeException("Không thể giao dịch giữa hai tài khoản thanh toán");
        }
        // if (taiKhoanNguon instanceof PhieuGuiTien && taiKhoanDich instanceof
        // TaiKhoanThanhToan) {
        // throw new RuntimeException("Không thể giao dịch giữa Phiếu gửi tiền và Tài
        // khoản thanh toán");
        // }

        // Giao dịch
        if (taiKhoanNguon instanceof TaiKhoanThanhToan tkttNguon) {
            if (tkttNguon.getSoDu() < giaoDich.getSoTienGiaoDich()) {
                throw new RuntimeException("Số dư không đủ");
            }
            // Trừ tiền tài khoản nguồn
            tkttNguon.setSoDu(tkttNguon.getSoDu() - giaoDich.getSoTienGiaoDich());
            taiKhoanThanhToanRepo.save(tkttNguon);
        } else if (taiKhoanNguon instanceof PhieuGuiTien pgtNguon) {
            if (pgtNguon.getSoDuHienTai() < giaoDich.getSoTienGiaoDich()) {
                throw new RuntimeException("Số dư không đủ");
            }
            // Trừ tiền tài khoản nguồn
            phieuGuiTienRepo.save(pgtNguon);
        }

        if (taiKhoanDich instanceof TaiKhoanThanhToan tkttDich) {
            tkttDich.setSoDu(tkttDich.getSoDu() + giaoDich.getSoTienGiaoDich());
            taiKhoanThanhToanRepo.save(tkttDich);
        } else if (taiKhoanDich instanceof PhieuGuiTien pgtDich) {
            phieuGuiTienRepo.save(pgtDich);
        }

        var savedGiaoDich = giaoDichRepo.save(giaoDich);

        // Lich sử giao dịch
        if (taiKhoanNguon instanceof TaiKhoanThanhToan tkttNguon) {
            lichSuTKTTRepo.save(new LichSuGiaoDich_TKTT(
                    null,
                    tkttNguon,
                    savedGiaoDich,
                    tkttNguon.getSoDu()));
        }
        // else if (taiKhoanNguon instanceof PhieuGuiTien pgtNguon) {
        // lichSuPGTRepo.save(new LichSuGiaoDich_PhieuGuiTien(
        // null,
        // pgtNguon,
        // savedGiaoDich,
        // savedGiaoDich.getSoTienGiaoDich(),
        // pgtNguon.getSoDuHienTai(),
        // pgtNguon.getTienLaiNhanDinhKy(),
        // 0.0f,
        // pgtNguon.getTienLaiDaNhanNhungChuaQuyetToan(),
        // pgtNguon.getTongLaiQuyetToan()));
        // }

        if (taiKhoanDich instanceof TaiKhoanThanhToan tkttDich) {
            lichSuTKTTRepo.save(new LichSuGiaoDich_TKTT(
                    null,
                    tkttDich,
                    savedGiaoDich,
                    tkttDich.getSoDu()));
        } else if (taiKhoanDich instanceof PhieuGuiTien pgtDich) {
            lichSuPGTRepo.save(new LichSuGiaoDich_PhieuGuiTien(
                    null,
                    pgtDich,
                    savedGiaoDich,
                    savedGiaoDich.getSoTienGiaoDich(),
                    pgtDich.getSoDuHienTai(),
                    pgtDich.getTienLaiNhanDinhKy(),
                    0.0f,
                    pgtDich.getTienLaiDaNhanNhungChuaQuyetToan(),
                    pgtDich.getTongLaiQuyetToan()));
        }

        return savedGiaoDich;
    }

    // // this should either delete or create, no update i think?
    // public void cancelGiaoDich(Long id) {
    // var giaoDich = giaoDichRepo.findById(id).orElseThrow();
    //
    // cancelGiaoDich(giaoDich);
    // }
    //
    // // Please fill out all field before using this
    // public void cancelGiaoDich(GiaoDich giaoDich) {
    // var taiKhoanNguon =
    // taiKhoanThanhToanRepo.findById(giaoDich.getTaiKhoanNguon()).orElseThrow();
    // var taiKhoanDich =
    // taiKhoanThanhToanRepo.findById(giaoDich.getTaiKhoanDich()).orElseThrow();
    //
    // taiKhoanNguon.setSoDu(taiKhoanNguon.getSoDu() +
    // giaoDich.getSoTienGiaoDich());
    // // Negate that bitch
    // taiKhoanDich.setSoDu(taiKhoanDich.getSoDu() - giaoDich.getSoTienGiaoDich());
    //
    // lichSuTKTTRepo.deleteByTaiKhoanAndGiaoDich(taiKhoanNguon, giaoDich);
    // lichSuTKTTRepo.deleteByTaiKhoanAndGiaoDich(taiKhoanDich, giaoDich);
    // giaoDichRepo.delete(giaoDich);
    //
    // taiKhoanThanhToanRepo.save(taiKhoanNguon);
    // taiKhoanThanhToanRepo.save(taiKhoanDich);
    //
    // }
}
