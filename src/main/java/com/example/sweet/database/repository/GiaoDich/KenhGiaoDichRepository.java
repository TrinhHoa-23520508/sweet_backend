package com.example.sweet.database.repository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KenhGiaoDichRepository extends JpaRepository<KenhGiaoDich, Long> {
}
