package com.example.sweet.domain.response.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.GiaoDich;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LSGD_PGTDTO {
    Long lichSuPhieuGuiTienId;
    Long phieuGuiTienId;
    GiaoDichResponseDTO giaoDich;
    Double soTienGocGiaoDich;
    Double soDuHienTai_SauGD;
}
