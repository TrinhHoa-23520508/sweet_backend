package com.example.sweet.database.schema.TaiKhoan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@AllArgsConstructor
@NoArgsConstructor
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên role không được để trống")
    private String name;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    private Boolean active;



    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"vaiTros"})
    @JoinTable(name = "vaitro_quyenhan",
            joinColumns = @JoinColumn(name = "vaitro_id"),
            inverseJoinColumns = @JoinColumn(name = "quyenhan_id"))
    private List<QuyenHan> quyenHans;
}
