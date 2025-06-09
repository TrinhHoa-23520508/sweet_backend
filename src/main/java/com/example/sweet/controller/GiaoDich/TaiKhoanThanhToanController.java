package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.respository.TaiKhoan.TaiKhoanThanhToanRespository;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/giao-dich/tktt")
@AllArgsConstructor
public class TaiKhoanThanhToanController {
    private TaiKhoanThanhToanRespository respository;

    @GetMapping("/")
    @ApiMessage("Mah balls")
    public @ResponseBody Iterable<TaiKhoanThanhToan> getAllTaiKhoanThanhToan() {
        return respository.findAll();
    }

    @PostMapping("/")
    public @ResponseBody TaiKhoanThanhToan insertTaiKhoanThanhToan(@RequestBody TaiKhoanThanhToan taiKhoan) {
        respository.save(taiKhoan);
        return taiKhoan;
    }

    @DeleteMapping("/{id}")
    public void deleteTaiKhoanThanhToan(@PathVariable int id) {
        respository.deleteById(id);
    }
}
