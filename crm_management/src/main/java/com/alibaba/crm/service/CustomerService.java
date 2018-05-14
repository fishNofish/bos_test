package com.alibaba.crm.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.alibaba.crm.domain.Customer;

/**
 * 客户操作
 * 
 * @author itcast
 *
 */
public interface CustomerService {
	//查询未关联的客户
	@Path("/noAssociationCustomers")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Customer> findByAssociationCustomers();
	//查询已关联的客户
	@Path("/hasAssociationCustomers/{fixedAreaId}")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Customer> findHasAssociationCustomers(
			@PathParam("fixedAreaId") String fixedAreaId);
	//将客户关联到定区上,并将所有客户的id拼成字符串
	@Path("/associationCustomerToFixArea")
	@PUT
	public void associationCustomerToFixArea(
			@QueryParam("customerIdStr") String customerIdStr,
			@QueryParam("fixedAreaId") String fixedAreaId);
	
	
	
}
