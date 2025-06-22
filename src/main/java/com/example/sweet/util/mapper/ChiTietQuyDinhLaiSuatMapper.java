package com.example.sweet.util.mapper;

import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.domain.response.ChiTietQuyDinhLaiSuatResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChiTietQuyDinhLaiSuatMapper {
    private final NhanVienMapper nhanVienMapper;

    public ChiTietQuyDinhLaiSuatResponseDTO toChiTietQuyDinhLaiSuatResponseDTO(
            ChiTietQuyDinhLaiSuat chiTietQuyDinhLaiSuat) {
        if (chiTietQuyDinhLaiSuat == null) {
            return null;
        }

        ChiTietQuyDinhLaiSuatResponseDTO responseDTO = new ChiTietQuyDinhLaiSuatResponseDTO();
        responseDTO.setChiTietQuyDinhID(chiTietQuyDinhLaiSuat.getChiTietQuyDinhID());
        responseDTO.setQuyDinhLaiSuatID(chiTietQuyDinhLaiSuat.getChiTietQuyDinhID());
        responseDTO.setTanSuatNhanLai(chiTietQuyDinhLaiSuat.getTanSuatNhanLai());
        responseDTO.setLoaiTietKiem(chiTietQuyDinhLaiSuat.getLoaiTietKiem());
        responseDTO.setLaiSuat(chiTietQuyDinhLaiSuat.getLaiSuat());
        responseDTO.setLoaiKyHan(chiTietQuyDinhLaiSuat.getLoaiKyHan());

        return responseDTO;
    }
}
