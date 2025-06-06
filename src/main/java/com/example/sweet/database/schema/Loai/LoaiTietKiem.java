package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoaiTietKiem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int loaiTietKiemID;
    private String tenLoaiTietKiem;
    private int maLoaiTietKiem;
    private String moTa;
    private boolean duocPhepRutTruocHan;
    private boolean duocPhepRutMotPhan;
    private boolean coHoatDong;
}
