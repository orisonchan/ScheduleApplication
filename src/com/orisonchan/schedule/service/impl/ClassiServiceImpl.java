package com.orisonchan.schedule.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orisonchan.schedule.bean.Classi;
import com.orisonchan.schedule.dao.ClassiDAO;
import com.orisonchan.schedule.service.ClassiService;

/**
 * @author Orison Chan
 */
@Transactional
@Service("classiService")
public class ClassiServiceImpl implements ClassiService {

	@Autowired
	private ClassiDAO classiDAO;

	@Override
	public int CountByUserId(int id) {
		return classiDAO.CountByUserId(id);
	}

	@Override
	public Integer add(String name, int parentId, int level, int userId) {
		Classi cla = new Classi(name, parentId, level, userId);
		System.out.println(cla.getCreateTime() + " in service");
		return classiDAO.insert(cla);
	}

	@Override
	public List<Classi> queryAllByuserId(int id) {
		List<Classi> list = this.classiDAO.findByProperty("userId", Integer.valueOf(id));
		List<Classi> listDepth = new ArrayList<Classi>();
		for (int i = 0; i < list.size(); i++) {
			Classi clazz = (Classi) list.get(i);
			if (clazz.getLevel() == 0) {
				DepthAdd(clazz, list, listDepth, i);
			}
		}
		return listDepth;
	}

	@Override
	public int queryCreateWithinOneWeekClass(Integer id) {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);
		now.set(Calendar.HOUR_OF_DAY, 0);
		Timestamp endtime = new Timestamp(now.getTimeInMillis());
		now.set(Calendar.DATE, -7);
		Timestamp starttime = new Timestamp(now.getTimeInMillis());
		return classiDAO.ListofTimeBetween(id, starttime, endtime).size();
	}

	public List<Classi> DepthAdd(Classi clazz, List<Classi> originlist, List<Classi> newlist, int i) {
		newlist.add(clazz);
		for (int j = i + 1; j < originlist.size(); j++) {
			Classi child = (Classi) originlist.get(j);
			if (child.getParentId() == clazz.getId()) {
				newlist = DepthAdd(child, originlist, newlist, j);
			}
		}
		return newlist;
	}
	
	@Override
	public Classi queryById(int id){
		return classiDAO.findById(id);
	}
}
