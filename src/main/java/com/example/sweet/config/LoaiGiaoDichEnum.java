package com.example.sweet.config;

import java.util.HashMap;
import java.util.Map;

public enum LoaiGiaoDichEnum {
    GUI_TIEN_TAI_KHOAN_THANH_TOAN(1L, "Gửi tiền vào tài khoản thanh toán"),
    RUT_TIEN_TAI_KHOAN_THANH_TOAN(2L, "Rút tiền từ tài khoản thanh toán"),
    GUI_TIEN_PHIEU_GUI_TIEN(3L, "Gửi tiền vào phiếu gửi tiền"),
    RUT_TIEN_PHIEU_GUI_TIEN(4L, "Rút tiền từ phiếu gửi tiền"),
    TAT_TOAN_PHIEU_GUI_TIEN(5L, "Tất toán phiếu gửi tiền"),
    TRA_TIEN_LAI(6L, "Trả tiền lãi"),
    DAO_HAN_PHIEU_GUI_TIEN(7L, "Đáo hạn phiếu gửi tiền");

    private final long code;
    private final String label;
    private static final Map<Long, LoaiGiaoDichEnum> CODE_TO_ENUM = new HashMap<>();

    static {
        for (LoaiGiaoDichEnum type : LoaiGiaoDichEnum.values()) {
            CODE_TO_ENUM.put(type.code, type);
        }
    }

    LoaiGiaoDichEnum(long code, String label) {
        this.code = code;
        this.label = label;
    }

    public long getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static LoaiGiaoDichEnum fromCode(Long code) {
        return CODE_TO_ENUM.get(code);
    }
}