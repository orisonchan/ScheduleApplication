package com.orisonchan.schedule.bean;

import java.sql.Timestamp;

public class Classi {
	int id;
	String name;
	int parentId;
	int userId;
	int level;
	Timestamp createTime;

	public Classi(){
		
	}
	
	/**
	 * ��id�Ĺ��췽��������addʱ,createtimeֱ��ȡ��ǰʱ��
	 * @param name
	 * @param parentId
	 * @param userId
	 * @param level
	 */
	public Classi(String name,int parentId,int level,int userId){
		this.name = name;
		this.parentId = parentId;
		this.userId = userId;
		this.level = level;
		this.createTime = new Timestamp(System.currentTimeMillis());
		System.out.println(this.createTime+" in constructor");
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parent_id) {
		this.parentId = parent_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int user_id) {
		this.userId = user_id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
