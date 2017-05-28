package com.orisonchan.schedule.service;

import java.util.List;

import com.orisonchan.schedule.bean.Classi;

public interface ClassiService {

	public Integer add(String name, int parentId, int level, int userId);

	public int CountByUserId(int id);

	public List<Classi> queryAllByuserId(int id);

	public int queryCreateWithinOneWeekClass(Integer id);
	
	public Classi queryById(int id);

}
