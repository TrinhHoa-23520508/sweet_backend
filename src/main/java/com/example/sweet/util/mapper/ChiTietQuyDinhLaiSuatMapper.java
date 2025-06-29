package com.example.sweet.util.mapper;

import com.example.sweet.database.repository.Loai.LoaiKyHanRepository;
import com.example.sweet.database.repository.Loai.LoaiTietKiemRepository;
import com.example.sweet.database.repository.Loai.QuyDinhLaiSuatRepository;
import com.example.sweet.database.repository.Loai.TanSuatNhanLaiRepository;
import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.domain.request.ChiTietQuyDinhLaiSuatReqDTO;
import com.example.sweet.domain.response.ChiTietQuyDinhLaiSuatResDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChiTietQuyDinhLaiSuatMapper {
    private final NhanVienMapper nhanVienMapper;
    private final TanSuatNhanLaiRepository tanSuatNhanLaiRepository;
    private final LoaiKyHanRepository loaiKyHanRepository;
    private final LoaiTietKiemRepository loaiTietKiemRepository;
    private final QuyDinhLaiSuatRepository quyDinhLaiSuatRepository;

    public ChiTietQuyDinhLaiSuatResDTO toChiTietQuyDinhLaiSuatResponseDTO(
            ChiTietQuyDinhLaiSuat chiTietQuyDinhLaiSuat) {
        if (chiTietQuyDinhLaiSuat == null) {
            return null;
        }

        ChiTietQuyDinhLaiSuatResDTO responseDTO = new ChiTietQuyDinhLaiSuatResDTO();
        responseDTO.setChiTietQuyDinhID(chiTietQuyDinhLaiSuat.getChiTietQuyDinhID());
        responseDTO.setQuyDinhLaiSuatID(chiTietQuyDinhLaiSuat.getQuyDinhLaiSuat().getQuyDinhLaiSuatID());
        responseDTO.setTanSuatNhanLai(chiTietQuyDinhLaiSuat.getTanSuatNhanLai());
        responseDTO.setLoaiTietKiem(chiTietQuyDinhLaiSuat.getLoaiTietKiem());
        responseDTO.setLaiSuat(chiTietQuyDinhLaiSuat.getLaiSuat());
        responseDTO.setLoaiKyHan(chiTietQuyDinhLaiSuat.getLoaiKyHan());

        return responseDTO;
    }

    public ChiTietQuyDinhLaiSuat toChiTietQuyDinhLaiSuat(
            ChiTietQuyDinhLaiSuatReqDTO chiTietQuyDinhLaiSuatReqDTO) {
        if (chiTietQuyDinhLaiSuatReqDTO == null) {
            return null;
        }

        ChiTietQuyDinhLaiSuat chiTietQuyDinhLaiSuat = new ChiTietQuyDinhLaiSuat();
        chiTietQuyDinhLaiSuat.setChiTietQuyDinhID(chiTietQuyDinhLaiSuatReqDTO.getChiTietQuyDinhID());
        chiTietQuyDinhLaiSuat.setTanSuatNhanLai(
                tanSuatNhanLaiRepository.findById(chiTietQuyDinhLaiSuatReqDTO.getTanSuatNhanLaiID())
                        .orElseThrow(() -> new NullPointerException("Không tồn tại tần suất nhận lãi với ID: "
                                + chiTietQuyDinhLaiSuatReqDTO.getTanSuatNhanLaiID())));
        chiTietQuyDinhLaiSuat.setLoaiTietKiem(
                loaiTietKiemRepository.findById(chiTietQuyDinhLaiSuatReqDTO.getLoaiTietKiemID())
                        .orElseThrow(() -> new NullPointerException("Không tồn tại loại tiết kiệm với ID: " +
                                chiTietQuyDinhLaiSuatReqDTO.getLoaiTietKiemID())));
        chiTietQuyDinhLaiSuat.setLaiSuat(chiTietQuyDinhLaiSuatReqDTO.getLaiSuat());
        if (chiTietQuyDinhLaiSuatReqDTO.getLoaiKyHan() == null) {
            throw new NullPointerException("Loại kỳ hạn không được để trống");
        }
        if (chiTietQuyDinhLaiSuatReqDTO.getLoaiKyHan().getLoaiKyHanID() == null) {
            var loaiKyHan = chiTietQuyDinhLaiSuatReqDTO.getLoaiKyHan();

            // Kiểm tra soThang đã tồn tại chưa
            var existingKyHan = loaiKyHanRepository.findBySoThang(loaiKyHan.getSoThang())
                    .orElse(null);

            if (existingKyHan != null) {
                // Nếu đã tồn tại, dùng LoaiKyHan có sẵn
                chiTietQuyDinhLaiSuat.setLoaiKyHan(existingKyHan);
            } else {
                // Nếu chưa tồn tại, tạo mới với ID tự tăng
                var newKyHan = loaiKyHanRepository.save(new LoaiKyHan(
                        null, // ID sẽ tự tăng
                        loaiKyHan.getTenLoaiKyHan(),
                        loaiKyHan.getSoThang()));
                chiTietQuyDinhLaiSuat.setLoaiKyHan(newKyHan);
            }
        } else {
            chiTietQuyDinhLaiSuat.setLoaiKyHan(
                    loaiKyHanRepository.findById(chiTietQuyDinhLaiSuatReqDTO.getLoaiKyHan().getLoaiKyHanID())
                            .orElseThrow(() -> new NullPointerException("Không tồn tại loại kỳ hạn với ID: " +
                                    chiTietQuyDinhLaiSuatReqDTO.getLoaiTietKiemID())));
        }
        // chiTietQuyDinhLaiSuat.setQuyDinhLaiSuat(
        // quyDinhLaiSuatRepository.findById(chiTietQuyDinhLaiSuatReqDTO.getQuyDinhLaiSuatID())
        // .orElseThrow(() -> new NullPointerException("Không tồn tại quy định lãi suất
        // với ID: " +
        // chiTietQuyDinhLaiSuatReqDTO.getQuyDinhLaiSuatID())
        // ));

        return chiTietQuyDinhLaiSuat;
    }
}
