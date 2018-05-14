package com.alibaba.bos.service.base.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.bos.dao.base.CourierRepository;
import com.alibaba.bos.domain.base.Courier;
import com.alibaba.bos.service.base.CourierService;

@Service
@Transactional
public class CourierServiceImpl implements CourierService {
	//注入dao
	@Autowired
	private CourierRepository courierPepositiry;
	
	//添加取件员
	@Override
	public void save(Courier courier) {
		courierPepositiry.save(courier);
	}
	//条件查询取件员
	@Override
	public Page<Courier> pageQuery(Specification<Courier> specification,Pageable pageAble) {
		Page<Courier> findAll = courierPepositiry.findAll(specification, pageAble);
		return findAll;
	}
	//将取件员失效
	@Override
	public void delect(String[] idArray) {
		for (String string : idArray) {
			try{
				int id = Integer.parseInt(string);
				courierPepositiry.updateDelTag(id);
				System.out.println(id);
			}catch(Exception e){				
			}
		}		
	}
	//将取件员还原
		@Override
		public void restore(String[] idArray) {
			for (String string : idArray) {
				try{
					int id = Integer.parseInt(string);
					courierPepositiry.restore(id);
				}catch(Exception e){				
				}
			}
	}
		//
		@Override
		public List<Courier> findnoassociation() {
			Specification<Courier> specification=new Specification<Courier>() {
				@Override
				public Predicate toPredicate(Root<Courier> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate p=cb.isEmpty(root.get("fixedAreas").as(Set.class));
					return p;
				}
			};
			return courierPepositiry.findAll(specification);
		}
}