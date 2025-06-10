package com.example.sweet.database.respository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KenhGiaoDichRespository extends CrudRepository<KenhGiaoDich, Integer> {}
