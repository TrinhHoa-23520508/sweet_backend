package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HinhThucDaoHan {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int hinhThucDaoHangID;
    private String tenHinhThucDaoHang;
    private int maHinhThucDaoHang;
    private String moTa;
}
