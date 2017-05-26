package com.orisonchan.schedule.service;

import com.orisonchan.schedule.bean.User;

public interface UserService {
	Integer validLogin(String username,String password);
	Integer signUp(String username,String password,String gender,int age);
	User getUserInfo(int id);
	void update(User user);
}
