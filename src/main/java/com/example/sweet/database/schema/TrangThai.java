package com.example.sweet.database.schema;

import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private Long trangThaiID;

    @NotBlank(message = "Mã trạng thái không được để trống")
    private String maTrangThai;
    private String tenTrangThai;

    @Column(nullable = false)
    private Boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "loai_trang_thai")
    private LoaiTrangThai loaiTrangThai;




}
