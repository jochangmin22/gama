package com.category;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AutoDialog extends JDialog {

	private JSpinner num;

	public int getNum() {
		return (int) num.getValue();
	}


	public AutoDialog() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		getContentPane().setLayout(null);

		setType(Type.POPUP);
		setMinimumSize(new Dimension(300, 170));
		setIconImage(Toolkit.getDefaultToolkit().getImage(AutoDialog.class.getResource("/com/category/resource/category.png")));
		setModal(true);
		setTitle("자동생성 건수 입력");
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("확 인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(79, 74, 117, 33);
		getContentPane().add(btnNewButton);

		JLabel lblNewLabel1 = new JLabel("자동생성 건수를 입력하세요.");
		lblNewLabel1.setBounds(12, 10, 156, 15);
		getContentPane().add(lblNewLabel1);

		num = new JSpinner();
		num.setBounds(85, 35, 100, 22);
		getContentPane().add(num);
		num.setPreferredSize(new Dimension(100, 22));
		num.setMinimumSize(new Dimension(100, 22));
	}

	private static final long serialVersionUID = 1L;
}
