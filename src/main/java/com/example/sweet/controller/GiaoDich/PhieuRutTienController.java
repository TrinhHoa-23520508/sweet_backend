package com.example.sweet.controller.GiaoDich;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sweet.database.schema.GiaoDich.PhieuRutTien;
import com.example.sweet.services.GiaoDich.PhieuRutTienService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/phieu-rut-tien")

public class PhieuRutTienController {
    @Autowired
    private PhieuRutTienService service;

    @PostMapping
    public PhieuRutTien create(@RequestBody PhieuRutTien phieu) {
        return service.create(phieu);
    }

    @GetMapping
    public List<PhieuRutTien> getAll() {
        return service.getAll();
    }

}
