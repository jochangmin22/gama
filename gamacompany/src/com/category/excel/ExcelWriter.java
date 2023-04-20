package com.category.excel;

import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.category.common.Common;

public class ExcelWriter {
	private SXSSFWorkbook workbook;
	private Sheet sheet;
	private OutputStream out;

	public ExcelWriter(final OutputStream out) {
		this.out = out;
	}

	public void init() {
		this.workbook = new SXSSFWorkbook(10);
		this.workbook.setCompressTempFiles(false);
		this.sheet = this.workbook.createSheet("naver shop");
	}

	public void createHeader(List<String> headers) {
		Row row = nextRow();
		for (int i = 0; i < headers.size(); i++) {
			row.createCell(i).setCellValue(this.workbook.getCreationHelper().createRichTextString(headers.get(i)));
		}
	}

	public void add(List<Object> data) {
		Row row = nextRow();
		for (int i = 0; i < data.size(); i++) {
			String obj = Common.nvl(data.get(i));
			row.createCell(i).setCellValue(this.workbook.getCreationHelper().createRichTextString(obj));
		}
	}

	private Row nextRow() {
		return this.sheet.createRow(this.sheet.getLastRowNum() + 1);
	}

	public void close() {
		try {
			this.workbook.write(this.out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
			if (this.workbook != null) this.workbook.dispose();
		}
	}
}
