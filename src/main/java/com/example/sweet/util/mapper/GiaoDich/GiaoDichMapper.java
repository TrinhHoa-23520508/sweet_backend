package com.example.sweet.util.mapper.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.KenhGiaoDichRepository;
import com.example.sweet.database.repository.Loai.LoaiGiaoDichRepository;
import com.example.sweet.database.repository.Loai.LoaiTaiKhoanRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.domain.request.GiaoDich.GiaoDichRequestDTO;
import com.example.sweet.domain.request.NhanVienRequestDTO;
import com.example.sweet.domain.response.GiaoDich.GiaoDichResponseDTO;
import com.example.sweet.util.mapper.NhanVienMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Component
@AllArgsConstructor
public class GiaoDichMapper {
    private final GiaoDichRepository giaoDichRepository;
    private final LoaiTaiKhoanRepository loaiTaiKhoanRepository;
    private final LoaiGiaoDichRepository loaiGiaoDichRepository;
    private final KenhGiaoDichRepository kenhGiaoDichRepository;
    private final NhanVienRepository nhanVienRepository;
    private final NhanVienMapper nhanVienMapper;

    public GiaoDich toGiaoDich(GiaoDichRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        GiaoDich giaoDich = new GiaoDich();
        giaoDich.setGiaoDichID(requestDTO.getGiaoDichID());
        giaoDich.setTaiKhoanNguon(requestDTO.getTaiKhoanNguon());
        giaoDich.setTaiKhoanDich(requestDTO.getTaiKhoanDich());
        giaoDich.setLoaiTaiKhoanNguon(
                loaiTaiKhoanRepository.findById(requestDTO.getLoaiTaiKhoanNguonID())
                    .orElseThrow(() -> new NullPointerException("Không tìm thấy loại tài khoản nguồn")));
        giaoDich.setLoaiTaiKhoanDich(
                loaiTaiKhoanRepository.findById(requestDTO.getLoaiTaiKhoanDichID())
                    .orElseThrow(() -> new NullPointerException("Không tìm thấy loại tài khoản nguồn")));
        giaoDich.setLoaiGiaoDich(
                loaiGiaoDichRepository.findById(requestDTO.getLoaiGiaoDichID())
                    .orElseThrow(() -> new NullPointerException("Không tìm thấy loại giao dịch")));
        giaoDich.setKenhGiaoDich(
            kenhGiaoDichRepository.findById(requestDTO.getKenhGiaoDichID())
                .orElseThrow(() -> new NullPointerException("Không tìm thấy kênh giao dịch"))
        );
        giaoDich.setNhanVienGiaoDich(nhanVienRepository.findById(requestDTO.getNhanVienGiaoDichID()).orElse(null));
        giaoDich.setSoTienGiaoDich(requestDTO.getSoTienGiaoDich());
        giaoDich.setNoiDung(requestDTO.getNoiDung());
        giaoDich.setThoiGianGiaoDich(requestDTO.getThoiGianGiaoDich());
        return giaoDich;
    }

    public GiaoDichResponseDTO toGiaoDichResponseDTO(GiaoDich giaoDich) {
        if (giaoDich == null) {
            return null;
        }
        GiaoDichResponseDTO responseDTO = new GiaoDichResponseDTO();
        responseDTO.setGiaoDichID(giaoDich.getGiaoDichID());
        responseDTO.setTaiKhoanNguon(giaoDich.getTaiKhoanNguon());
        responseDTO.setLoaiTaiKhoanNguon(giaoDich.getLoaiTaiKhoanNguon());
        responseDTO.setTaiKhoanDich(giaoDich.getTaiKhoanDich());
        responseDTO.setLoaiTaiKhoanDich(giaoDich.getLoaiTaiKhoanDich());
        responseDTO.setLoaiGiaoDich(giaoDich.getLoaiGiaoDich());
        responseDTO.setKenhGiaoDich(giaoDich.getKenhGiaoDich());
        responseDTO.setNhanVienGiaoDich(nhanVienMapper.toNhanVienResponseDTO(giaoDich.getNhanVienGiaoDich()));
        responseDTO.setSoTienGiaoDich(giaoDich.getSoTienGiaoDich());
        responseDTO.setNoiDung(giaoDich.getNoiDung());
        responseDTO.setThoiGianGiaoDich(giaoDich.getThoiGianGiaoDich());
        return responseDTO;
    }
}
