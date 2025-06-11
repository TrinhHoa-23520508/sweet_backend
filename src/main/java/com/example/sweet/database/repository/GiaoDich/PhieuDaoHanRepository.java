package com.example.sweet.database.repository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.PhieuDaoHan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuDaoHanRepository extends JpaRepository<PhieuDaoHan, Integer> {
}
