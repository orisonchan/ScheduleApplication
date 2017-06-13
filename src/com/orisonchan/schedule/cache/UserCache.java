package com.orisonchan.schedule.cache;

import java.util.ArrayList;

import com.orisonchan.schedule.bean.User;

public class UserCache {

	public static ArrayList<User> userList;

	public void init() {
		userList = new ArrayList<User>();
	}

	public void put(User user) {
		userList.add(user);
	}

	public boolean find(User user) {
		for (User u : userList)
			if (u.getId() == user.getId())
				return true;
		return false;
	}

	public void remove(User user) {
		for (User u : userList)
			if (u.getId() == user.getId()) {
				userList.remove(u);
				break;
			}
	}

}
