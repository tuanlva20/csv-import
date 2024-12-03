package com.read.csv.importExcel.repository;

import com.read.csv.importExcel.entity.SiteIdLoaiRan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteIdLoaiRanRepository extends JpaRepository<SiteIdLoaiRan, Long> {
  List<SiteIdLoaiRan> findAllBySource(String source);
}
