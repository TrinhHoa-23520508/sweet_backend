package com.example.sweet.service;


import com.example.sweet.database.respository.TaiKhoan.KhachHangRespository;
import org.springframework.stereotype.Service;

@Service
public class KhachHangService {

    private final KhachHangRespository khachHangRespository;

    public KhachHangService(KhachHangRespository khachHangRespository) {
        this.khachHangRespository = khachHangRespository;
    }

//    public boolean checkDuplicate()
}
