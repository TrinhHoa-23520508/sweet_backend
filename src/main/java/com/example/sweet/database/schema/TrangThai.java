package com.example.sweet.database.schema;

import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import jakarta.persistence.*;

@Entity
public class TrangThai {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int trangThaiID;
    private int maTrangThai;
    private String tenTrangThai;
    @ManyToOne
    @JoinColumn(name = "loai_trang_thai")
    private LoaiTrangThai loaiTrangThai;

    public int getTrangThaiID() {
        return trangThaiID;
    }

    public void setTrangThaiID(int trangThaiID) {
        this.trangThaiID = trangThaiID;
    }

    public int getMaTrangThai() {
        return maTrangThai;
    }

    public void setMaTrangThai(int maTrangThai) {
        this.maTrangThai = maTrangThai;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public LoaiTrangThai getLoaiTrangThai() {
        return loaiTrangThai;
    }

    public void setLoaiTrangThai(LoaiTrangThai loaiTrangThai) {
        this.loaiTrangThai = loaiTrangThai;
    }
}
