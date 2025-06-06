package com.example.sweet.database.schema.Loai;

import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import jakarta.persistence.*;

//@Entity
public class TanSuatNhanLaiHinhThucDaoHan {
    @EmbeddedId
    private TanSuatNhanLaiHinhThucDaoHan id;

    @ManyToOne
    @MapsId("tanSuatNhanLaiID")
    @JoinColumn(name = "tan_suat_nhan_lai_id")
    private TanSuatNhanLai tanSuatNhanLai;

    @ManyToOne
    @MapsId("hinhThucDaoHanID")
    @JoinColumn(name = "hinh_thuc_dao_han_id")
    private HinhThucDaoHan hinhThucDaoHan;

    public TanSuatNhanLai getTanSuatNhanLai() {
        return tanSuatNhanLai;
    }

    public void setTanSuatNhanLai(TanSuatNhanLai tanSuatNhanLai) {
        this.tanSuatNhanLai = tanSuatNhanLai;
    }

    public TanSuatNhanLaiHinhThucDaoHan getId() {
        return id;
    }

    public void setId(TanSuatNhanLaiHinhThucDaoHan id) {
        this.id = id;
    }

    public HinhThucDaoHan getHinhThucDaoHan() {
        return hinhThucDaoHan;
    }

    public void setHinhThucDaoHan(HinhThucDaoHan hinhThucDaoHan) {
        this.hinhThucDaoHan = hinhThucDaoHan;
    }
}
