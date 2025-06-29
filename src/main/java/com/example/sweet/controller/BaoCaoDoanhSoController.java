package com.example.sweet.controller;

import com.example.sweet.database.schema.BaoCaoDoanhSo;
import com.example.sweet.database.schema.ChiTietBaoCaoDoanhSo;
import com.example.sweet.services.BaoCaoDoanhSoService;
import com.example.sweet.util.annotation.ApiMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BaoCaoDoanhSoController {

    private final BaoCaoDoanhSoService baoCaoDoanhSoService;

    public BaoCaoDoanhSoController(BaoCaoDoanhSoService baoCaoDoanhSoService) {
        this.baoCaoDoanhSoService = baoCaoDoanhSoService;
    }

    @GetMapping("/bao-cao-doanh-so")
    @ApiMessage("Get all bao cao doanh so")
    public ResponseEntity<List<BaoCaoDoanhSo>> getAllBaoCaoDoanhSo() {
        return ResponseEntity.ok(this.baoCaoDoanhSoService.getAllBaoCaoDoanhSo());
    }

    @GetMapping("/bao-cao-doanh-so/{id}/chi-tiet")
    @ApiMessage("Get all chi tiet bao cao doanh so of bao cao doanh so")
    public ResponseEntity<List<ChiTietBaoCaoDoanhSo>> getAllChiTietBaoCaoByBaoCao(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.baoCaoDoanhSoService.getAllChiTietBaoCaoByBaoCao(id));
    }


}
