package com.example.sweet.config;

import java.util.HashMap;
import java.util.Map;

public enum KenhGiaoDichEnum {
    GIAO_DICH_TAI_QUAY(1L, "Giao dịch tại quầy"),
    GIAO_DICH_TRUC_TUYEN(2L, "Giao dịch trực tuyến");

    private final long code;
    private final String label;
    private static final Map<Long, KenhGiaoDichEnum> CODE_TO_ENUM = new HashMap<>();

    static {
        for (KenhGiaoDichEnum type : KenhGiaoDichEnum.values()) {
            CODE_TO_ENUM.put(type.code, type);
        }
    }

    KenhGiaoDichEnum(long code, String label) {
        this.code = code;
        this.label = label;
    }

    public long getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static KenhGiaoDichEnum fromCode(Long code) {
        return CODE_TO_ENUM.get(code);
    }
}