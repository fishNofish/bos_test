package com.alibaba.bos.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.alibaba.bos.domain.base.FixedArea;

public interface FixedAreaService {

	void save(FixedArea model);

	Page<FixedArea> areaSearch(Specification specification, Pageable pageable);

	void associationCourierToFixedArea(FixedArea model, Integer courierId,
			Integer takeTimeId);
		
}
