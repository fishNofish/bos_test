package com.alibaba.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.bos.domain.base.Standard;

public interface StandardService {
		public void save(Standard standard);
		public Page<Standard> finfByPage(Pageable pageable);
		public List<Standard> findAll();
}
