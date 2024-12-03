package com.read.csv.importExcel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TRIEN_KHAI_THIET_BI")
public class TrienKhaiThietBi {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MA_TB", nullable = false)
  private Long id;

  @Column(name = "DAI_VT", length = 150)
  private String daiVt;

  @Column(name = "GHI_CHU")
  private String ghiChu;

  @Column(name = "GOIPA", length = 150)
  private String goipa;

  @Column(name = "HANG_MUC", length = 150)
  private String hangMuc;

  @Column(name = "HINH_THUC_LAP", length = 150)
  private String hinhThucLap;

  @Column(name = "ID_PTM", length = 50)
  private String idPtm;

  @Column(name = "KHAO_SAT")
  private String khaoSat;

  @Column(name = "LOAICSHT", length = 150)
  private String loaicsht;

  @Column(name = "LY_DOCR", length = 4000)
  private String lyDocr;

  @Column(name = "MA_DU_AN", length = 50)
  private String maDuAn;

  @Column(name = "MA_TRAM", length = 50)
  private String maTram;

  @Column(name = "NGAYCDD", length = 20)
  private String ngaycdd;

  @Column(name = "NGAY_CHUYEN_HANG_DEN_SITE", length = 20)
  private String ngayChuyenHangDenSite;

  @Column(name = "NGAY_LAP_DAT", length = 20)
  private String ngayLapDat;

  @Column(name = "NGAY_LAP_TU_NGUON", length = 20)
  private String ngayLapTuNguon;

  @Column(name = "NGAY_ON_AIR", length = 20)
  private String ngayOnAir;

  @Column(name = "NGAY_PAT", length = 20)
  private String ngayPat;

  @Column(name = "NGAY_TICH_HOP", length = 20)
  private String ngayTichHop;

  @Column(name = "NGAY_TRUYEN_DAN", length = 20)
  private String ngayTruyenDan;

  @Column(name = "NGAYVLAN", length = 20)
  private String ngayvlan;

  @Column(name = "NGAY_XUAT_HANG_TAI_KHO", length = 20)
  private String ngayXuatHangTaiKho;

  @Column(name = "NGUOI_TAO", length = 100)
  private String nguoiTao;

  @Column(name = "PHONGVTDUYET", length = 50)
  private String phongvtduyet;

  @Column(name = "SITE_ID", length = 50)
  private String siteId;

  @Column(name = "SOCR", length = 50)
  private String socr;

  @Column(name = "SOLIEU_TREN_SMARTF", length = 50)
  private String solieuTrenSmartf;

  @Column(name = "THANH_PHO", length = 150)
  private String thanhPho;

  @Column(name = "THIET_KEHDL", length = 150)
  private String thietKehdl;

  @Column(name = "THIET_KERF", length = 150)
  private String thietKerf;

  @Column(name = "THIET_KE_THI_CONG", length = 150)
  private String thietKeThiCong;

  @Column(name = "TINH", length = 150)
  private String tinh;

  @Column(name = "TINH_TRANGCR", length = 50)
  private String tinhTrangcr;

  @Column(name = "TINH_TRANG_THIET_KE", length = 50)
  private String tinhTrangThietKe;

  @Column(name = "TINH_TRANG_TRUYEN_DAN", length = 50)
  private String tinhTrangTruyenDan;

  @Column(name = "TRAM_DE_XUAT_THAY_THE", length = 50)
  private String tramDeXuatThayThe;

  @Column(name = "TRANG_THAI", length = 20)
  private String trangThai;

  @Column(name = "CREATED_AT")
  private Date createdAt;

  @Column(name = "UPDATED_AT")
  private Date updatedAt;

  @Column(name = "IDPTMCR", length = 150)
  private String idptmcr;

  @Column(name = "KINH_DO")
  private Double kinhDo;

  @Column(name = "KINH_DOCR")
  private Double kinhDocr;

  @Column(name = "LOAI_THIET_BI_RAN", length = 50)
  private String loaiThietBiRan;

  @Column(name = "NGAYCR", length = 20)
  private String ngaycr;

  @Column(name = "TINHCR", length = 150)
  private String tinhcr;

  @Column(name = "VI_DO")
  private Double viDo;

  @Column(name = "VI_DOCR")
  private Double viDocr;

  @Column(name = "TON_TAI", length = 200)
  private String tonTai;

  @Column(name = "TRAM_MAIN", length = 200)
  private String tramMain;

  @Column(name = "TOQL", length = 150)
  private String toql;

  @Column(name = "HUYEN", length = 150)
  private String huyen;

  @Column(name = "VENDOR", length = 200)
  private String vendor;

  @Column(name = "SITE_IDCR")
  private String siteIdcr;

  @Column(name = "THUOC_DU_AN")
  private String thuocDuAn;

  @Column(name = "TYPE_EMAIL", length = 5)
  private String typeEmail;

  @Column(name = "ORIGNAL_ID", length = 5)
  private String orignalId;

}
