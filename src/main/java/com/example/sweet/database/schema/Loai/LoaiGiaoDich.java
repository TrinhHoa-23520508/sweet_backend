package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoaiGiaoDich {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int loaiGiaoDichID;
    private String tenLoaiGiaoDich;
    private int maLoaiGiaoDich;
    private String moTa;
}
