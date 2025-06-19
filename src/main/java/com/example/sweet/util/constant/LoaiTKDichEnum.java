package com.example.sweet.util.constant;

import java.util.HashMap;
import java.util.Map;

public enum LoaiTKDichEnum {
    TAI_KHOAN_THANH_TOAN(1L, "Tài khoản thanh toán"),
    PHIEU_GUI_TIEN(2L, "Phiếu gửi tiền"),
    TIEN_MAT_TAI_QUAY(3L, "Tiền mặt tại quầy"),
    NGAN_HANG(4L, "Ngân hàng");

    private final long code;
    private final String label;
    private static final Map<Long, LoaiTKDichEnum> CODE_TO_ENUM = new HashMap<>();

    static {
        for (LoaiTKDichEnum type : LoaiTKDichEnum.values()) {
            CODE_TO_ENUM.put(type.code, type);
        }
    }

    LoaiTKDichEnum(long code, String label) {
        this.code = code;
        this.label = label;
    }

    public long getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static LoaiTKDichEnum fromCode(Long code) {
        return CODE_TO_ENUM.get(code);
    }
}