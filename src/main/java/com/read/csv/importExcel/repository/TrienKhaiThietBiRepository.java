package com.read.csv.importExcel.repository;

import com.read.csv.importExcel.entity.TrienKhaiThietBi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrienKhaiThietBiRepository extends JpaRepository<TrienKhaiThietBi, Long> {
}
