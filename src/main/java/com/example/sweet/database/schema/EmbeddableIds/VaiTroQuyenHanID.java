package com.example.sweet.database.schema.EmbeddableIds;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class VaiTroQuyenHanID implements Serializable {
    private Integer vaiTroID;
    private Integer quyenHanID;

    // Default constructor
    public VaiTroQuyenHanID() {}

    public VaiTroQuyenHanID(Integer vaiTroID, Integer quyenHanID) {
        this.vaiTroID = vaiTroID;
        this.quyenHanID = quyenHanID;
    }

    // Getters and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VaiTroQuyenHanID)) return false;
        VaiTroQuyenHanID that = (VaiTroQuyenHanID) o;
        return vaiTroID.equals(that.vaiTroID) &&
                quyenHanID.equals(that.quyenHanID);
    }

    @Override
    public int hashCode() {
        return vaiTroID.hashCode() + quyenHanID.hashCode();
    }
}
