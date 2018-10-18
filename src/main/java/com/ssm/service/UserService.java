package com.ssm.service;

import java.util.List;

import com.ssm.domain.User;

public interface UserService {
	public abstract List<User> findList(); //获取所有的数据
}
