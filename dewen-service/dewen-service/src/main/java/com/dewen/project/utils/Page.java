package com.dewen.project.utils;

import java.util.Collections;
import java.util.List;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * 
 * @author harlin
 */
public class Page<T> {

	// -- 分页参数 --//
	protected int pageNo = 1;
	protected int pageSize = -1;
	protected boolean autoCount = true;

	// -- 返回结果 --//
	protected List<T> result = Collections.emptyList();
	protected long totalCount = -1;

	// -- 构造函数 --//
	public Page() {

	}

	public Page(int pageSize, int pageNo) {
		this.pageSize = pageSize;
		setPageNo(pageNo);
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(final List<T> result) {
		this.result = result;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}
		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}
	
}