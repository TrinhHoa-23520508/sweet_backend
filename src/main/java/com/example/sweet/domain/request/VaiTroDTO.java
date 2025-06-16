package com.example.sweet.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VaiTroDTO {

    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private List<Long> quyenHanIds;
}
