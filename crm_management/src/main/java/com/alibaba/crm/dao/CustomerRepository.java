package com.alibaba.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.alibaba.crm.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public List<Customer> findByFixedAreaIdIsNull();

	public List<Customer> findByFixedAreaId(String fixedAreaId);

	@Query("update Customer set fixedAreaId = ? where id = ?")
	@Modifying
	public void updateFixedAreaId(String fixedAreaId, Integer id);
	@Query("update Customer set fixedAreaId = null where fixedAreaId = ?")
	@Modifying
	public void clearFixedAreaId(String fixedAreaId);

	

}