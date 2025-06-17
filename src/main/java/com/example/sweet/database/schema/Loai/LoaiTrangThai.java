package com.example.sweet.database.schema.Loai;

import com.example.sweet.database.schema.TrangThai;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoaiTrangThai {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long loaiTrangThaiID;

    @NotBlank(message = "maLoaiTrangThai không được để trống")
    private String maLoaiTrangThai;

    private String tenLoaiTrangThai;
    private String moTa;

    @Column(nullable = false)
    private Boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "loaiTrangThai", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TrangThai> TrangThais;



}
