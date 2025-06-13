package com.example.sweet.controller.GiaoDich;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sweet.services.GiaoDich.PhieuDaoHanService;
import com.example.sweet.util.annotation.ApiMessage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/phieu-dao-han")
public class PhieuDaoHanController {
    private final PhieuDaoHanService phieuDaoHanService;

    public PhieuDaoHanController(PhieuDaoHanService phieuDaoHanService) {
        this.phieuDaoHanService = phieuDaoHanService;
    }

    @GetMapping("/phieu-dao-han")
    @ApiMessage("Tạo phiếu đáo hạn")
    public String createPhieuDaoHan(@RequestBody String phieuDaoHanDTO) {
        return new String();
    }

}
