package com.alibaba.bos.web.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.alibaba.bos.domain.base.Courier;
import com.alibaba.bos.domain.base.Standard;
import com.alibaba.bos.service.base.CourierService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class CourierAction extends ActionSupport implements ModelDriven<Courier> {
		//模型驱动
	private Courier courier=new Courier();
		//注入courierService
		@Autowired
		private CourierService courierService;
		@Override
	public Courier getModel() {
		return courier;
	}
	//注解方式进行保存
	@Action(value="courier_save",results={@Result(name="success",location="./pages/base/courier.html",type="redirect")})
	public String courieSave(){
			courierService.save(courier);		
		return SUCCESS;		
	}	
	//属性注入page,rows
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	@Action(value="courier_pageQuery",results={@Result(name="success",type="json")})
	public String pageQuery(){
		//对数据进行分装查询,并接收返回值
		Pageable pageAble=new PageRequest(page-1, rows);
		// 封装条件查询对象 Specification
				Specification<Courier> specification = new Specification<Courier>() {
					@Override
					// Root 用于获取属性字段，CriteriaQuery可以用于简单条件查询，CriteriaBuilder 用于构造复杂条件查询
					public Predicate toPredicate(Root<Courier> root,
							CriteriaQuery<?> query, CriteriaBuilder cb) {
						List<Predicate> list = new ArrayList<Predicate>();

						// 简单单表查询
						if (StringUtils.isNotBlank(courier.getCourierNum())) {
							Predicate p1 = cb.equal(
									root.get("courierNum").as(String.class),
									courier.getCourierNum());
							list.add(p1);
						}
						if (StringUtils.isNotBlank(courier.getCompany())) {
							Predicate p2 = cb.like(
									root.get("company").as(String.class),
									"%" + courier.getCompany() + "%");
							list.add(p2);
						}
						if (StringUtils.isNotBlank(courier.getType())) {
							Predicate p3 = cb.equal(root.get("type").as(String.class),
									courier.getType());
							list.add(p3);
						}
						// 多表查询
						Join<Courier, Standard> standardJoin = root.join("standard",
								JoinType.INNER);
						if (courier.getStandard() != null
								&& StringUtils.isNotBlank(courier.getStandard()
										.getName())) {
							Predicate p4 = cb.like(
									standardJoin.get("name").as(String.class), "%"
											+ courier.getStandard().getName() + "%");
							list.add(p4);
						}
						return cb.and(list.toArray(new Predicate[0]));
					}
				};
		//对返回值进行处理,并返回到前台
		Page<Courier> page= courierService.pageQuery(specification,pageAble);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("total", page.getTotalElements());
		map.put("rows", page.getContent());
		//将map装换成json
		ActionContext.getContext().getValueStack().push(map);
		return SUCCESS;
	}
	//属性注入ids
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}
	//将快递员失效配置
	@Action(value="courier_delect",results={@Result(name="success",location="./pages/base/courier.html",type="redirect")})
	public String courierDelect(){
			String[] idArray = ids.split(",");
			courierService.delect(idArray);
		return SUCCESS;
	}
	//将快递员还原
	@Action(value="courier_restore",results={@Result(name="success",location="./pages/base/courier.html",type="redirect")})
	public String courierRestore(){
			String[] idArray = ids.split(",");
			courierService.restore(idArray);
		return SUCCESS;
	}
	@Action(value="courier_findnoassociation",results={@Result(name="success",type="json")})
	public String findNoAssociation(){
		List<Courier> couries=  courierService.findnoassociation();
		ActionContext.getContext().getValueStack().push(couries);		
		return SUCCESS;
	}
	
	
	
	
	

}
