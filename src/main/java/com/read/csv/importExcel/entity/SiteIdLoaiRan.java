package com.read.csv.importExcel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "SITE_ID_LOAI_RAN")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class SiteIdLoaiRan {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SITE_ID_LOAI_RAN_SEQUENCE")
  @SequenceGenerator(name = "SITE_ID_LOAI_RAN_SEQUENCE", sequenceName = "SITE_ID_LOAI_RAN_SEQUENCE", allocationSize = 1)
  @Column(name = "ID", nullable = false)
  Long id;

  @Column(name = "LOAI_RAN")
  String loaiRan;

  @Column(name = "SITE_ID")
  String siteId;

  @Column(name = "NGAY")
  String ngay;

  @Column(name = "SOURCE")
  String source;

  @Transient
  Date ngayDate;

  public void setNgayDate(Date ngayDate) {
    this.ngayDate = ngayDate;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    if (this.ngayDate != null) {
      this.setNgay(dateFormat.format(this.ngayDate));
    }
  }

}
