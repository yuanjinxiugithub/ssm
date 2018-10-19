package com.ssm.dao;


import org.springframework.stereotype.Repository;

import com.ssm.domain.LogInfo;

@Repository
public interface LogDao {
  public void insert(LogInfo entity);
}
