package com.example.sweet.database.schema.GiaoDich;

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
public class KenhGiaoDich {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Long kenhGiaoDichID;
    private int maKenhGiaoDich;
    private String tenKenhGiaoDich;
    private String moTa;
}
