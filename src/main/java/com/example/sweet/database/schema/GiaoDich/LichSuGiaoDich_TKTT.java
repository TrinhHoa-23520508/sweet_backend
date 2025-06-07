package com.example.sweet.database.schema.GiaoDich;

import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import jakarta.persistence.*;

@Entity
public class LichSuGiaoDich_TKTT {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int lichSuGiaoDichID;

    @ManyToOne
    @JoinColumn(name = "tai_khoan", nullable = false)
    private TaiKhoanThanhToan taiKhoan;

    @ManyToOne
    @JoinColumn(name = "giao_dich", nullable = false)
    private GiaoDich giaoDich;

    private int SoDuSauGD;

    public int getLichSuGiaoDichID() {
        return lichSuGiaoDichID;
    }

    public void setLichSuGiaoDichID(int lichSuGiaoDichID) {
        this.lichSuGiaoDichID = lichSuGiaoDichID;
    }

    public TaiKhoanThanhToan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoanThanhToan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public GiaoDich getGiaoDich() {
        return giaoDich;
    }

    public void setGiaoDich(GiaoDich giaoDich) {
        this.giaoDich = giaoDich;
    }

    public int getSoDuSauGD() {
        return SoDuSauGD;
    }

    public void setSoDuSauGD(int soDuSauGD) {
        SoDuSauGD = soDuSauGD;
    }
}
