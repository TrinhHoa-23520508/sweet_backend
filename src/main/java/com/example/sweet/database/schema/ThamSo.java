package com.example.sweet.database.schema;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ThamSo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int thamSoID;
    private String tenThamSo;
    private String moTa;
    private int maThamSo;
    private int giaTri;

    public int getThamSoID() {
        return thamSoID;
    }

    public void setThamSoID(int thamSoID) {
        this.thamSoID = thamSoID;
    }

    public String getTenThamSo() {
        return tenThamSo;
    }

    public void setTenThamSo(String tenThamSo) {
        this.tenThamSo = tenThamSo;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaThamSo() {
        return maThamSo;
    }

    public void setMaThamSo(int maThamSo) {
        this.maThamSo = maThamSo;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }
}
