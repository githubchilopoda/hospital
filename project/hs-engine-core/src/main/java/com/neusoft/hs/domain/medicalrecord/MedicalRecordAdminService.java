package com.neusoft.hs.domain.medicalrecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MedicalRecordAdminService {

	@Autowired
	private MedicalRecordClipRepo medicalRecordClipRepo;

	@Autowired
	private MedicalRecordTypeRepo medicalRecordTypeRepo;

	@Autowired
	private MedicalRecordRepo medicalRecordRepo;

	@Autowired
	private MedicalRecordTypeBuilderRepo medicalRecordTypeBuilderRepo;

	@Autowired
	private MedicalRecordRenderRepo medicalRecordRenderRepo;

	public void createMedicalRecordType(MedicalRecordType type) {
		medicalRecordTypeRepo.save(type);
	}

	public void createMedicalRecordTypes(
			List<MedicalRecordType> medicalRecordTypes) {
		medicalRecordTypeRepo.save(medicalRecordTypes);
	}

	public MedicalRecordType getMedicalRecordType(String id) {
		return medicalRecordTypeRepo.findOne(id);
	}

	public void clear() {
		//清空病历
		this.clearMedicalRecord();
		// 清空病历渲染器
		this.clearRender();
		// 清空病历类型
		this.clearType();
	}

	public void clearRender() {
		medicalRecordRenderRepo.clear();
	}

	public void clearClip() {
		medicalRecordClipRepo.deleteAll();
	}

	public void clearMedicalRecord() {
		medicalRecordRepo.deleteAll();
	}

	public void clearType() {
		medicalRecordTypeRepo.deleteAll();
	}
}
