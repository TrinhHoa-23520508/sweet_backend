package com.example.sweet.database.repository.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhieuTraLaiDTO {
    private Long phieuTraLaiID;
    private Long phieuGuiTTienID;
    private Long giaoDichID;
    private LocalDateTime ngayTraLai;
}
