package com.example.sweet.database.schema.GiaoDich;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
@Table(name = "LICHSUGIAODICH_PHIEUGUITIEN")
public class LichSuGiaoDich_PhieuGuiTien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lichSuPhieuGuiTienID;

    @ManyToOne
    @JoinColumn(name = "phieu_gui_tien_id", nullable = false)
    private PhieuGuiTien phieuGuiTien;

    @ManyToOne
    @JoinColumn(name = "giao_dich_id", nullable = false)
    private GiaoDich giaoDich;

    @Column(name = "so_tien_goc_giao_dich")
    private double soTienGocGiaoDich;

    @Column(name = "so_du_hien_tai_sau_gd")
    private double soDuHienTai_SauGD;

    @Column(name = "tien_lai_trong_gd")
    private double tienLai_TrongGD;

    @Column(name = "lai_quyet_toan_trong_gd")
    private double laiQuyetToan_TrongGD;

    @Column(name = "tien_lai_da_nhan_nhung_chua_quyet_toan_sau_gd")
    private double tienLaiDaNhanNhungChuaQuyetToan_SauGD;

    @Column(name = "tong_lai_quyet_toan_sau_gd")
    private double tongLaiQuyetToan_SauGD;
}
