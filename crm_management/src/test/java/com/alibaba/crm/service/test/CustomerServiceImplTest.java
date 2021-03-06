package com.alibaba.crm.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.crm.service.CustomerService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerServiceImplTest {
	@Autowired
	private CustomerService customerService;
	@Test
	public void testFindByAssociationCustomers() {	
		System.out.println(customerService.findByAssociationCustomers());
	}

	@Test
	public void testFindHasAssociationCustomers() {
		System.out.println("1");
	}

	@Test
	public void testAssociationCustomerToFixArea() {
		customerService.associationCustomerToFixArea("1,2", "dq003");
	}

}
