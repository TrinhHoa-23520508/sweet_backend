package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoaiTietKiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loaiTietKiemID;
    private String tenLoaiTietKiem;
    private int maLoaiTietKiem;
    private String moTa;
    private boolean duocPhepRutTruocHan;
    private boolean duocPhepRutMotPhan;
    private boolean coHoatDong;

    public Long getLoaiTietKiemID() {
        return loaiTietKiemID;
    }

    public void setLoaiTietKiemID(Long loaiTietKiemID) {
        this.loaiTietKiemID = loaiTietKiemID;
    }

    public String getTenLoaiTietKiem() {
        return tenLoaiTietKiem;
    }

    public void setTenLoaiTietKiem(String tenLoaiTietKiem) {
        this.tenLoaiTietKiem = tenLoaiTietKiem;
    }

    public int getMaLoaiTietKiem() {
        return maLoaiTietKiem;
    }

    public void setMaLoaiTietKiem(int maLoaiTietKiem) {
        this.maLoaiTietKiem = maLoaiTietKiem;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public boolean isDuocPhepRutTruocHan() {
        return duocPhepRutTruocHan;
    }

    public void setDuocPhepRutTruocHan(boolean duocPhepRutTruocHan) {
        this.duocPhepRutTruocHan = duocPhepRutTruocHan;
    }

    public boolean isDuocPhepRutMotPhan() {
        return duocPhepRutMotPhan;
    }

    public void setDuocPhepRutMotPhan(boolean duocPhepRutMotPhan) {
        this.duocPhepRutMotPhan = duocPhepRutMotPhan;
    }

    public boolean isCoHoatDong() {
        return coHoatDong;
    }

    public void setCoHoatDong(boolean coHoatDong) {
        this.coHoatDong = coHoatDong;
    }
}
