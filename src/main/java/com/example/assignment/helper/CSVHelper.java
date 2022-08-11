package com.example.assignment.helper;

import com.example.assignment.exceptions.InvalidInputException;
import com.example.assignment.model.Stock;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERS = {"quarter", "stock", "date", "open", "high", "low", "close", "volume", "percent_change_price", "percent_change_volume_over_last_wk", "previous_weeks_volume", "next_weeks_open", "next_weeks_close",
            "percent_change_next_weeks_price", "days_to_next_dividend", "percent_return_next_dividend"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Stock> csvToStockReader(InputStream is) throws InvalidInputException {
        int count = 0;
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8")); CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Stock> stockList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            if (!Arrays.equals(csvParser.getHeaderNames().toArray(), HEADERS)) {
                throw new InvalidInputException("The header of CSV file is not correct please follow this " + Arrays.toString(HEADERS) + " current headers - " + csvParser.getHeaderNames());
            }
            for (CSVRecord csvRecord : csvRecords) {
                count++;
                Stock stock = Stock.builder().quarter(csvRecord.get("quarter").isEmpty() ? null : Integer.valueOf(csvRecord.get("quarter"))).stock(csvRecord.get("stock").isEmpty() ? null : csvRecord.get("stock"))
                        .date(csvRecord.get("date").isEmpty() ? null : new SimpleDateFormat("MM/dd/yyyy").parse(csvRecord.get("date"))).open(csvRecord.get("open").isEmpty() ? null : Double.valueOf(csvRecord.get("open").substring(1)))
                        .high(csvRecord.get("high").isEmpty() ? null : Double.valueOf(csvRecord.get("high").substring(1))).low(csvRecord.get("low").isEmpty() ? null : Double.valueOf(csvRecord.get("low").substring(1)))
                        .close(csvRecord.get("close").isEmpty() ? null : Double.valueOf(csvRecord.get("close").substring(1))).volume(csvRecord.get("volume").isEmpty() ? null : Long.valueOf(csvRecord.get("volume")))
                        .percent_change_price(csvRecord.get("percent_change_price").isEmpty() ? null : Double.valueOf(csvRecord.get("percent_change_price")))
                        .percent_change_volume_over_last_wk(csvRecord.get("percent_change_volume_over_last_wk").isEmpty() ? null : Double.valueOf(csvRecord.get("percent_change_volume_over_last_wk")))
                        .previous_weeks_volume(csvRecord.get("previous_weeks_volume").isEmpty() ? null : Long.valueOf(csvRecord.get("previous_weeks_volume")))
                        .next_weeks_open(csvRecord.get("next_weeks_open").isEmpty() ? null : Double.valueOf(csvRecord.get("next_weeks_open").substring(1)))
                        .next_weeks_close(csvRecord.get("next_weeks_close").isEmpty() ? null : Double.valueOf(csvRecord.get("next_weeks_close").substring(1)))
                        .percent_change_next_weeks_price(csvRecord.get("percent_change_next_weeks_price").isEmpty() ? null : Double.valueOf(csvRecord.get("percent_change_next_weeks_price")))
                        .days_to_next_dividend(csvRecord.get("days_to_next_dividend").isEmpty() ? null : Integer.valueOf(csvRecord.get("days_to_next_dividend")))
                        .percent_return_next_dividend(csvRecord.get("percent_return_next_dividend").isEmpty() ? null : Double.valueOf(csvRecord.get("percent_return_next_dividend"))).build();
                stockList.add(stock);
            }
            return stockList;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Empty Value on line - " + count);
        } catch (ParseException | IOException e) {
            throw new InvalidInputException("Fail to parse CSV file at line - " + count);
        }
    }

}
