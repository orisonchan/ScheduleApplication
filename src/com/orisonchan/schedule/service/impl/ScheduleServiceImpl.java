package com.orisonchan.schedule.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orisonchan.schedule.bean.Schedule;
import com.orisonchan.schedule.dao.ScheduleDAO;
import com.orisonchan.schedule.service.ClassiService;
import com.orisonchan.schedule.service.ScheduleService;
import com.orisonchan.schedule.vo.ScheduleVO;

/**
 * @author Orison Chan
 */
@Transactional
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDAO scheduleDAO;
	
	@Autowired
	private ClassiService classiService;

	@Override
	public Integer addschedule(Timestamp start_time, Timestamp end_time,
			String title, String content, int userid, int classiId) {
		return scheduleDAO.insertall(start_time, end_time, title, content,
				userid, classiId);
	}


	@Override
	public int updateschedule(Schedule s) {
		scheduleDAO.update(s);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ScheduleVO> queryAllByuserId(int id) {
		List<Schedule> list = scheduleDAO.findByProperty("userId", id);
		ArrayList<ScheduleVO> arraylist = new ArrayList<ScheduleVO>();
		ScheduleVO svo;
		for(Schedule sche:list){
			svo = new ScheduleVO();
			svo = ScheduleVO.tranform(sche,classiService);
			arraylist.add(svo);
		}
		return arraylist;
	}

	@Override
	public void delete(int id) {
		Schedule s = scheduleDAO.findById(id);
		scheduleDAO.delete(s);
	}

	@Override
	public int CountByUserId(int userId) {
		return scheduleDAO.CountByUserId(userId);
	}

	@Override
	public int TodayScheduleCount(int userId) {
		return TodaySchedule(userId).size();
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ScheduleVO> TodaySchedule(int userId) {
		List<Schedule> list = scheduleDAO.TodaySchedule(userId);
		ArrayList<ScheduleVO> arraylist = new ArrayList<ScheduleVO>();
		ScheduleVO svo;
		for(Schedule sche:list){
			svo = new ScheduleVO();
			svo = ScheduleVO.tranform(sche,classiService);
			arraylist.add(svo);
		}
		return arraylist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ScheduleVO> findByUserIdNClassId(int userId, int classiId) {
		List<Schedule> list = scheduleDAO.findByUserIdNClassId(userId, classiId);
		ArrayList<ScheduleVO> arraylist = new ArrayList<ScheduleVO>();
		ScheduleVO svo;
		for(Schedule sche:list){
			svo = new ScheduleVO();
			svo = ScheduleVO.tranform(sche,classiService);
			arraylist.add(svo);
		}
		return arraylist;
	}

	@Override
	public ScheduleVO findById(Integer id) {
		ScheduleVO svo = ScheduleVO.tranform(scheduleDAO.findById(id),classiService);
		return svo;
	}
}
