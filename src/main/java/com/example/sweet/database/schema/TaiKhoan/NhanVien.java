package com.example.sweet.database.schema.TaiKhoan;

import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int nhanVienID;
    private String hoTen;
    private LocalDate ngaySinh;
    private String cccd;
    private String email;

    @ManyToOne
    @JoinColumn(name = "dia_chi_thuong_tru", nullable = false)
    private DiaChi diaChiThuongTruID;
    @ManyToOne
    @JoinColumn(name = "dia_chi_lien_lac", nullable = false)
    private DiaChi diaChiLienLacID;
    private LocalDate ngayTuyenDung;
    private String tenDangNhap;
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "vai_tro", nullable = false)
    private VaiTro vaiTro;

    @ManyToOne
    @JoinColumn(name = "trang_thai_tai_khoan", nullable = false)
    private TrangThai trangThaiTaiKhoan;


}
