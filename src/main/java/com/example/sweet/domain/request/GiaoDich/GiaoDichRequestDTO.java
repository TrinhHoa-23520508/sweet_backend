package com.example.sweet.domain.request.GiaoDich;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDichRequestDTO {
    private Long giaoDichID;
    private Long taiKhoanNguon;
    private Long loaiTaiKhoanNguonID;
    private Long taiKhoanDich;
    private Long loaiTaiKhoanDichID;
    private Long loaiGiaoDichID;
    private Long kenhGiaoDichID;
    private Long nhanVienGiaoDichID;
    private Long soTienGiaoDich;
    private String noiDung;
    private Instant thoiGianGiaoDich;
}
