package com.example.sweet.database.schema;

import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import jakarta.persistence.*;

@Entity
public class TrangThai {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int trangThaiID;
    private int maTrangThai;
    private String tenTrangThai;
    @ManyToOne
    @JoinColumn(name = "loai_trang_thai")
    private LoaiTrangThai loaiTrangThai;
}
