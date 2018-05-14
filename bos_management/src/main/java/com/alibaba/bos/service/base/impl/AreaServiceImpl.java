package com.alibaba.bos.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.bos.dao.base.AreaRepository;
import com.alibaba.bos.domain.base.Area;
import com.alibaba.bos.service.base.AreaService;
@Service
@Transactional

public class AreaServiceImpl implements AreaService {

	//注入dao
	@Autowired
	private AreaRepository areaRepository;
	@Override
	public void fileUpload(List<Area> list) {
		//保存数据
		areaRepository.save(list);
	}
	@Override
	public Page<Area> pageQuery(Specification specification, Pageable pageAble) {
		
		Page<Area> page=areaRepository.findAll(specification, pageAble);
		return page;
	}

}
