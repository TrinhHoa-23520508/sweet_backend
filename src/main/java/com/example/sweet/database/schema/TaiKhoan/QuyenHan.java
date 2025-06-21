package com.example.sweet.database.schema.TaiKhoan;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuyenHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên permission không được để trống")
    private String name;

    @NotBlank(message = "Api path không được để trống")
    private String apiPath;

    @NotBlank(message = "Tên method không được để trống")
    private String method;

    @NotBlank(message = "Tên module không được để trống")
    private String module;


    @ManyToMany(mappedBy = "quyenHans", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<VaiTro> vaiTros;


}
