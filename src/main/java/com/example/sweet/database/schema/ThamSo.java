package com.example.sweet.database.schema;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ThamSo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int thamSoID;
    private String tenThamSo;
    private String moTa;
    private int maThamSo;
    private int giaTri;
}
