package com.example.sweet.database.schema.TaiKhoan;

import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class NhanVien {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int nhanVienID;
    private String hoTen;
    private LocalDateTime ngaySinh;
    private String cccd;
    private String email;

    @ManyToOne
    @JoinColumn(name = "dia_chi_thuong_tru", nullable = false)
    private DiaChi diaChiThuongTruID;
    @ManyToOne
    @JoinColumn(name = "dia_chi_lien_lac", nullable = false)
    private DiaChi diaChiLienLacID;
    private LocalDateTime ngayTuyenDung;
    private String tenDangNhap;
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "vai_tro", nullable = false)
    private VaiTro vaiTroID;
//    @OneToMany(mappedBy = "quyenHan")
//    private List<VaiTroQuyenHan> danhSachVaiTro;
    @ManyToOne
    @JoinColumn(name = "trang_thai_tai_khoan", nullable = false)
    private TrangThai trangThaiTaiKhoan;

}
