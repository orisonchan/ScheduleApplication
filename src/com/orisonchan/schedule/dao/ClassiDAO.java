package com.orisonchan.schedule.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.orisonchan.schedule.bean.Classi;

/**
 * @author Orison Chan
 */
@Repository("classiDAO")
public class ClassiDAO {
	private static final Logger log = LoggerFactory.getLogger(ClassiDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PARENT_ID = "parentId";
	public static final String PRIORITY = "priority";

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(Classi transientInstance) {
		log.debug("saving Classi instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Classi persistentInstance) {
		log.debug("deleting Classi instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Classi findById(java.lang.Integer id) {
		log.debug("getting Classi instance with id: " + id);
		try {
			Classi instance = (Classi) getCurrentSession().get("com.orisonchan.schedule.bean.Classi", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByExample(Classi instance) {
		log.debug("finding Classi instance by example");
		try {
			List results = getCurrentSession().createCriteria("com.orisonchan.schedule.bean.Classi").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Classi> findByProperty(String propertyName, Object value) {
		log.debug("finding Classi instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Classi as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			List<Classi> list = new ArrayList<Classi>();
			for (int i = 0; i < queryObject.list().size(); i++)
				list.add((Classi) queryObject.list().get(i));
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	@SuppressWarnings("rawtypes")
	public List findByParentId(Object parentId) {
		return findByProperty(PARENT_ID, parentId);
	}

	@SuppressWarnings("rawtypes")
	public List findByPriority(Object priority) {
		return findByProperty(PRIORITY, priority);
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		log.debug("finding all Classi instances");
		try {
			String queryString = "from Classi";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Classi merge(Classi detachedInstance) {
		log.debug("merging Classi instance");
		try {
			Classi result = (Classi) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Classi instance) {
		log.debug("attaching dirty Classi instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Classi instance) {
		log.debug("attaching clean Classi instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public int CountByUserId(int id) {
		@SuppressWarnings("rawtypes")
		List list = findByProperty("userId", id);
		return list.size();
	}

	public Integer insert(Classi cl) {
		System.out.println(cl.getCreateTime() + " in dao");
		Timestamp a = cl.getCreateTime();
		a = Timestamp.valueOf((a.toString()).substring(0, 19));
		System.out.println(a + " in dao");
		cl.setCreateTime(a);
		return (Integer) getCurrentSession().save(cl);
	}

	@SuppressWarnings("rawtypes")
	public List ListofTimeBetween(Integer userId, Timestamp starttime, Timestamp endtime) {
		Date olddate = new Date(starttime.getTime());
		Date newdate = new Date(endtime.getTime());
		String queryString = "from Classi where userId = " + userId + " and createTime>'" + olddate
				+ "' and createTime<'" + newdate + "'";
		Query queryObject = getCurrentSession().createQuery(queryString);
		return queryObject.list();
	}
}