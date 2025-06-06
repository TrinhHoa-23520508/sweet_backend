package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TanSuatNhanLai {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int tanSoNhanLaiID;
    private String tenTanSoNhanLai;
    private int maTanSoNhanLai;
    private String moTa;
    private boolean coHoatDong;
}
