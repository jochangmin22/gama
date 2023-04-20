package com.category.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.category.cate.Category;
import com.category.common.HttpAPI;
import com.category.log.LogPanel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ExcelService implements Runnable {

	private File file;
	private List<Category> categorys;

	public ExcelService(final File file, List<Category> categorys) {
		this.file = file;
		this.categorys = categorys;
	}

	@Override
	public void run() {
		LogPanel.append("Excel download start : " + file);
		ExcelWriter writer = null;
		try {
			writer = new ExcelWriter(new FileOutputStream(file));
			writer.init();

			createHeader(writer);
			for (int x = 0; x < categorys.size(); x++) {
				Category category = categorys.get(x);
				JSONArray result = HttpAPI.getCategoryByShoppingResult(getParam(category.getCatId()));
				for (int i = 0; i < result.size(); i++) {
					JSONObject obj = result.getJSONObject(i);
					List<Object> data = new ArrayList<>();
					data.add(obj.get("productTitle"));
					data.add(obj.get("price"));

					writer.add(data);
				}
			}

			JOptionPane.showMessageDialog(null, "다운로드가 완료 되었습니다.");
			LogPanel.append("Excel download End");
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (writer != null) writer.close();
		}
	}

	/**
	 * 쇼핑 사이트 검색을 위한 파라미터 값 추출
	 * 
	 * @return
	 */
	public Map<String, String> getParam(final String catId) {
		Map<String, String> param = new HashMap<>();
		param.put("catId", catId);
		param.put("frm", "NVSHCAT");
		param.put("isOpened", "true");
		param.put("origQuery", "");
		param.put("pagingIndex", "1");
		param.put("pagingSize", "40");
		param.put("productSet", "total");
		param.put("query", "review");
		param.put("sort", "rel");
		param.put("viewType", "list");
		param.put("timestamp", "");
		return param;
	}

	public void createHeader(ExcelWriter writer) {
		List<String> headers = new ArrayList<>();
		headers.add("순번");
		headers.add("대분류");
		headers.add("중분류");
		headers.add("소분류");
		headers.add("세분류");
		headers.add("카테고리 id");
		headers.add("순위");
		headers.add("판매유형");
		headers.add("제조국가");
		headers.add("상품가");
		headers.add("등록일");
		headers.add("상품명");
		writer.createHeader(headers);
	}
}
