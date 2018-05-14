package com.alibaba.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.crm.dao.CustomerRepository;
import com.alibaba.crm.domain.Customer;
import com.alibaba.crm.service.CustomerService;



@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	// 注入DAO
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> findByAssociationCustomers() {
		
		return customerRepository.findByFixedAreaIdIsNull();
	}

	@Override
	public List<Customer> findHasAssociationCustomers(String fixedAreaId) {
		
		return customerRepository.findByFixedAreaId(fixedAreaId);
	}

	@Override
	public void associationCustomerToFixArea(String customerIdStr,
			String fixedAreaId) {
		//清空选中的fixedAreaId下已绑定的客户
		customerRepository.clearFixedAreaId(fixedAreaId);
		//判断customerIdStr是否为空
		if("null".equals(customerIdStr)){
			return;
		}	
		//将customerIdStr字符串分割,并传递到后台
		String[] customerIdArrry = customerIdStr.split(",");
		for (String string : customerIdArrry) {
			int id = Integer.parseInt(string);
			customerRepository.updateFixedAreaId(fixedAreaId, id);
		}
	}

	

}
