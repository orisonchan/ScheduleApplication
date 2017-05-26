package com.orisonchan.schedule.bean;

import java.sql.Timestamp;

public class Schedule {
	int id;
	int userId;
	int classiId;
	String title;
	Timestamp start_time;
	Timestamp end_time;
	String content;

	public Schedule() {

	}

	public Schedule(Timestamp start_time, Timestamp end_time, String title,
			String content, int userid, int classiId) {
		this.start_time = start_time;
		this.end_time = end_time;
		this.title = title;
		this.content = content;
		this.userId = userid;
		this.classiId = classiId;
	}

	public Schedule(int id, Timestamp start_time, Timestamp end_time, String title,
			String content, int userid, int classiId) {
		this.id = id;
		this.start_time = start_time;
		this.end_time = end_time;
		this.title = title;
		this.content = content;
		this.userId = userid;
		this.classiId = classiId;
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
}
