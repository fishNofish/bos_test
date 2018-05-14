package com.alibaba.bos.web.action.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.alibaba.bos.domain.base.Standard;
import com.alibaba.bos.service.base.StandardService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class StandardAction extends ActionSupport implements
		ModelDriven<Standard> {

	// 模型驱动
	private Standard standard = new Standard();

	@Override
	public Standard getModel() {
		return standard;
	}

	// 注入Service对象
	@Autowired
	private StandardService standardService;

	// 添加操作
	@Action(value = "standard_save", results = { @Result(name = "success", type = "redirect", location = "./pages/base/standard.html") })
	public String save() {
		System.out.println("添加收派标准....");
		standardService.save(standard);
		return SUCCESS;
	}
	
	//属性驱动
	private int page;
	private int rows;

	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	@Action(value="standard_findByPage",results={@Result(name="success",type="json")})
	public String findByPage(){
		//调用业务层并返回结果
		Pageable pageable=new PageRequest(page-1, rows);
		Page<Standard> pageDate = standardService.finfByPage(pageable);
		//将返回的数据进行封装
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("total",pageDate.getNumberOfElements() );
		result.put("rows", pageDate.getContent());
		//将map转换成json
		ActionContext.getContext().getValueStack().push(result);
		return SUCCESS ;
	}
    @Action(value="standard_findAll",results={@Result(name="success",type="json")})
    public String standardFindAll(){
    	List<Standard> result=standardService.findAll();
    	ActionContext.getContext().getValueStack().push(result);
    	return SUCCESS;   	
    }

}
