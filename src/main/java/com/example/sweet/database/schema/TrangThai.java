package com.example.sweet.database.schema;

import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrangThai {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private int trangThaiID;
    private int maTrangThai;
    private String tenTrangThai;

    @ManyToOne
    @JoinColumn(name = "loai_trang_thai")
    private LoaiTrangThai loaiTrangThai;
}
