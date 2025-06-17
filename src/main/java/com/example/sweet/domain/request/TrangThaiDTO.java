package com.example.sweet.domain.request;

import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrangThaiDTO {

    private Long trangThaiID;

    @NotNull(message = "loaiTrangThaiID không được để trống")
    private Long loaiTrangThaiID;

    @NotBlank(message = "Mã trạng thái không được để trống")
    private String maTrangThai;
    private String tenTrangThai;

    private Boolean deleted = Boolean.FALSE;


}
