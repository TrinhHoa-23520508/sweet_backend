package com.example.sweet.database.schema;

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
public class ThamSo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long thamSoID;
    private String tenThamSo;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String moTa;
    private String maThamSo;
    private String giaTri;


}
