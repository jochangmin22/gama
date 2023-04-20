package com.category.log;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.apache.commons.lang.RandomStringUtils;

import com.category.common.DateUtil;

public class LogPanel {

	public static final JTextPane logText = new JTextPane();

	public JTextPane render() {
		logText.setForeground(Color.WHITE);
		logText.setFont(new Font("Gulim", Font.PLAIN, 12));
		logText.setBackground(Color.BLACK);
		logText.setCaretColor(Color.WHITE); // 백색 커서 설정
		return logText;
	}

	public static String random() {
		long unixTime = System.currentTimeMillis();
		StringBuilder b = new StringBuilder(RandomStringUtils.random(8, true, true).toUpperCase() + Long.toHexString(unixTime).toUpperCase());
		for (int i = b.length() - 1; i > 0; i--) {
			if (i % 4 == 0) {
				b.insert(i, "-");
			}
		}
		return b.toString();
	}

	public static void main(String[] args) {
		System.out.println(random());
	}

	public static void append(final String s) {
		try {
			final String message = String.format("[%s] | %s", DateUtil.getCurrentTime(), s);
			Document doc = logText.getDocument();
			doc.insertString(doc.getLength(), message + "\r\n", null);
			System.out.println(message);
			logText.setCaretPosition(logText.getDocument().getLength());
		} catch (BadLocationException exc) {
			exc.printStackTrace();
		}
	}
}