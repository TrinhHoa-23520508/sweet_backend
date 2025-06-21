package com.example.sweet.controller;

import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.services.QuyenHanService;
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
public class QuyenHanController {

    private final QuyenHanService quyenHanService;

    public QuyenHanController(QuyenHanService quyenHanService) {
        this.quyenHanService = quyenHanService;
    }

    //create a new quyen han
    @PostMapping("/quyen-han")
    @ApiMessage("Create a new Quyen Han")
    public ResponseEntity<QuyenHan> createQuyenHan(@Valid @RequestBody QuyenHan quyenHan) {
        QuyenHan createdQuyenHan = quyenHanService.createQuyenHan(quyenHan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuyenHan);
    }

    //get all quyen han
    @GetMapping("/quyen-han")
    @ApiMessage("Get all Quyen Han")
    public ResponseEntity<List<QuyenHan>> getAllQuyenHan(@Filter Specification<QuyenHan> specification) {
        List<QuyenHan> quyenHanList = quyenHanService.getAllQuyenHan(specification);
        return ResponseEntity.ok(quyenHanList);
    }

    //get quyen han by ID
    @GetMapping("/quyen-han/{id}")
    @ApiMessage("Get Quyen Han by ID")
    public ResponseEntity<QuyenHan> getQuyenHanById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(quyenHanService.getQuyenHanById(id));
    }

    //update quyen han
    @PutMapping("/quyen-han/{id}")
    @ApiMessage("Update Quyen Han by ID")
    public ResponseEntity<QuyenHan> updateQuyenHan(@PathVariable("id") Long id, @Valid @RequestBody QuyenHan quyenHan) {
        quyenHan.setId(id);
        QuyenHan updatedQuyenHan = quyenHanService.updateQuyenHan(quyenHan);
        return ResponseEntity.ok(updatedQuyenHan);
    }

    //delete quyen han
    @DeleteMapping("/quyen-han/{id}")
    @ApiMessage("Delete Quyen Han by ID")
    public ResponseEntity<Void> deleteQuyenHan(@PathVariable("id") Long id) {
        quyenHanService.deleteQuyenHan(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //create list of quyen han
    @PostMapping("/quyen-han/danh-sach")
    @ApiMessage("Create a list of Quyen Han")
    public ResponseEntity<List<QuyenHan>> createListQuyenHan(@Valid @RequestBody List<QuyenHan> quyenHanList) {
        List<QuyenHan> createdQuyenHanList = quyenHanService.createListQuyenHan(quyenHanList);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuyenHanList);
    }

}
