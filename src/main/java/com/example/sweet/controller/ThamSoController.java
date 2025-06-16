package com.example.sweet.controller;

import com.example.sweet.database.respository.TaiKhoan.NhanVienRespository;
import com.example.sweet.database.respository.ThamSoRespository;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.service.ThamSoService;
import com.example.sweet.util.annotation.ApiMessage;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ThamSoController {

    private final ThamSoService thamSoService;

    public ThamSoController(ThamSoService thamSoService) {
        this.thamSoService = thamSoService;
    }

    //create a new Tham So
    @PostMapping("/tham-so")
    @ApiMessage("Create a new Tham So")
    public ResponseEntity<ThamSo> createThamSo(@Valid @RequestBody ThamSo thamSo) {
        ThamSo createdThamSo = thamSoService.createThamSo(thamSo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdThamSo);
    }

    //Get all tham so
    @GetMapping("/tham-so")
    @ApiMessage("Get all Tham So")
    public ResponseEntity<List<ThamSo>> getAllThamSo(@Filter Specification<ThamSo> specification) {
        List<ThamSo> thamSoList = thamSoService.getAllThamSo(specification);
        return ResponseEntity.ok(thamSoList);
    }

    //Get tham so by ID
    @GetMapping("/tham-so/{id}")
    @ApiMessage("Get Tham So by ID")
    public ResponseEntity<ThamSo> getThamSoById(@PathVariable("id") Long id) {
        ThamSo thamSo = thamSoService.getThamSoById(id);
        return ResponseEntity.ok(thamSo);
    }

    //Update tham so
    @PutMapping("/tham-so/{id}")
    @ApiMessage("Update Tham So by ID")
    public ResponseEntity<ThamSo> updateThamSo(@PathVariable("id") Long id, @Valid @RequestBody ThamSo thamSo) {
        thamSo.setThamSoID(id);
        ThamSo updatedThamSo = thamSoService.updateThamSo(thamSo);
        return ResponseEntity.ok(updatedThamSo);
    }


}
