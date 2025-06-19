package com.example.sweet.services.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_TKTTRepository;
import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_PhieuGuiTienRepository;
import com.example.sweet.database.repository.TaiKhoan.TaiKhoanThanhToanRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.Loai.LoaiTaiKhoanRepository;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_TKTT;
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
    private final GiaoDichMapper giaoDichMapper;

    public List<GiaoDichResponseDTO> findAll() {
        return giaoDichRepo.findAll().stream().map(giaoDichMapper::toGiaoDichResponseDTO).toList();
    }

    public Optional<GiaoDichResponseDTO> findById(Long id) {
        return giaoDichRepo.findById(id).map(giaoDichMapper::toGiaoDichResponseDTO);
    }

    public GiaoDich createGiaoDich(GiaoDichRequestDTO giaoDichRequest) {
        GiaoDich giaoDich = giaoDichMapper.toGiaoDich(giaoDichRequest);

        return createGiaoDich(giaoDich);
    }

    @Transactional
    public GiaoDich createGiaoDich(GiaoDich giaoDich) {
        // Xác định loại tài khoản nguồn
        var loaiTKNguon = giaoDich.getLoaiTaiKhoanNguon();

        // Xác định loại tài khoản đích
        var loaiTKDich = giaoDich.getLoaiTaiKhoanDich();

        // Lấy tài khoản nguồn dựa vào loại
        Object taiKhoanNguon = null;
        if ("Tài khoản thanh toán".equals(loaiTKNguon.getTenLoaiTaiKhoan())) {
            taiKhoanNguon = taiKhoanThanhToanRepo.findById(giaoDich.getTaiKhoanNguon())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản thanh toán nguồn"));
        } else {
            taiKhoanNguon = phieuGuiTienRepo.findById(giaoDich.getTaiKhoanNguon())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu gửi tiền nguồn"));
        }

        // Lấy tài khoản đích dựa vào loại
        Object taiKhoanDich = null;
        if ("Tài khoản thanh toán".equals(loaiTKDich.getTenLoaiTaiKhoan())) {
            taiKhoanDich = taiKhoanThanhToanRepo.findById(giaoDich.getTaiKhoanDich())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản thanh toán đích"));
        } else {
            taiKhoanDich = phieuGuiTienRepo.findById(giaoDich.getTaiKhoanDich())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu gửi tiền đích"));
        }

        // Kiểm tra số dư nếu tài khoản nguồn là TKTT
        if (taiKhoanNguon instanceof TaiKhoanThanhToan) {
            TaiKhoanThanhToan tktt = (TaiKhoanThanhToan) taiKhoanNguon;
            if (tktt.getSoDu() < giaoDich.getSoTienGiaoDich()) {
                throw new RuntimeException("Số dư không đủ");
            }
            // Trừ tiền tài khoản nguồn
            tktt.setSoDu(tktt.getSoDu() - giaoDich.getSoTienGiaoDich());
            taiKhoanThanhToanRepo.save(tktt);
        }

        // Cộng tiền nếu tài khoản đích là TKTT
        if (taiKhoanDich instanceof TaiKhoanThanhToan) {
            TaiKhoanThanhToan tktt = (TaiKhoanThanhToan) taiKhoanDich;
            tktt.setSoDu(tktt.getSoDu() + giaoDich.getSoTienGiaoDich());
            taiKhoanThanhToanRepo.save(tktt);
        }

        // Lưu giao dịch
        var savedGiaoDich = giaoDichRepo.save(giaoDich);

        // Lưu lịch sử giao dịch theo loại tài khoản
        if (taiKhoanNguon instanceof TaiKhoanThanhToan) {
            lichSuTKTTRepo.save(new LichSuGiaoDich_TKTT(
                    null,
                    (TaiKhoanThanhToan) taiKhoanNguon,
                    savedGiaoDich,
                    ((TaiKhoanThanhToan) taiKhoanNguon).getSoDu()));
        }

        if (taiKhoanDich instanceof TaiKhoanThanhToan) {
            lichSuTKTTRepo.save(new LichSuGiaoDich_TKTT(
                    null,
                    (TaiKhoanThanhToan) taiKhoanDich,
                    savedGiaoDich,
                    ((TaiKhoanThanhToan) taiKhoanDich).getSoDu()));
        }

        return savedGiaoDich;
    }

    // this should either delete or create, no update i think?
    public void cancelGiaoDich(Long id) {
        var giaoDich = giaoDichRepo.findById(id).orElseThrow();

        cancelGiaoDich(giaoDich);
    }

    // Please fill out all field before using this
    public void cancelGiaoDich(GiaoDich giaoDich) {
        var taiKhoanNguon = taiKhoanThanhToanRepo.findById(giaoDich.getTaiKhoanNguon()).orElseThrow();
        var taiKhoanDich = taiKhoanThanhToanRepo.findById(giaoDich.getTaiKhoanDich()).orElseThrow();

        taiKhoanNguon.setSoDu(taiKhoanNguon.getSoDu() + giaoDich.getSoTienGiaoDich());
        // Negate that bitch
        taiKhoanDich.setSoDu(taiKhoanDich.getSoDu() - giaoDich.getSoTienGiaoDich());

        lichSuTKTTRepo.deleteByTaiKhoanAndGiaoDich(taiKhoanNguon, giaoDich);
        lichSuTKTTRepo.deleteByTaiKhoanAndGiaoDich(taiKhoanDich, giaoDich);
        giaoDichRepo.delete(giaoDich);

        taiKhoanThanhToanRepo.save(taiKhoanNguon);
        taiKhoanThanhToanRepo.save(taiKhoanDich);

    }
}
