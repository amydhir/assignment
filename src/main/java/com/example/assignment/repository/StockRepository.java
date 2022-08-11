package com.example.assignment.repository;

import com.example.assignment.model.Stock;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("select s from Stock s where s.stock = ?1")
    List<Stock> findStocksByName(String name);
}
