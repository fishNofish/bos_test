package com.alibaba.bos.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.bos.dao.base.CourierRepository;
import com.alibaba.bos.dao.base.FixedAreaRepository;
import com.alibaba.bos.dao.base.TakeTimeRepository;
import com.alibaba.bos.domain.base.Courier;
import com.alibaba.bos.domain.base.FixedArea;
import com.alibaba.bos.domain.base.TakeTime;
import com.alibaba.bos.service.base.FixedAreaService;
@Service
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {
	@Autowired
	private FixedAreaRepository fixedAreaRepository;
	@Autowired
	private CourierRepository courierRepository;
	@Autowired
	private TakeTimeRepository takeTimeRepository;
	
	@Override
	public void save(FixedArea model) {
		fixedAreaRepository.save(model);
		
	}
	@Override
	public Page<FixedArea> areaSearch(Specification specification,
			Pageable pageable) {
		
		return fixedAreaRepository.findAll(specification, pageable);
	}
	@Override
	public void associationCourierToFixedArea(FixedArea model,
			Integer courierId, Integer takeTimeId) {
		//先获取各id对应的对象
		FixedArea fixedArea = fixedAreaRepository.findOne(model.getId());
		Courier courier = courierRepository.findOne(courierId);
		TakeTime takeTime = takeTimeRepository.findOne(takeTimeId);
		//将快递员绑定定区
		fixedArea.getCouriers().add(courier);
		//将收派标准绑定到快递员
		courier.setTakeTime(takeTime);		
	}

	

}
