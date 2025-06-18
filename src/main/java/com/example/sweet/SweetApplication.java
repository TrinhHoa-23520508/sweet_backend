package com.example.sweet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;

@SpringBootApplication
//disable security
//@SpringBootApplication(exclude = {
//		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
//		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
//})
public class SweetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweetApplication.class, args);
    }

}
