package com.example.sweet.config;

import java.util.HashMap;
import java.util.Map;

public enum LoaiTKNguonEnum {
    TAI_KHOAN_THANH_TOAN(1L, "Tài khoản thanh toán"),
    PHIEU_GUI_TIEN(2L, "Phiếu gửi tiền"),
    TIEN_MAT_TAI_QUAY(3L, "Tiền mặt tại quầy"),
    NGAN_HANG(4L, "Ngân hàng");

    private final long code;
    private final String label;
    private static final Map<Long, LoaiTKNguonEnum> CODE_TO_ENUM = new HashMap<>();

    // Initialize the map
    static {
        for (LoaiTKNguonEnum type : LoaiTKNguonEnum.values()) {
            CODE_TO_ENUM.put(type.code, type);
        }
    }

    LoaiTKNguonEnum(long code, String label) {
        this.code = code;
        this.label = label;
    }

    public long getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static LoaiTKNguonEnum fromCode(Long code) {
        return CODE_TO_ENUM.get(code);
    }
}