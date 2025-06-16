package com.example.sweet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class KhachHangController {

    @PostMapping("/khach-hang")
    public ResponseEntity<String> createKhachHang() {
        // Logic to create a new Khach Hang (Customer)
        return ResponseEntity.ok("Khach Hang created successfully");
    }
}
