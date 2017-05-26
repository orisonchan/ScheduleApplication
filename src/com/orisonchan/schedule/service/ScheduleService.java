package com.orisonchan.schedule.service;

import java.sql.Timestamp;
import java.util.List;

import com.orisonchan.schedule.bean.Schedule;

public interface ScheduleService {
	
	public Integer addschedule(Timestamp start_time,Timestamp end_time,String title,String content,int userid,int classiId);
	public int updateschedule(Schedule s);
	@SuppressWarnings("rawtypes")
	public List queryAllByuserId(int id);
	public void delete(int id);
	public int CountByUserId(int userId);
	public int TodayScheduleCount(int userId);
	@SuppressWarnings("rawtypes")
	public List TodaySchedule(int userId);
	@SuppressWarnings("rawtypes")
	public List findByUserIdNClassId(int userId,int classiId);
	public Schedule findById(java.lang.Integer id);
}
