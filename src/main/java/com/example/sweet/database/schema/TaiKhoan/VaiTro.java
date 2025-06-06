package com.example.sweet.database.schema.TaiKhoan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VaiTro {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer vaiTroID;
    private String tenVaiTro;
    private String moTa;

    public Integer getVaiTroID() {
        return vaiTroID;
    }

    public void setVaiTroID(Integer vaiTroID) {
        this.vaiTroID = vaiTroID;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
