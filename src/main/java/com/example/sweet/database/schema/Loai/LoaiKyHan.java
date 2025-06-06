package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoaiKyHan {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int loaiKyHanID;
    private String tenLoaiKyHan;
    private int soThang;
}
