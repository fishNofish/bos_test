package com.alibaba.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.alibaba.bos.domain.base.Area;

public interface AreaService {
		public void fileUpload(List<Area> list);

		public Page<Area> pageQuery(Specification specification,
				Pageable pageAble);
}
