package com.example.sweet.database.schema.TaiKhoan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DiaChi {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer diaChiID;
    private Integer soNha;
    private String tenDuong;
    private String phuongXa;
    private String quanHuyen;
    private String tinhTP;

    public Integer getDiaChiID() {
        return diaChiID;
    }

    public void setDiaChiID(Integer diaChiID) {
        this.diaChiID = diaChiID;
    }

    public Integer getSoNha() {
        return soNha;
    }

    public void setSoNha(Integer soNha) {
        this.soNha = soNha;
    }

    public String getTenDuong() {
        return tenDuong;
    }

    public void setTenDuong(String tenDuong) {
        this.tenDuong = tenDuong;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getTinhTP() {
        return tinhTP;
    }

    public void setTinhTP(String tinhTP) {
        this.tinhTP = tinhTP;
    }
}
