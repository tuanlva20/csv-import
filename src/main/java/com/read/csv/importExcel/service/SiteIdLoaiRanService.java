package com.read.csv.importExcel.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.read.csv.importExcel.entity.SiteIdLoaiRan;
import com.read.csv.importExcel.repository.SiteIdLoaiRanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class SiteIdLoaiRanService {
  @Autowired
  private SiteIdLoaiRanRepository siteIdLoaiRanRepository;

  @Transactional
  public void processFile(String filePath, String loaiRan) throws IOException {
    final int BATCH_SIZE = 1000000;
    List<CompletableFuture<Map<String, Date>>> futures = new ArrayList<>();
    int totalRows = this.getTotalRows(filePath);
    AtomicInteger startRow = new AtomicInteger(0);
    List<SiteIdLoaiRan> siteIdLoaiRanList = siteIdLoaiRanRepository.findAll();

    while (startRow.get() < totalRows) {
      AtomicInteger endRow = new AtomicInteger(Math.min(startRow.get() + BATCH_SIZE, totalRows));
      futures.add(processFileAsync(loaiRan, filePath, startRow.get(), endRow.get()));
      startRow.addAndGet(BATCH_SIZE);
    }

    List<Map<String, Date>> resultList = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
        .thenApply(v -> futures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList())
        ).join();

    Map<String, Date> resultMap = new HashMap<>();
    for (Map<String, Date> item : resultList) {
      item.forEach((key, value) -> {
        if (!resultMap.containsKey(key)){
          resultMap.put(key, value);
        } else {
          Date currentDate = resultMap.get(key);
          if (value.before(currentDate)) {
            resultMap.put(key, value);
          }
        }
      });
    }

    List<SiteIdLoaiRan> insertList = new ArrayList<>();
    resultMap.forEach((k, v) -> {
      SiteIdLoaiRan excel = siteIdLoaiRanList.stream()
          .filter(e -> e.getSiteId() != null && e.getSiteId().equals(k) && e.getLoaiRan().equals(loaiRan))
          .findFirst()
          .orElse(null);

      if (excel == null) {
        SiteIdLoaiRan newExcel = new SiteIdLoaiRan();
        newExcel.setSiteId(k);
        newExcel.setNgayDate(v);
        newExcel.setLoaiRan(loaiRan);
        newExcel.setSource("3G-4G-5G");
        insertList.add(newExcel);
      }
    });

    if (!insertList.isEmpty()) siteIdLoaiRanRepository.saveAll(insertList);
  }

  @Async
  public CompletableFuture<Map<String, Date>> processFileAsync(String loaiRan, String filePath, int startRow, int endRow) throws IOException {
    Map<String, Date> excelList = new HashMap<>();
    try (InputStream inputStream = CSVReader.class.getClassLoader().getResourceAsStream(filePath)) {
      int currentRow = 0;
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
          CSVReader csvReader = new CSVReader(reader)) {
        String[] nextRecord;

        // Read the file line by line
        while ((nextRecord = csvReader.readNext()) != null) {
          if (currentRow > endRow - 1) break;
          if (currentRow >= startRow) {
            System.out.print(currentRow + "...............................................\n");

            if (nextRecord[0].equals("STT")) continue;

            SiteIdLoaiRan excel = new SiteIdLoaiRan();
            excel.setSiteId(nextRecord[1]);
            excel.setNgayDate(new Date(nextRecord[3]));
            
            double data = 0.0;
            if (loaiRan.equals("3G") && !nextRecord[11].isEmpty()){
              data = Double.parseDouble(nextRecord[11]);
            } else if (!loaiRan.equals("3G")  && !nextRecord[9].isEmpty()){
              data = Double.parseDouble(nextRecord[9]);
            }
            
            if (data > 0 && excel.getNgayDate() != null) {
              if (excelList.containsKey(excel.getSiteId())) {
                Date currentDate = excelList.get(excel.getSiteId());
                if (excel.getNgayDate().before(currentDate)) {
                  excelList.put(excel.getSiteId(), excel.getNgayDate());
                }
              } else {
                excelList.put(excel.getSiteId(), excel.getNgayDate());
              }
            }
          }
          currentRow++;
        }
      } catch (CsvValidationException e) {
        throw new RuntimeException(e);
      }
    }
    return CompletableFuture.completedFuture(excelList);
  }

  private int getTotalRows(String filePath) {
    int count = 0;
    try (InputStream inputStream = CSVReader.class.getClassLoader().getResourceAsStream(filePath)) {

      // Check if the file exists
      if (inputStream == null) {
        throw new RuntimeException("File not found: " + filePath);
      }

      try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
          CSVReader csvReader = new CSVReader(reader)) {
        while (csvReader.readNext() != null) {
          count++;
        }
      } catch (CsvValidationException e) {
        throw new RuntimeException(e);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return count;
  }

}
