package com.category.file;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	public File fileSaveDlg() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Microsoft Excel File", "xlsx"));
		fileChooser.setDialogTitle("쇼핑 사이트 분석 - 엑셀 저장 경로 선택");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int userSelection = fileChooser.showSaveDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}
}
