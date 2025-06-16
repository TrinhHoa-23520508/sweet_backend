package com.example.sweet.controller;

import com.example.sweet.database.schema.TaiKhoan.DiaChi;
import com.example.sweet.service.DiaChiService;
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
public class DiaChiController {

    private final DiaChiService diaChiService;

    public DiaChiController(DiaChiService diaChiService) {
        this.diaChiService = diaChiService;
    }

    //Create a new DiaChi
     @PostMapping("/dia-chi")
     @ApiMessage("Create a new Dia Chi")
    public ResponseEntity<DiaChi> createDiaChi(@Valid @RequestBody DiaChi diaChi) {
        DiaChi createdDiaChi = diaChiService.createDiaChi(diaChi);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiaChi);
    }

    //get dia chi by ID
    @GetMapping("/dia-chi/{id}")
    @ApiMessage("Get Dia Chi by ID")
    public ResponseEntity<DiaChi> getDiaChiById(@PathVariable("id") Long id) {
        DiaChi diaChi = diaChiService.getDiaChiById(id);
        return ResponseEntity.ok(diaChi);
    }

    //update dia chi
    @PutMapping("/dia-chi/{id}")
    @ApiMessage("Update Dia Chi by ID")
    public ResponseEntity<DiaChi> updateDiaChi(@PathVariable("id") Long id, @Valid @RequestBody DiaChi diaChi) {
        diaChi.setDiaChiID(id);
        DiaChi updatedDiaChi = diaChiService.updateDiaChi(diaChi);
        return ResponseEntity.ok(updatedDiaChi);
    }

    //get all dia chi by filter
    @GetMapping("/dia-chi")
    @ApiMessage("Get all Dia Chi")
    public ResponseEntity<List<DiaChi>> getAllDiaChi(@Filter Specification<DiaChi> specification) {
        List<DiaChi> diaChiList = diaChiService.getAllDiaChi(specification);
        return ResponseEntity.ok(diaChiList);
    }


}
