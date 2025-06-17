package com.example.sweet.services;


import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import org.springframework.stereotype.Service;

@Service
public class KhachHangService {

    private final KhachHangRepository khachHangRepository;

    public KhachHangService(KhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

//    public boolean checkDuplicate()
}
