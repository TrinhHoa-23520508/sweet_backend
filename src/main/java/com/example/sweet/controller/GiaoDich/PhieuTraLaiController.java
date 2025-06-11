package com.example.sweet.controller.GiaoDich;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sweet.database.schema.GiaoDich.PhieuTraLai;
import com.example.sweet.services.GiaoDich.PhieuTraLaiService;

@RestController
@RequestMapping("/api/v1/phieu-tra-lai")

public class PhieuTraLaiController {
    @Autowired
    private PhieuTraLaiService service;

    @PostMapping
    public PhieuTraLai create(@RequestBody PhieuTraLai phieu) {
        return service.create(phieu);
    }

    @GetMapping
    public List<PhieuTraLai> getAll() {
        return service.getAll();
    }

}