package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoaiTaiKhoan {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int loaiTaiKhoanID;
    private String tenLoaiTaiKhoan;
    private int maLoaiTaiKhoan;
    private String moTa;
}
