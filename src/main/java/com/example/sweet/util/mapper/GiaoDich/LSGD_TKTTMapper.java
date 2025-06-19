package com.example.sweet.util.mapper.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_TKTT;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.domain.response.GiaoDich.LSGD_TKTTResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Component
public class LSGD_TKTTMapper {
    private final TKTTMapper tkttMapper;
    private final GiaoDichMapper giaoDichMapper;
    public LSGD_TKTTResponseDTO toLSGD_TKTTResponseDTO(LichSuGiaoDich_TKTT lsgd) {
        LSGD_TKTTResponseDTO responseDTO = new LSGD_TKTTResponseDTO();
        responseDTO.setLichSuGiaoDichID(lsgd.getLichSuGiaoDichID());
        responseDTO.setTaiKhoan(tkttMapper.toTKTTResponseDTO(lsgd.getTaiKhoan()));
        responseDTO.setGiaoDich(giaoDichMapper.toGiaoDichResponseDTO(lsgd.getGiaoDich()));
        responseDTO.setSoDuSauGD(lsgd.getSoDuSauGD());
        return responseDTO;
    }
}
