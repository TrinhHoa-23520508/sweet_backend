package com.example.sweet.domain.request;

import com.example.sweet.database.schema.Loai.LoaiKyHan;
import com.example.sweet.database.schema.Loai.LoaiTietKiem;
import com.example.sweet.database.schema.Loai.TanSuatNhanLai;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChiTietQuyDinhLaiSuatReqDTO {
    private Long chiTietQuyDinhID;
    private Long quyDinhLaiSuatID;
    private Long loaiTietKiemID;
    private Long tanSuatNhanLaiID;
    private LoaiKyHan loaiKyHan;
    private float laiSuat;
}
