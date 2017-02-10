//Source file: F:\\my_workspace\\201611������ҽ�������\\DesignModel\\DesignElement\\domain\\organization\\Doctor.java

package com.neusoft.hs.domain.organization;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.neusoft.hs.domain.order.Order;
import com.neusoft.hs.domain.visit.Visit;

@Entity
@DiscriminatorValue("Doctor")
public class Doctor extends AbstractUser {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	private InPatientDept dept;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "superior_id")
	private Doctor superior;

	@OneToMany(mappedBy = "superior", cascade = { CascadeType.REFRESH })
	private List<Doctor> subordinates;

	@OneToMany(mappedBy = "respDoctor", cascade = { CascadeType.REFRESH })
	private List<Visit> visits;

	@OneToMany(mappedBy = "creator", cascade = { CascadeType.REFRESH })
	@OrderBy("createDate DESC")
	private List<Order> orders;

	@Override
	public String getDeptId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDeptId(String deptId) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getOrgId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrgId(String orgId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getRoleIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRoleIds(List<String> roleIds) {
		// TODO Auto-generated method stub

	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Doctor getSuperior() {
		return superior;
	}

	public void setSuperior(Doctor superior) {
		this.superior = superior;
	}

	public List<Doctor> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Doctor> subordinates) {
		this.subordinates = subordinates;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public InPatientDept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = (InPatientDept) dept;
	}

}