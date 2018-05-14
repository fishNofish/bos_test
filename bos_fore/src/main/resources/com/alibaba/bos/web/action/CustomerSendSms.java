package com.alibaba.bos.web.action;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/")
public class CustomerSendSms extends BaseAction<Customer> {
		@Action(value="customer_sendSms")
		public void SendSms(){
			//生成验证码
			String random = RandomStringUtils.randomNumeric(4);
			//输出验证码
			System.out.println("验证码为:"+random);
			//将验证码保存到session中
			
		}
}

	