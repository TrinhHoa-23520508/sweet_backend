package com.example.sweet.database.schema.GiaoDich;

import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhieuGuiTien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phieuGuiTienID;
    @ManyToOne
    @JoinColumn(name = "khach_hang", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_giao_dich", nullable = false)
    private NhanVien giaoDichVien;

    @ManyToOne
    @JoinColumn(name = "chi_tiet_quy_dinh_lai_suat", nullable = false)
    private ChiTietQuyDinhLaiSuat chiTietQuyDinhLaiSuat;

    @ManyToOne
    @JoinColumn(name = "hinh_thuc_dao_han", nullable = false)
    private HinhThucDaoHan hinhThucDaoHan;

    private Instant ngayGuiTien;
    private Long soTienGuiBanDau;
    private String tenGoiNho;
    @ManyToOne
    @JoinColumn(name = "trang_thai", nullable = false)
    private TrangThai trangThai;

    private Long soDuHienTai;
    private Long tongTienLaiDuKien;
    private Long tienLaiNhanDinhKy;
    private Long tienLaiDaNhanNhungChuaQuyetToan;
    private Long tongLaiQuyetToan;
    private Instant ngayDaoHan;
}
