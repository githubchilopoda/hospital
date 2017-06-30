package com.neusoft.hs.portal.swing.business.medicalrecord;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import com.neusoft.hs.domain.medicalrecord.MedicalRecord;
import com.neusoft.hs.platform.exception.HsException;
import com.neusoft.hs.platform.util.DateUtil;
import com.neusoft.hs.portal.swing.util.ConstMessagesCN;

public class TemporaryOrderListFrame extends JFrame {

	private static final int DEFAULT_WIDTH = 400;

	private static final int DEFAULT_HEIGHT = 600;

	private MedicalRecord medicalRecord;

	public TemporaryOrderListFrame(MedicalRecord medicalRecord) {

		this.medicalRecord = medicalRecord;

		setFrameUp();
		initComponents();
	}

	private void setFrameUp() {
		setTitle(ConstMessagesCN.Labels.InitBlanace);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void initComponents() {

		JPanel contentPanel = new JPanel(new BorderLayout());

		JPanel titlePanel = new JPanel(new GridLayout(2, 1));

		JLabel name = new JLabel(medicalRecord.getType().getName());
		titlePanel.add(name);

		JPanel subtitlePanel = new JPanel(new GridLayout(1, 2));

		subtitlePanel.add(new JLabel(medicalRecord.getVisitName()));
		try {
			subtitlePanel.add(new JLabel(DateUtil.toString(medicalRecord
					.getCreateDate())));
		} catch (HsException e) {
			e.printStackTrace();
		}

		titlePanel.add(subtitlePanel);

		contentPanel.add(titlePanel, BorderLayout.NORTH);

		medicalRecord.getItems();

		JTable table = new JTable(null);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane paneWithTable = new JScrollPane(table);

		contentPanel.add(paneWithTable, BorderLayout.CENTER);

		add(contentPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();

		JButton closeBtn = new JButton(ConstMessagesCN.Labels.CLOSE_BTN);
		buttonPanel.add(closeBtn);

		add(buttonPanel, BorderLayout.SOUTH);
	}

}