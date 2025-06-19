package com.example.sweet.controller.GiaoDich;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sweet.database.repository.dto.PhieuDaoHanDTO;
import com.example.sweet.database.repository.dto.PhieuDaoHanDTO_inp;
import com.example.sweet.services.GiaoDich.PhieuDaoHanService;
import com.example.sweet.util.annotation.ApiMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1")
public class PhieuDaoHanController {
    private final PhieuDaoHanService phieuDaoHanService;

    public PhieuDaoHanController(PhieuDaoHanService phieuDaoHanService) {
        this.phieuDaoHanService = phieuDaoHanService;
    }

    @GetMapping("/phieu-dao-han")
    @ApiMessage("Lấy các phiếu đáo hạn")
    public String doSth(@RequestBody String phieuDaoHanDTO) {
        return new String();
    }

    @PostMapping("/phieu-dao-han")
    @ApiMessage("Tạo mới một phiếu đáo hạn")
    public ResponseEntity<PhieuDaoHanDTO> createPhieuDaoHan(@RequestBody PhieuDaoHanDTO_inp dto) {
        // TODO: process POST request
        return ResponseEntity.status(HttpStatus.CREATED).body(this.phieuDaoHanService.handleCreatePhieuDaoHan(dto));
        // return entity;
        // return null;
    }

}
