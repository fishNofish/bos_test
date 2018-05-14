package com.alibaba.bos.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.bos.dao.base.CourierRepository;
import com.alibaba.bos.domain.base.Courier;
public interface CourierService {
		public void save(Courier courier);
	
		public Page<Courier> pageQuery(Specification<Courier> specification, Pageable pageAble);

		public void delect(String[] idArray);

		public void restore(String[] idArray);

		public List<Courier> findnoassociation();
}
