package com.neusoft.hs.portal.swing.ui.forms.medicalrecord.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.neusoft.hs.domain.medicalrecord.MedicalRecordOperationEvent;
import com.neusoft.hs.domain.visit.Visit;
import com.neusoft.hs.portal.swing.ui.shared.model.MedicalRecordTableModel;
import com.neusoft.hs.portal.swing.ui.shared.model.VisitComboBoxModel;
import com.neusoft.hs.portal.swing.ui.shared.view.MedicalRecordListPanel;
import com.neusoft.hs.portal.swing.util.ConstMessagesCN;

@Component
public class CreateMedicalRecordFrame extends JFrame {

	private JComboBox<Visit> visitCB;
	private VisitComboBoxModel visitComboBoxModel;

	private MedicalRecordListPanel medicalRecordListPanel;

	private JButton createOutPatientRecordBtn;

	private JButton closeBtn;

	private static final int DEFAULT_WIDTH = 800;

	private static final int DEFAULT_HEIGHT = 300;

	@Autowired
	public CreateMedicalRecordFrame() {
		this.medicalRecordListPanel = new MedicalRecordListPanel();

		setFrameUp();
		initComponents();
	}

	private void setFrameUp() {
		setTitle(ConstMessagesCN.Labels.ArrangementMedicalRecord);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel workspacePanel = new JPanel(new BorderLayout());

		JPanel interactivePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		visitComboBoxModel = new VisitComboBoxModel();
		visitCB = new JComboBox<>(visitComboBoxModel);

		interactivePanel.add(visitCB);

		createOutPatientRecordBtn = new JButton(
				ConstMessagesCN.Labels.createOutPatientRecordMR);

		interactivePanel.add(createOutPatientRecordBtn);

		workspacePanel.add(interactivePanel, BorderLayout.NORTH);

		workspacePanel.add(medicalRecordListPanel, BorderLayout.CENTER);

		add(workspacePanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();

		closeBtn = new JButton(ConstMessagesCN.Labels.CLOSE_BTN);
		buttonPanel.add(closeBtn);

		add(buttonPanel, BorderLayout.SOUTH);

	}

	public JComboBox<Visit> getVisitCB() {
		return visitCB;
	}

	public VisitComboBoxModel getVisitComboBoxModel() {
		return visitComboBoxModel;
	}

	public MedicalRecordTableModel getMedicalRecordTableModel() {
		return medicalRecordListPanel.getMedicalRecordTableModel();
	}

	public JTable getTable() {
		return medicalRecordListPanel.getTable();
	}

	public JButton getCreateOutPatientRecordBtn() {
		return createOutPatientRecordBtn;
	}

	public JButton getCloseBtn() {
		return closeBtn;
	}
}
