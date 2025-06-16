package com.example.sweet.database.respository.GiaoDich;

import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.ThamSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface GiaoDichRespository extends JpaRepository<GiaoDich, Long>, JpaSpecificationExecutor<GiaoDich> {}
