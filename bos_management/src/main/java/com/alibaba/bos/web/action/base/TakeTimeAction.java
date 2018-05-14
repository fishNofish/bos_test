package com.alibaba.bos.web.action.base;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.bos.domain.base.TakeTime;
import com.alibaba.bos.service.base.TakeTimeService;
import com.alibaba.bos.web.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;
@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("propotype")
public class TakeTimeAction extends BaseAction<TakeTime>{
	@Autowired
	private TakeTimeService takeTimeService;
	@Action(value="taketime_findAll",results={@Result(name="success",type="json")})
	public String findAll(){
		List<TakeTime> result= takeTimeService.findAll();
		ActionContext.getContext().getValueStack().push(result);
		return SUCCESS;
	}
}
