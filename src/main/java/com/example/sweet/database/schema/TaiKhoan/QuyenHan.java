package com.example.sweet.database.schema.TaiKhoan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QuyenHan {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int quyenHanID;
    private String tenQuyenHan;
    private String moTa;
}
