package com.example.sweet.domain.request.GiaoDich;

import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TrangThai;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TKTTRequestDTO {
    private Long soTaiKhoan;
    private Long khachHangID;
    private Long trangThaiID;
    private Instant ngayTao;
    private Long soDu;

}
