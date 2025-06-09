package com.example.sweet.controller;

import com.example.sweet.database.respository.TaiKhoan.NhanVienRespository;
import com.example.sweet.database.respository.ThamSoRespository;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.ThamSo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ThamSoController {
    @Autowired
    private ThamSoRespository thamSoRespository;

    @GetMapping("/thamso")
    public @ResponseBody Iterable<ThamSo> getAllThamSo() {
        return thamSoRespository.findAll();
    }

    @PostMapping("/thamso")
    public @ResponseBody ThamSo addThamSo(@RequestBody ThamSo thamSo) {
        return thamSoRespository.save(thamSo);
    }

    @DeleteMapping("/thamso/{id}")
    void deleteEmployee(@PathVariable int id) {
        thamSoRespository.deleteById(id);
    }
}
