package com.read.csv.importExcel.service;

import com.read.csv.importExcel.entity.SiteIdLoaiRan;
import com.read.csv.importExcel.entity.TrienKhaiThietBi;
import com.read.csv.importExcel.repository.SiteIdLoaiRanRepository;
import com.read.csv.importExcel.repository.TrienKhaiThietBiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TrienKhaiThietBiService {
  @Autowired
  TrienKhaiThietBiRepository trienKhaiThietBiRepository;

  @Autowired
  SiteIdLoaiRanRepository siteIdLoaiRanRepository;

  @Transactional
  public void updateNgayOnAir() {
    List<SiteIdLoaiRan> siteIdLoaiRanList = siteIdLoaiRanRepository.findAllBySource("3G-4G-5G");
    List<TrienKhaiThietBi> trienKhaiThietBiList = trienKhaiThietBiRepository.findAll();

    // groupBy siteId
    Map<String, List<SiteIdLoaiRan>> groupBySiteId = siteIdLoaiRanList.stream()
        .filter(s -> s.getSiteId() != null)
        .collect(Collectors.groupingBy(SiteIdLoaiRan::getSiteId));

    for (Map.Entry<String, List<SiteIdLoaiRan>> entry : groupBySiteId.entrySet()) {
      List<TrienKhaiThietBi> trienKhaiThietBiFilterList = trienKhaiThietBiList.stream()
          .filter(t -> t.getSiteId() != null && t.getSiteId().equals(entry.getKey()))
          .toList();

      for (TrienKhaiThietBi trienKhaiThietBi : trienKhaiThietBiFilterList) {
        Date minDate = entry.getValue().stream()
            .map(SiteIdLoaiRan::getNgay)
            .filter(Objects::nonNull)
            .map(this::convertStringToDate)
            .filter(Objects::nonNull)
            .min(Date::compareTo)
            .orElse(null);

        if (minDate != null) {
          Date onAirDate = this.convertStringToDate(trienKhaiThietBi.getNgayOnAir());
          if (onAirDate == null || minDate.before(onAirDate)) {
            trienKhaiThietBi.setNgayOnAir(convertDateToString(minDate));
            trienKhaiThietBiRepository.save(trienKhaiThietBi);
          }
        }
      }
    }
  }

  private Date convertStringToDate(String dateString) {
    try {
      return dateString == null ? null : new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
    } catch (ParseException e) {
      System.out.println("Can't convert String to Date: " + dateString);
      return null;
    }
  }

  private String convertDateToString(Date date) {
    if (date != null) {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      return sdf.format(date);
    }
    return null;
  }
}
