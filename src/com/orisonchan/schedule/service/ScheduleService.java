package com.orisonchan.schedule.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.orisonchan.schedule.bean.Schedule;
import com.orisonchan.schedule.vo.ScheduleVO;

public interface ScheduleService {
	
	public Integer addschedule(Timestamp start_time,Timestamp end_time,String title,String content,int userid,int classiId);
	public int updateschedule(Schedule s);
	public ArrayList<ScheduleVO> queryAllByuserId(int id);
	public void delete(int id);
	public int CountByUserId(int userId);
	public int TodayScheduleCount(int userId);
	public ArrayList<ScheduleVO> TodaySchedule(int userId);
	public ArrayList<ScheduleVO> findByUserIdNClassId(int userId,int classiId);
	public ScheduleVO findById(java.lang.Integer id);
}
