package com.shuidi168.earn.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
/**
 * 持久层通用接口
 * @author Blackfeng
 * @param <T>
 */
public interface IBaseDao<T> {
    public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T findById(Serializable id);
	public List<T> findAll();
	public void executeUpdate(String queryName,Object...objects);
	public List<T> executeQuery(String queryName,Object...objects);
	public T executeQueryOne(String queryName,Object...objects);
	public List<T> querylimit(String queryName,int fristResult,int maxResults,Object... objects);
	int queryCount(String queryName, Object... objects);
	BigDecimal queryAccount(String queryName, Object... objects);
}
