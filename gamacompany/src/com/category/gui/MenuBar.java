package com.category.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.category.about.About;

public class MenuBar {

	public JMenuBar createMenuBar() {
		JMenuBar jMenuBar = new JMenuBar();

		JMenu mnFile = new JMenu("File    ");
		jMenuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("종료    ");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Do you want to close?", "쇼핑 사이트 분석",
						JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		mntmExit.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		jMenuBar.add(mnHelp);

		JMenuItem mntmAboutUrlCategory = new JMenuItem("About 쇼핑 사이트 분석");

		mntmAboutUrlCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				About dialog = new About();
				dialog.setResizable(false);
				dialog.init();
				dialog.setVisible(true);
			}
		});
		mntmAboutUrlCategory.setHorizontalAlignment(SwingConstants.LEFT);
		mnHelp.add(mntmAboutUrlCategory);
		return jMenuBar;
	}
}
