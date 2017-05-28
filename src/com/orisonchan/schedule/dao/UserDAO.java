package com.orisonchan.schedule.dao;

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

import com.orisonchan.schedule.bean.User;

/**
 * @author Orison Chan
 */
@Repository("userDAO")
public class UserDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String GENDER = "gender";
	public static final String AGE = "age";

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Integer save(User transientInstance) {
		log.debug("saving User instance");
		try {
			Integer id = (Integer) getCurrentSession().save(transientInstance);
			log.debug("save successful");
			return id;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getCurrentSession().get("com.orisonchan.schedule.bean.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List results = getCurrentSession().createCriteria("com.orisonchan.schedule.bean.User")
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	@SuppressWarnings("rawtypes")
	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	@SuppressWarnings("rawtypes")
	public List findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	@SuppressWarnings("rawtypes")
	public List findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public User findUserByNameAndPass(String username, String password) {
		log.debug("finding Users instance with property: " + username
				+ ", passwor: " + password);
		try {

			String[] args = { username, password };
			String queryString = "from User au where au.username = ? and au.password = ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, args[0]);
			queryObject.setParameter(1, args[1]);
			@SuppressWarnings("rawtypes")
			List ul = queryObject.list();
			if (ul.size() > 0) {
				return (User) ul.get(0);
			}
		} catch (Exception re) {
			System.out.println(re);
			log.error("find by example failed", re);
		}
		return null;
	}
	
	public void update(User user){
		getCurrentSession().update(user);
	}
}