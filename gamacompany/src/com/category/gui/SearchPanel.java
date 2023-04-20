package com.category.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel {

	private static final long serialVersionUID = 5620795540386996759L;

	public SearchPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		JTextField text1 = new JTextField();
		JTextField text2 = new JTextField();
		JTextField text3 = new JTextField();

		panel.add(new JLabel("이름"));
		panel.add(text1);

		panel.add(new JLabel("주소"));
		panel.add(text2);

		panel.add(new JLabel("전화번호"));
		panel.add(text3);

		add(panel, BorderLayout.CENTER);
		add(new Button("입력"), BorderLayout.SOUTH);
	}
}