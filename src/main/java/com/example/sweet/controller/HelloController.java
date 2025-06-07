package com.example.sweet.controller;

import com.example.sweet.database.respository.TaiKhoan.NhanVienRespository;
import com.example.sweet.database.respository.ThamSoRespository;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.ThamSo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired private ThamSoRespository thamSoRespository;

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World";
    }

}
