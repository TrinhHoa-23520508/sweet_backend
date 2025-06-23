package com.example.sweet.domain.response.GiaoDich;

import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.domain.response.KhachHangNoVaiTroResponseDTO;
import com.example.sweet.domain.response.KhachHangResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TKTTResponseDTO {
    private Long soTaiKhoan;

    private KhachHangNoVaiTroResponseDTO khachHang;

    private TrangThai trangThai;

    private Instant ngayTao;

    private Long soDu;

}
