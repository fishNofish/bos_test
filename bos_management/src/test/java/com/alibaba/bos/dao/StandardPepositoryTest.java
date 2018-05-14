package com.alibaba.bos.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.bos.dao.base.StandardRepository;
import com.alibaba.bos.domain.base.Standard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class StandardPepositoryTest {
		//注入standardRepository
		@Autowired
		private StandardRepository standardRepository;
		@Test
		public void findByName(){
			List<Standard> name = standardRepository.findByName("10-20公斤");
			System.out.println(name);
		}
		@Test
		public void findName(){
			System.out.println(standardRepository.findName("10-20公斤"));
		}
		
		@Test
		@Transactional
		@Rollback(false)
		public void updateMinLength(){
			standardRepository.updateMinLength(1, 15);
		}
		
		
		
		
}
