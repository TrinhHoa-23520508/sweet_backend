package com.example.sweet.controller;

import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.domain.request.TrangThaiDTO;
import com.example.sweet.services.TrangThaiService;
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
public class TrangThaiController {

    private final TrangThaiService trangThaiService;
    public TrangThaiController(TrangThaiService trangThaiService) {
        this.trangThaiService = trangThaiService;
    }

    //create a new trang thai
    @PostMapping("/trang-thai")
    @ApiMessage("Create a new Trang Thai")
    public ResponseEntity<TrangThaiDTO> createTrangThai(@Valid @RequestBody TrangThaiDTO trangThaiDTO) {
        TrangThaiDTO createdTrangThai = trangThaiService.createTrangThai(trangThaiDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrangThai);
    }

    //get all trang thai of loai trang thai
    @GetMapping("/trang-thai/loai/{loaiTrangThaiId}")
    @ApiMessage("Get all Trang Thai by Loai Trang Thai ID")
    public ResponseEntity<List<TrangThaiDTO>> getAllTrangThaiByLoai(@PathVariable("loaiTrangThaiId") Long loaiTrangThaiId) {
        List<TrangThaiDTO> trangThaiList = trangThaiService.getAllTrangThaiByLoai(loaiTrangThaiId);
        return ResponseEntity.ok(trangThaiList);
    }

    //get trang thai by ID
    @GetMapping("/trang-thai/{id}")
    @ApiMessage("Get Trang Thai by ID")
    public ResponseEntity<TrangThaiDTO> getTrangThaiById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(trangThaiService.getTrangThaiById(id));
    }

    //gett all trang thai
    @GetMapping("/trang-thai")
    @ApiMessage("Get all Trang Thai")
    public ResponseEntity<List<TrangThaiDTO>> getAllTrangThai(@Filter Specification<TrangThai> specification) {
        List<TrangThaiDTO> trangThaiList = trangThaiService.getAllTrangThai(specification);
        return ResponseEntity.ok(trangThaiList);
    }

    //update trang thai
    @PutMapping("/trang-thai/{id}")
    @ApiMessage("Update Trang Thai by ID")
    public ResponseEntity<TrangThaiDTO> updateTrangThai(@PathVariable("id") Long id, @Valid @RequestBody TrangThaiDTO trangThaiDTO) {
        trangThaiDTO.setTrangThaiID(id);
        TrangThaiDTO updatedTrangThai = trangThaiService.updateTrangThai(trangThaiDTO);
        return ResponseEntity.ok(updatedTrangThai);
    }

    //delete trang thai
    @DeleteMapping("/trang-thai/{id}")
    @ApiMessage("Delete Trang Thai by ID")
    public ResponseEntity<Void> deleteTrangThai(@PathVariable("id") Long id) {
        trangThaiService.deleteTrangThai(id);
        return ResponseEntity.noContent().build();
    }

}
