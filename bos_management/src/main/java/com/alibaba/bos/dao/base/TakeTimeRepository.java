package com.alibaba.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alibaba.bos.domain.base.TakeTime;

public interface TakeTimeRepository extends JpaRepository<TakeTime, Integer> {

}
