package com.techprimers.stock.dbservice.repository;

import com.techprimers.stock.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuetesRepository extends JpaRepository<Quote, Integer> {
    public List<Quote> findByUserName(String username);
}
