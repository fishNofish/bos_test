package com.alibaba.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.alibaba.bos.domain.base.Courier;

public interface CourierRepository extends JpaRepository<Courier, Integer>,
																			JpaSpecificationExecutor<Courier>{
	//将快递员失效
	@Query(value = "update Courier set deltag='1' where id = ?")
	@Modifying
	public void updateDelTag(Integer id);
	//将失效快递员还原
	@Query(value = "update Courier set deltag='' where id = ?")
	@Modifying
	public void restore(Integer id);

}
