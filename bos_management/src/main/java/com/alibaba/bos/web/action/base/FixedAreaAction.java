package com.alibaba.bos.web.action.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.alibaba.bos.domain.base.FixedArea;
import com.alibaba.bos.service.base.FixedAreaService;
import com.alibaba.bos.web.action.BaseAction;
import com.alibaba.crm.domain.Customer;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototye")
@ParentPackage("json-default")
@Namespace("/")
public class FixedAreaAction extends BaseAction<FixedArea> {
	//注入服务层
	@Autowired
	private FixedAreaService fixedAreaService;
	@Action(value="fixedAarea_save",results={@Result(name="success",location="./pages/base/fixed_area.html",type="redirect")})
	public String fixedAareaSave(){
		fixedAreaService.save(model);
		return SUCCESS;
	}
	@Action(value="fixedArea_search",results={@Result(name="success",type="json")})
	public String areaSearch(){
		//进行分页查询
		Pageable pageable=new PageRequest(page-1,rows);
		//构架条件查询
		Specification specification =new Specification<FixedArea>() {
			@Override
			public Predicate toPredicate(Root<FixedArea> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list=new ArrayList<Predicate>();
				//构造查询条件 
				//查询id
				if(StringUtils.isNotBlank(model.getId())){
					Predicate p1=cb.equal(root.get("id").as(String.class), model.getId());
					list.add(p1);
				}
				//查询公司
				if(StringUtils.isNotBlank(model.getCompany())){
					Predicate p2=cb.equal(root.get("company").as(String.class), model.getCompany());
					list.add(p2);
				}		
				return cb.and(list.toArray(new Predicate[0]));
			}
		};
		//进行条件查询,并返回数据
		Page<FixedArea> page=fixedAreaService.areaSearch(specification,pageable);
		//将数据压栈
		pushPageDataToValueStack(page);	
		return SUCCESS;
	}
	
	//查找未分区的客户
	@Action(value="fixedArea_findNoAssociationSelect",results={@Result(name="success",type="json")})
	public String findNoAssociationSelect(){
		Collection<? extends Customer> collection = WebClient.create("http://localhost:9002/services/customerService/noAssociationCustomers")
						.accept(MediaType.APPLICATION_JSON)
						.getCollection(Customer.class);
			ActionContext.getContext().getValueStack().push(collection);
		return SUCCESS;
	}
	
	//查找已分区的客户
		@Action(value="fixedArea_findHasAssociationSelect",results={@Result(name="success",type="json")})
		public String findHasAssociationSelect(){
			// 使用webClient调用 webService接口		
			Collection<? extends Customer> collection = WebClient
					.create("http://localhost:9002/services/customerService/hasAssociationCustomers/"
							+model.getId()).accept(MediaType.APPLICATION_JSON)
					.type(MediaType.APPLICATION_JSON).getCollection(Customer.class);
			ActionContext.getContext().getValueStack().push(collection);
			return SUCCESS;
		}
		// 属性驱动
		private String[] customerIds;

		public void setCustomerIds(String[] customerIds) {
			this.customerIds = customerIds;
		}

		// 关联客户到定区
	@Action(value = "fixedArea_associationCustomersToFixedArea", results = { @Result(name = "success", type = "redirect", location = "./pages/base/fixed_area.html") })
	public String associationCustomersToFixedArea(){	
		String customerIdStr = StringUtils.join(customerIds, ",");
		WebClient
		.create("http://localhost:9002/services/customerService/associationCustomerToFixArea?customerIdStr="+customerIdStr+"&fixedAreaId="+model.getId())
						.put(null);
		return SUCCESS;
	}
	//关联快递员到定区
	//属性驱动
	private Integer courierId;
	private Integer takeTimeId;
	
	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}
	public void setTakeTimeId(Integer takeTimeId) {
		this.takeTimeId = takeTimeId;
	}
	@Action(value="fixedArea_associationCourierToFixedArea",results={@Result(name="success"
			,type="redirect",location="./pages/base/fixed_area.html")})
	public String associationCourierToFixedArea(){
		fixedAreaService.associationCourierToFixedArea(model,courierId,takeTimeId);		
		return SUCCESS;
	}
	
	
	
	
}
