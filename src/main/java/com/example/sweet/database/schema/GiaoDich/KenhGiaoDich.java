package com.example.sweet.database.schema.GiaoDich;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KenhGiaoDich {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int kenhGiaoDichID;
    private String tenKenhGiaoDich;
    private int maKenhGiaoDich;
    private String moTa;

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaKenhGiaoDich() {
        return maKenhGiaoDich;
    }

    public void setMaKenhGiaoDich(int maKenhGiaoDich) {
        this.maKenhGiaoDich = maKenhGiaoDich;
    }

    public String getTenKenhGiaoDich() {
        return tenKenhGiaoDich;
    }

    public void setTenKenhGiaoDich(String tenKenhGiaoDich) {
        this.tenKenhGiaoDich = tenKenhGiaoDich;
    }

    public int getKenhGiaoDichID() {
        return kenhGiaoDichID;
    }

    public void setKenhGiaoDichID(int kenhGiaoDichID) {
        this.kenhGiaoDichID = kenhGiaoDichID;
    }
}
