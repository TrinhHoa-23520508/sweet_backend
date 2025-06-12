package com.example.sweet.database.schema.GiaoDich;

import com.example.sweet.database.schema.Loai.HinhThucDaoHan;
import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;
import com.example.sweet.database.schema.TrangThai;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    @JoinColumn(name = "giao_dich", nullable = false)
    private GiaoDich giaoDich;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_giao_dich", nullable = false)
    private NhanVien giaoDichVien;

    @ManyToOne
    @JoinColumn(name = "loai_tiet_kiem", nullable = false)
    private LoaiTietKiem loaiTietKiem;
    @ManyToOne
    @JoinColumn(name = "tan_suat_nhan_lai", nullable = false)
    private TanSuatNhanLai tanSuatNhanLai;
    @ManyToOne
    @JoinColumn(name = "loai_ky_han", nullable = false)
    private LoaiKyHan loaiKyHan;

    @ManyToOne
    @JoinColumn(name = "hinh_thuc_dao_han", nullable = false)
    private HinhThucDaoHan hinhThucDaoHan;

    private LocalDate ngayGuiTien;
    private Long soTienGuiBanDau;
    private Float laiSuatCamKet;
    private String tenGoiNho;
    @ManyToOne
    @JoinColumn(name = "trang_thai", nullable = false)
    private TrangThai trangThai;
}
