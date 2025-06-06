package com.example.sweet.database.schema.TaiKhoan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DiaChi {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer diaChiID;
    private Integer soNha;
    private String tenDuong;
    private String phuongXa;
    private String quanHuyen;
    private String tinhTP;
}
