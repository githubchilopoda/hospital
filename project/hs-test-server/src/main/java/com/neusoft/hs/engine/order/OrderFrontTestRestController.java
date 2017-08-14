package com.neusoft.hs.engine.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.hs.domain.order.Order;
import com.neusoft.hs.platform.exception.HsException;
import com.neusoft.hs.platform.util.DateUtil;

@RestController
public class OrderFrontTestRestController {

	@Autowired
	private OrderFacade orderFacade;

	@RequestMapping(method = RequestMethod.GET, value = "/test/order/create")
	public List<OrderDTO> create() throws HsException {

		CreateOrderDTO createOrderDTO = new CreateOrderDTO();

		createOrderDTO.setVisitCardNumber("ttt");
		createOrderDTO.setPlaceType(Order.PlaceType_InPatient);
		createOrderDTO.setLong(false);

		CreateOrderItemDTO createOrderItemDTO = new CreateOrderItemDTO();
		createOrderItemDTO.setOrderTypeId("drugOrderType001");
		createOrderItemDTO.setCount(2);

		createOrderDTO.addItem(createOrderItemDTO);

		createOrderDTO.setDrugUseModeId("oralOrderUseMode");
		createOrderDTO.setPlanStartDate(DateUtil.getSysDate());
		
		createOrderDTO.setExecuteDeptId("deptccc");

		createOrderDTO.setCreator("doctor002");

		return orderFacade.create(createOrderDTO);
	}
}
