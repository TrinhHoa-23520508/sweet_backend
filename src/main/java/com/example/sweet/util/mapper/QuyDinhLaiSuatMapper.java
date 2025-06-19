package com.example.sweet.util.mapper;

import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.domain.response.QuyDinhLaiSuatResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuyDinhLaiSuatMapper {
    private final NhanVienMapper nhanVienMapper;
    private final ChiTietQuyDinhLaiSuatMapper chiTietQuyDinhLaiSuatMapper;

    public QuyDinhLaiSuatResponseDTO toQuyDinhLaiSuatResponseDTO(QuyDinhLaiSuat quyDinhLaiSuat) {
        if (quyDinhLaiSuat == null) {
            return null;
        }

        QuyDinhLaiSuatResponseDTO responseDTO = new QuyDinhLaiSuatResponseDTO();
        responseDTO.setQuyDinhLaiSuatID(quyDinhLaiSuat.getQuyDinhLaiSuatID());
        responseDTO.setNgayBatDau(quyDinhLaiSuat.getNgayBatDau());
        responseDTO.setNgayKetThuc(quyDinhLaiSuat.getNgayKetThuc());
        responseDTO.setMoTa(quyDinhLaiSuat.getMoTa());
        responseDTO.setNguoiLapQuyDinh(nhanVienMapper.toNhanVienResponseDTO(quyDinhLaiSuat.getNguoiLapQuyDinh()));
        responseDTO.setLaiSuatKhongKyHan(quyDinhLaiSuat.getLaiSuatKhongKyHan());
        responseDTO.setSoTienGuiToiThieu(quyDinhLaiSuat.getSoTienGuiToiThieu());
        responseDTO.setChiTietQuyDinhLaiSuats(
                quyDinhLaiSuat
                .getChiTietQuyDinhLaiSuats()
                .stream()
                .map(chiTietQuyDinhLaiSuatMapper::toChiTietQuyDinhLaiSuatResponseDTO).toList());

        return responseDTO;
    }
}
