package com.neusoft.hs.engine.order;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.hs.domain.order.OrderExecute;
import com.neusoft.hs.domain.orderexecute.OrderExecuteAppService;
import com.neusoft.hs.domain.organization.AbstractUser;
import com.neusoft.hs.domain.organization.UserAdminDomainService;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderExecuteFacadeImpl implements OrderExecuteFacade {

	@Autowired
	private OrderExecuteAppService orderExecuteAppService;

	@Autowired
	private UserAdminDomainService userAdminDomainService;

	@Autowired
	private OrderExecuteDTOUtil orderExecuteDTOUtil;

	@Override
	public List<OrderExecuteDTO> findNeedExecute(String userId, Integer pageNumber, Integer pageSize)
			throws OrderExecuteDTOException {
		AbstractUser user = userAdminDomainService.find(userId);
		if (user == null) {
			throw new OrderExecuteDTOException(null, "用户userId=[%s]不存在", userId);
		}
		Sort sort = new Sort(Direction.DESC, "planStartDate");
		Pageable pageable = new PageRequest(pageNumber, pageSize, sort);

		List<OrderExecute> executes = orderExecuteAppService.findNeedExecuteOrderExecutes(user, pageable);

		List<OrderExecuteDTO> executeDTOs = new ArrayList<OrderExecuteDTO>();
		try {
			for (OrderExecute execute : executes) {
				executeDTOs.add(orderExecuteDTOUtil.convert(execute));
			}
			return executeDTOs;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new OrderExecuteDTOException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new OrderExecuteDTOException(e);
		}
	}

	@Override
	public OrderExecuteDTO finish(String executeId, String userId) throws OrderExecuteDTOException {
		// TODO Auto-generated method stub
		return null;
	}

}
