package com.example.sweet.database.schema.TaiKhoan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VaiTro {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer vaiTroID;
    private String tenVaiTro;
    private String moTa;
}
