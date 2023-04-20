package com.category.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Form extends JPanel {

	private static final long serialVersionUID = -8426468050028008249L;

	public Form() {
		
		JPanel searchPanel = new JPanel();
		JLabel searchLabel = new JLabel("검색어:");
		JTextField searchField = new JTextField(20);
		searchPanel.add(searchLabel);
		searchPanel.add(searchField);

		JPanel contentPanel = new JPanel();
		JTextArea contentArea = new JTextArea(10, 30);
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		JScrollPane contentScroll = new JScrollPane(contentArea);
		contentPanel.add(contentScroll);
		
		JPanel listPanel = new JPanel();
		JLabel listLabel = new JLabel("목록:");
		String[] items = {"항목1", "항목2", "항목3", "항목4", "항목5"};
		JList<String> itemList = new JList<String>(items);
		JScrollPane listScroll = new JScrollPane(itemList);
		listPanel.add(listLabel);
		listPanel.add(listScroll);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(searchPanel, BorderLayout.NORTH);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(listPanel, BorderLayout.SOUTH);
		
		add(mainPanel);
		
		
		
		
		
		
//		
//		setBorder(new EmptyBorder(8, 8, 8, 8));
//		setLayout(new GridBagLayout());
//
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.fill = GridBagConstraints.BOTH;
//		gbc.weightx = 1;
//
//		SearchPanel searchConditionPanel = new SearchPanel();
//		searchConditionPanel.setBorder(new CompoundBorder(new TitledBorder(""), new EmptyBorder(4, 4, 4, 4)));
//		add(searchConditionPanel, gbc);
//
//		gbc.gridx = 0;
//		gbc.gridy++;
//		gbc.fill = GridBagConstraints.BOTH;
//
//		LogPanel logPane = new LogPanel();
//		logPane.setBorder(new CompoundBorder(new TitledBorder("로그"), new EmptyBorder(4, 4, 4, 4)));
//		add(logPane, gbc);

	}

	public class SearchPanel extends JPanel {
		private static final long serialVersionUID = -2303945683932966888L;

		public SearchPanel() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.EAST;

			add(new JLabel("First Name:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Last Name:"), gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			add(new JLabel("Title:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Nickname:"), gbc);

			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 0.5;
			add(new JTextField(10), gbc);
			gbc.gridx += 2;
			add(new JTextField(10), gbc);
			gbc.gridy++;
			gbc.gridx = 1;
			add(new JTextField(10), gbc);
			gbc.gridx += 2;
			add(new JTextField(10), gbc);

			gbc.gridx = 0;
			gbc.gridy++;
			gbc.anchor = GridBagConstraints.EAST;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			add(new JLabel("Format:"), gbc);

			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx++;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			add(new JComboBox(), gbc);
		}
	}

	protected class LogPanel extends JPanel {
		private static final long serialVersionUID = -3968031585223178176L;

		public LogPanel() {
			JPanel detailsPane = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.EAST;
			detailsPane.add(new JLabel("E-Mail Address:"), gbc);

			gbc.gridx++;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			detailsPane.add(new JTextField(10), gbc);

			gbc.gridy++;
			gbc.gridx = 0;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.weighty = 1;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			detailsPane.add(new JScrollPane(new JList()), gbc);

			setLayout(new BorderLayout());
			add(detailsPane);
		}
	}
}