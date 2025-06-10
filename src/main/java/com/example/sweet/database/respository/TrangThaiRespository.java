package com.example.sweet.database.respository;

import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.database.schema.TrangThai;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrangThaiRespository extends CrudRepository<TrangThai, Integer> {}
