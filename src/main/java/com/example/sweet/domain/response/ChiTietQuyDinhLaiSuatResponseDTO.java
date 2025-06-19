package com.example.sweet.domain.response;

import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChiTietQuyDinhLaiSuatResponseDTO {
    private Long chiTietQuyDinhID;
    private Long quyDinhLaiSuatID;
    private LoaiTietKiem loaiTietKiem;
    private TanSuatNhanLai tanSuatNhanLai;
    private LoaiKyHan loaiKyHan;
    private float laiSuat;
}
