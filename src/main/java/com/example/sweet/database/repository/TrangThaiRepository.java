package com.example.sweet.database.repository;

import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.database.schema.TrangThai;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrangThaiRepository extends JpaRepository<TrangThai, Long> {
}
