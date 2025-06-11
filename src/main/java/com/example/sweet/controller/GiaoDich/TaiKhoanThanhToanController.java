package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.database.repository.TaiKhoan.TaiKhoanThanhToanRepository;
import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.services.GiaoDich.TaiKhoanThanhToanService;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/giao-dich/tktt")
@AllArgsConstructor
public class TaiKhoanThanhToanController {
    private TaiKhoanThanhToanService service;

    @GetMapping("/")
    @ApiMessage("Mah balls")
    public @ResponseBody Iterable<TaiKhoanThanhToan> getAllTaiKhoanThanhToan() {
        return service.findAll();
    }

    @PostMapping("/")
    public @ResponseBody TaiKhoanThanhToan insertTaiKhoanThanhToan(@RequestBody TaiKhoanThanhToan taiKhoan) {
        return service.save(taiKhoan);
    }

    @GetMapping("/loai/{id}")
    @ApiMessage("Mah balls")
    public @ResponseBody Optional<TaiKhoanThanhToan> getTaiKhoanThanhToanByLoai(@PathVariable int id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTaiKhoanThanhToan(@PathVariable int id) {
        service.deleteById(id);
    }
}
