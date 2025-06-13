package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TanSuatNhanLai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tanSoNhanLaiID;
    private String tenTanSoNhanLai;
    private int maTanSoNhanLai;
    private String moTa;
    private boolean coHoatDong;

    public Long getTanSoNhanLaiID() {
        return tanSoNhanLaiID;
    }

    public void setTanSoNhanLaiID(Long tanSoNhanLaiID) {
        this.tanSoNhanLaiID = tanSoNhanLaiID;
    }

    public String getTenTanSoNhanLai() {
        return tenTanSoNhanLai;
    }

    public void setTenTanSoNhanLai(String tenTanSoNhanLai) {
        this.tenTanSoNhanLai = tenTanSoNhanLai;
    }

    public int getMaTanSoNhanLai() {
        return maTanSoNhanLai;
    }

    public void setMaTanSoNhanLai(int maTanSoNhanLai) {
        this.maTanSoNhanLai = maTanSoNhanLai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public boolean isCoHoatDong() {
        return coHoatDong;
    }

    public void setCoHoatDong(boolean coHoatDong) {
        this.coHoatDong = coHoatDong;
    }
}
