package com.orisonchan.schedule.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.orisonchan.schedule.bean.Schedule;

/**
 * @author Orison Chan
 */
@Repository("scheduleDAO")
public class ScheduleDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ScheduleDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String CLASSI_ID = "classiId";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(Schedule transientInstance) {
		log.debug("saving Schedule instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Schedule persistentInstance) {
		log.debug("deleting Schedule instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Schedule findById(java.lang.Integer id) {
		log.debug("getting Schedule instance with id: " + id);
		try {
			Schedule instance = (Schedule) getCurrentSession().get("com.bean.Schedule",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByExample(Schedule instance) {
		log.debug("finding Schedule instance by example");
		try {
			List results = getCurrentSession().createCriteria("com.bean.Schedule")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Schedule instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Schedule as model where model."
					+ propertyName + "= ? order by model.id desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	@SuppressWarnings("rawtypes")
	public List findByClassiId(Object classiId) {
		return findByProperty(CLASSI_ID, classiId);
	}

	@SuppressWarnings("rawtypes")
	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	@SuppressWarnings("rawtypes")
	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		log.debug("finding all Schedule instances");
		try {
			String queryString = "from Schedule";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Schedule merge(Schedule detachedInstance) {
		log.debug("merging Schedule instance");
		try {
			Schedule result = (Schedule) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Schedule instance) {
		log.debug("attaching dirty Schedule instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Schedule instance) {
		log.debug("attaching clean Schedule instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public Integer insertall(Timestamp start_time,Timestamp end_time,String title,String content,int userid,int classiId) {
		Schedule s = new Schedule(start_time, end_time, title, content, userid, classiId);
		return (Integer) getCurrentSession().save(s);
	}
	
	public void update(Schedule s){
		getCurrentSession().update(s);
	}
	
	public int CountByUserId(int id){
		@SuppressWarnings("rawtypes")
		List list = findByProperty("userId",id);
		return list.size();
	}
	
	@SuppressWarnings("rawtypes")
	public List findByUserIdNClassId(int userId,int classiId){
		Integer[] args = { userId, classiId };
		String queryString = "from Schedule s where s.userId = ? and s.classiId = ?";
		Query queryObject = getCurrentSession().createQuery(queryString);
		queryObject.setParameter(0, args[0]);
		queryObject.setParameter(1, args[1]);
		return queryObject.list();
	}
	
	public int CountByUserIdNClassId(int userId,int classiId){
		return findByUserIdNClassId(userId, classiId).size();
	}
	
	@SuppressWarnings("rawtypes")
	public List TodaySchedule(int userId){
		Integer[] args = { userId };
		String queryString = "from Schedule s where s.userId = ? and s.start_time<sysdate() and s.end_time>sysdate()";
		Query queryObject = getCurrentSession().createQuery(queryString);
		queryObject.setParameter(0, args[0]);
		return queryObject.list();
	}
}