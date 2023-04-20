package com.category.common;

import java.io.File;
import java.io.IOException;

public class FileRenamePolicy {

	public File rename(File f) {
		if (createNewFile(f)) return f;

		String name = f.getName();
		String body = null;
		String ext = null;

		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			body = name.substring(0, dot);
			ext = name.substring(dot);
		} else {
			body = name;
			ext = "";
		}

		int count = 0;
		while (!createNewFile(f) && count < 999999) {
			count++;
			String newName = body + "_" + count + ext;
			f = new File(f.getParent(), newName);
		}
		return f;
	}

	private boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		} catch (IOException e) {
			return false;
		}
	}
}