package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoaiGiaoDich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loaiGiaoDichID;
    private int maLoaiGiaoDich;
    private String tenLoaiGiaoDich;
    private String moTa;

}
