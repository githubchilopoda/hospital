package com.neusoft.hs.domain.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neusoft.hs.domain.pharmacy.DrugTypeConsumeRecord;
import com.neusoft.hs.domain.pharmacy.DrugTypeSpec;
import com.neusoft.hs.domain.pharmacy.Pharmacy;

@Entity
public abstract class DrugOrderExecute extends OrderExecute {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pharmacy_id")
	private Pharmacy pharmacy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "drug_type_spec_id")
	private DrugTypeSpec drugTypeSpec;

	@JsonIgnore
	@OneToMany(mappedBy = "drugOrderExecute", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private List<DrugTypeConsumeRecord> consumeRecords;

	@Override
	protected void calTip() {

		if (this.getDrugTypeSpec() != null) {
			StringBuilder builder = new StringBuilder();

			builder.append(this.getDrugTypeSpec().getName());
			builder.append("(");
			builder.append(this.getCount());
			if (this.getDrugTypeSpec().getChargeItem() != null) {
				builder.append(this.getDrugTypeSpec().getChargeItem().getUnit());
			}
			builder.append(")");

			this.setTip(builder.toString());
		}
	}

	public DrugTypeSpec getDrugTypeSpec() {
		return drugTypeSpec;
	}

	public void setDrugTypeSpec(DrugTypeSpec drugTypeSpec) {
		this.drugTypeSpec = drugTypeSpec;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public List<DrugTypeConsumeRecord> getConsumeRecords() {
		return consumeRecords;
	}

	public void setConsumeRecords(List<DrugTypeConsumeRecord> consumeRecords) {
		this.consumeRecords = consumeRecords;

		for (DrugTypeConsumeRecord record : consumeRecords) {
			record.setDrugOrderExecute(this);
		}
	}
}
