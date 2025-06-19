package com.example.sweet.controller;

import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.services.LoaiTrangThaiService;
import com.example.sweet.util.annotation.ApiMessage;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LoaiTrangThaiController {

    private final LoaiTrangThaiService loaiTrangThaiService;

    public LoaiTrangThaiController(LoaiTrangThaiService loaiTrangThaiService) {
        this.loaiTrangThaiService = loaiTrangThaiService;
    }

    //create a new LoaiTrangThai
    @PostMapping("/loai-trang-thai")
    @ApiMessage("Create a new Loai Trang Thai")
    public ResponseEntity<LoaiTrangThai> createLoaiTrangThai(@Valid @RequestBody LoaiTrangThai loaiTrangThai) {
        LoaiTrangThai createdLoaiTrangThai = loaiTrangThaiService.createLoaiTrangThai(loaiTrangThai);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoaiTrangThai);
    }

    //get all LoaiTrangThai
    @GetMapping("/loai-trang-thai")
    @ApiMessage("Get all Loai Trang Thai")
    public ResponseEntity<List<LoaiTrangThai>> getAllLoaiTrangThai(@Filter Specification<LoaiTrangThai> specification) {
        List<LoaiTrangThai> loaiTrangThaiList = loaiTrangThaiService.getAllLoaiTrangThai(specification);
        return ResponseEntity.ok(loaiTrangThaiList);
    }

    //get LoaiTrangThai By ID
    @GetMapping("/loai-trang-thai/{id}")
    @ApiMessage("Get Loai Trang Thai by ID")
    public ResponseEntity<LoaiTrangThai> getLoaiTrangThaiById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(loaiTrangThaiService.getLoaiTrangThaiById(id));
    }

    //update LoaiTrangThai
    @PutMapping("/loai-trang-thai/{id}")
    @ApiMessage("Update Loai Trang Thai by ID")
    public ResponseEntity<LoaiTrangThai> updateLoaiTrangThai(@PathVariable("id") Long id, @Valid @RequestBody LoaiTrangThai loaiTrangThai) {
        loaiTrangThai.setLoaiTrangThaiID(id);
        LoaiTrangThai updatedLoaiTrangThai = loaiTrangThaiService.updateLoaiTrangThai(loaiTrangThai);
        return ResponseEntity.ok(updatedLoaiTrangThai);
    }

    //delete LoaiTrangThai
    @DeleteMapping("/loai-trang-thai/{id}")
    @ApiMessage("Delete Loai Trang Thai by ID")
    public ResponseEntity<Void> deleteLoaiTrangThai(@PathVariable("id") Long id) {
        loaiTrangThaiService.deleteLoaiTrangThai(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
