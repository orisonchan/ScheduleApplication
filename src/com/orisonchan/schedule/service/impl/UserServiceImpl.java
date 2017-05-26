package com.orisonchan.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orisonchan.schedule.bean.User;
import com.orisonchan.schedule.dao.UserDAO;
import com.orisonchan.schedule.service.UserService;

/**
 * @author Orison Chan
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO usersDAO;
	
	@Override
	public Integer validLogin(String username, String password) {
		try {
			User user = usersDAO.findUserByNameAndPass(username, password);
			if (user != null) {
				return new Integer(user.getId());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Integer signUp(String username, String password, String gender,
			int age) {
		return usersDAO.save(new User(username, password, gender, age));
	}

	@Override
	public User getUserInfo(int id) {
		
		return usersDAO.findById(id);
	}

	@Override
	public void update(User user) {
		usersDAO.update(user);
	}
}
