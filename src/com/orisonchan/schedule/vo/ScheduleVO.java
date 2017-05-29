package com.orisonchan.schedule.vo;

import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.orisonchan.schedule.bean.Schedule;
import com.orisonchan.schedule.service.ClassiService;

public class ScheduleVO {

	int id;
	int userId;
	int classiId;
	String title;
	Timestamp start_time;
	Timestamp end_time;
	String content;
	String classiname;
	
	public static ScheduleVO tranform(Schedule sche, ClassiService classiService){
		ScheduleVO svo = new ScheduleVO();
		BeanUtils.copyProperties(sche,svo);	
		svo.setClassiname(classiService.queryById(svo.classiId).getName());
		return svo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int user_id) {
		this.userId = user_id;
	}

	public int getClassiId() {
		return classiId;
	}

	public void setClassiId(int classi_id) {
		this.classiId = classi_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getClassiname() {
		return classiname;
	}

	public void setClassiname(String classiname) {
		this.classiname = classiname;
	}
	
}
