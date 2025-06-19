package com.example.sweet.domain.response.GiaoDich;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LSGD_TKTTResponseDTO {

    private Long lichSuGiaoDichID;

    private TKTTResponseDTO taiKhoan;
    private GiaoDichResponseDTO giaoDich;

    private Long soDuSauGD;
}
