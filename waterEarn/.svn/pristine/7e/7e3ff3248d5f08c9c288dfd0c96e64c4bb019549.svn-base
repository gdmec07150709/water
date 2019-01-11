package com.shuidi168.earn.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.shuidi168.earn.dao.IBaseDao;

/**
 * 持久层通用实现
 * 
 * @author zhaoqx
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	// 代表的是某个实体的类型
	private Class<T> entityClass;

	@Resource // 根据类型注入spring工厂中的会话工厂对象sessionFactory
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// 在父类（BaseDaoImpl）的构造方法中动态获得entityClass
	public BaseDaoImpl() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获得父类上声明的泛型数组
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
	}

	public void save(T entity) {
		this.getHibernateTemplate().setCheckWriteOperations(false);
		this.getHibernateTemplate().save(entity);
	}

	public void delete(T entity) {
		this.getHibernateTemplate().setCheckWriteOperations(false);
		this.getHibernateTemplate().delete(entity);
	}

	public void update(T entity) {
		this.getHibernateTemplate().setCheckWriteOperations(false);
		this.getHibernateTemplate().update(entity);
	}

	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	public List<T> findAll() {
		String hql = "FROM " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	/**
	 * 执行本地更新语句
	 * 
	 * @param queryName
	 *            本地查询语句名称
	 * @param objects
	 *            可变参数占位符
	 */
	public void executeUpdate(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i = 0;
		for (Object object : objects) {
			// 为HQL语句中的？赋值
			query.setParameter(i++, object);
		}
		// 执行更新
		query.executeUpdate();
	}

	/**
	 * 执行本地查询语句
	 * 
	 * @param queryName
	 *            本地查询语句名称
	 * @param objects
	 *            可变参数占位符
	 */
	public List<T> executeQuery(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i = 0;
		for (Object object : objects) {
			// 为HQL语句中的？赋值
			query.setParameter(i++, object);
		}
		// 执行查询
		return query.list();
	}

	/**
	 * 执行本地查询语句(返回唯一的单个对象)
	 * 
	 * @param queryName
	 *            本地查询语句名称
	 * @param objects
	 *            可变参数占位符
	 * @author chenyumin
	 */
	public T executeQueryOne(String queryName, Object... objects) {
		List<T> list = executeQuery(queryName, objects);
		T t = null;
		if (list != null && (!list.isEmpty())) {
			t = executeQuery(queryName, objects).get(0);
		}
		return t;
	}

	/**
	 * 执行本地查询语句(查询bigDecimal类型的账户金额)
	 * 
	 * @param queryName
	 *            本地查询语句名称
	 * @param objects
	 *            可变参数占位符
	 * @author chenyumin
	 */
	@SuppressWarnings("deprecation")
	public BigDecimal queryAccount(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i = 0;
		for (Object object : objects) {
			// 为HQL语句中的？赋值
			query.setParameter(i++, object);
		}
		// 执行查询
		return (BigDecimal) query.uniqueResult();
	}

	/**
	 * 执行本地查询语句(查询指定的区域的条数，即分页)
	 * 
	 * @param queryName
	 * @param fristResult
	 * @param maxResults
	 * @param objects
	 * @return
	 */
	public List<T> querylimit(String queryName, int fristResult, int maxResults, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i = 0;
		if (objects == null) {
			// 设置分页开始位置和查询的最大条数
			query.setFirstResult(fristResult);
			query.setMaxResults(maxResults);
			// 执行查询
			return query.list();
		}
		for (Object object : objects) {
			// 为HQL语句中的？赋值
			query.setParameter(i++, object);
		}
		// 设置分页开始位置和查询的最大条数
		query.setFirstResult(fristResult);
		query.setMaxResults(maxResults);
		// 执行查询
		return query.list();
	}

	public int queryCount(String queryName, Object... objects) {
		List<T> executeQuery = executeQuery(queryName, objects);
		int count = executeQuery.size();
		return count;
	}
}
