package com.shuidi168.earn.util;
/**
 * 分页工具类
 * @author cym
 *
 */

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean<T> {
	// 当前页
	private Integer currentPage;
	// 每页记录数
	private Integer pageSize;
	// 总记录数
	private Integer totalCount;
	// 总页数
	private Integer totalPage;
	// 开始索引
	private Integer startIndex;
	// 数据
	private List<T> data;
	// 离线查询对象
	private DetachedCriteria dc;

	public PageBean(Integer currentPage, Integer pageSize, Integer totalCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		doCalculate();
	}

	/**
	 * 更新计算的信息，用于调用set方法后
	 */
	public void doCalculate() {
		if (this.currentPage == null) {
			// 如页面没有指定显示那一页.显示第一页.
			this.currentPage = 1;
		}

		if (this.pageSize == null) {
			// 如果每页显示条数没有指定,默认每页显示3条
			this.pageSize = 3;
		}

		this.totalPage = (totalCount - 1) / pageSize + 1;

		// 判断当前页数是否超出范围
		// 不能小于1
		if (this.currentPage < 1) {
			this.currentPage = 1;
		}
		// 不能大于总页数
		if (this.currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		}

		// 根据当前页和每页显示条数计算起始索引
		this.startIndex = (currentPage - 1) * pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public DetachedCriteria getDc() {
		return dc;
	}

	public void setDc(DetachedCriteria dc) {
		this.dc = dc;
	}
}
