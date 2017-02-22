package com.neusoft.hs.domain.treatment;

import java.util.Date;
import java.util.List;

import com.neusoft.hs.domain.organization.AbstractUser;
import com.neusoft.hs.domain.visit.Visit;

public interface Itemable {

	public String getName();

	public List<? extends ItemValue> getValues();

	public void save();

	public Visit getVisit();

	public Date getCreateDate();

	public void setCreateDate(Date date);

	public AbstractUser getCreator();

	public void setCreator(AbstractUser user);
}