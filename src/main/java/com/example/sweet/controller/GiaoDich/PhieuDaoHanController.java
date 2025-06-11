package com.example.sweet.controller.GiaoDich;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sweet.database.schema.GiaoDich.PhieuDaoHan;
import com.example.sweet.services.GiaoDich.PhieuDaoHanService;

@RestController
@RequestMapping("/api/v1/phieu-dao-han")

public class PhieuDaoHanController {
    @Autowired
    private PhieuDaoHanService service;

    @PostMapping
    public PhieuDaoHan create(@RequestBody PhieuDaoHan phieu) {
        return service.create(phieu);
    }

    @GetMapping
    public List<PhieuDaoHan> getAll() {
        return service.getAll();
    }

}