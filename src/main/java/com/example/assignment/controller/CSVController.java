package com.example.assignment.controller;

import com.example.assignment.model.Stock;
import com.example.assignment.response.ResponseMessage;
import com.example.assignment.response.StockByNameResponse;
import com.example.assignment.service.CSVService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/csv")
public class CSVController {

    @Autowired
    CSVService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        fileService.save(file);
        String message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @GetMapping("/getStocks/{stockName}")
    public ResponseEntity<StockByNameResponse> findStockByName(@PathVariable String stockName) {
        List<Stock> stockList = fileService.getStockbyName(stockName);
        StockByNameResponse response = new StockByNameResponse();
        response.setStockList(stockList);
        if (stockList.isEmpty()) {
            response.setMessage("No matching stock name found");
        } else {
            response.setMessage("Found the matching stocks - " + stockList.size());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/stock")
    public ResponseEntity<ResponseMessage> insertRecord(@RequestBody Stock stock) {
        fileService.saveStock(stock);
        String message = "Record inserted successfully";
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
    }
}
