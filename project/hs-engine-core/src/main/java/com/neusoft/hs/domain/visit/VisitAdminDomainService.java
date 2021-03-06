package com.neusoft.hs.domain.visit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.hs.platform.log.LogUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class VisitAdminDomainService {

	@Autowired
	private VisitRepo visitRepo;

	@Autowired
	private VisitLogRepo visitLogRepo;

	@Autowired
	private ApplicationContext applicationContext;

	public void clear() {
		visitRepo.deleteAll();
	}

	/**
	 * @param visitId
	 * @roseuid 584E03140020
	 */
	public Visit find(String visitId) {
		return visitRepo.findOne(visitId);
	}

	public List<Visit> findByCardNumber(String cardNumber) {
		return this.visitRepo.findByCardNumber(cardNumber);
	}

	public List<Visit> find(Pageable pageable) {
		return visitRepo.findAll(pageable).getContent();
	}

	public List<VisitLog> getVisitLogs(Visit visit, Pageable pageable) {
		return visitLogRepo.findByVisit(visit, pageable);
	}

	public void delete(String visitId) {
		Visit visit = this.visitRepo.findOne(visitId);
		if (visit != null) {

			applicationContext.publishEvent(new VisitRemoveBeforeEvent(visit));

			visit.delete();

			applicationContext.publishEvent(new VisitRemovedEvent(visit));

			LogUtil.log(this.getClass(), "患者一次就诊[{}][{}]被删除", visit.getId(), visit.getName());
		}
	}
}
