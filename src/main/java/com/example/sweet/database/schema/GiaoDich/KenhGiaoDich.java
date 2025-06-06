package com.example.sweet.database.schema.GiaoDich;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KenhGiaoDich {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int kenhGiaoDichID;
    private String tenKenhGiaoDich;
    private int maKenhGiaoDich;
    private String moTa;
}
