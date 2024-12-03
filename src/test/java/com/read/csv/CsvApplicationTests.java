package com.read.csv;

import com.read.csv.importExcel.service.SiteIdLoaiRanService;
import com.read.csv.importExcel.service.TrienKhaiThietBiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.Map;

@SpringBootTest
class CsvApplicationTests {
	@Autowired
	SiteIdLoaiRanService excelService;

	@Autowired
	TrienKhaiThietBiService trienKhaiThietBiService;

	@Test
	void excelServiceTest() {
		Map<String, String[]> ranPathMap = Map.of(
			"3G", new String[]{"CELL_3G_DAY.csv"},
			"4G", new String[]{"CELL_4G_DAY_300824.csv", "CELL_4G_DAY_181024.csv"},
			"5G", new String[]{"CELL_5G_DAY.csv"}
		);

		ranPathMap.forEach((key, value) -> {
      try {
				for (String path : value) {
        	excelService.processFile(path, key);
				}
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
	}

	@Test
	void trienKhaiThietBiServiceTest() {
		trienKhaiThietBiService.updateNgayOnAir();
	}
}
