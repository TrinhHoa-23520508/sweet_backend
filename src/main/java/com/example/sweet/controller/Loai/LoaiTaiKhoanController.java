package com.example.sweet.controller.Loai;

import com.example.sweet.database.respository.Loai.LoaiTaiKhoanRespository;
import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import com.example.sweet.util.annotation.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loai-tai-khoan")
public class LoaiTaiKhoanController {
    @Autowired private LoaiTaiKhoanRespository respository;

    @GetMapping("/")
    @ApiMessage("Mah balls")
    public @ResponseBody Iterable<LoaiTaiKhoan> getAllLoaiTaiKhoan() {
        return respository.findAll();
    }

    @PostMapping("/")
    public @ResponseBody LoaiTaiKhoan insertLoaiTaiKhoan(@RequestBody LoaiTaiKhoan loaiTK) {
        respository.save(loaiTK);
        return loaiTK;
    }

    @DeleteMapping("/{id}")
    public void deleteLoaiTaiKhoan(@PathVariable int id) {
        respository.deleteById(id);
    }
}
