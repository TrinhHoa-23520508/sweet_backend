package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class LoaiTrangThai {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int loaiTrangThaiID;
    private int maLoaiTrangThai;
    private String tenLoaiTrangThai;
    private String moTa;
}
