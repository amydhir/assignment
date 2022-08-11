package com.example.assignment.service;

import com.example.assignment.exceptions.EmptyInputException;
import com.example.assignment.exceptions.InvalidInputException;
import com.example.assignment.exceptions.UnexpectedServerException;
import com.example.assignment.helper.CSVHelper;
import com.example.assignment.model.Stock;
import com.example.assignment.repository.StockRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {

    @Autowired
    StockRepository repository;

    public void save(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new EmptyInputException("The Input file is empty - " + file.getOriginalFilename());
            }
            if (!CSVHelper.hasCSVFormat(file)) {
                throw new InvalidInputException("The format of the file is not supported - " + file.getContentType());
            }
            List<Stock> stockList = CSVHelper.csvToStockReader(file.getInputStream());
            repository.saveAll(stockList);
        } catch (IOException e) {
            throw new UnexpectedServerException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Stock> getStockbyName(String stockName) {
        if (stockName.isEmpty() || stockName.equals(" ") || stockName == null ) {
            throw new EmptyInputException("Stock name to be searched cannot be null/empty/blank");
        }
        return repository.findStocksByName(stockName);
    }

    public void saveStock (Stock stock) {
        if (stock.getStock() == null ) {
            throw new InvalidInputException("Stock object is not valid");
        }
        repository.save(stock);
    }
}
