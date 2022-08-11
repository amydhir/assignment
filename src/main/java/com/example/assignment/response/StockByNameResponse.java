package com.example.assignment.response;

import com.example.assignment.model.Stock;
import java.util.List;
import lombok.Data;

@Data
public class StockByNameResponse {

    private String message;
    private List<Stock> stockList;
}
