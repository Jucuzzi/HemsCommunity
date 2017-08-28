package com.telek.util;

import java.math.BigInteger;
import java.util.List;

import com.telek.dao.IDAO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @Descrption 分页模型
 * @author zhanxf
 * @date 2016-1-26 TODO
 */
public class PageModel<T> {

	Class<T> clazz;

	public static int DEFAULT_PAGE_SIZE = 0;
	public static IDAO iDAO = null;
	public final static String COUNT_SQL = "select count(*)  FROM (";
	private int pageNumber = 1; // 当前页面
	private int pageSize; // 分页大小
	private int pageCount; // 总页数
	private int recordCount; // 总记录
	public int offset = 0; // 查询开始位置

	public boolean first; // 是否是首页
	public boolean last; // 是否是尾页
	private List<T> items;// 当前页包含的记录列表

	public PageModel() {
		pageNumber = 1;
		pageSize = DEFAULT_PAGE_SIZE;
	}

	public PageModel(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;

	}

	@SuppressWarnings("unchecked")
	public PageModel(int pageNumber, int pageSize, String sql, Class<T> clazz) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		String pageSQL = "";
		if (pageSize > 0) {
			pageSQL = " limit " + (pageNumber - 1) * pageSize + "," + pageSize;
		}

		this.clazz = clazz;

		items = (List<T>) iDAO.findBySQL(sql + pageSQL, this.clazz);
		System.out.println(items);
		sql = COUNT_SQL + sql + ") A";
		List<T> countList = (List<T>) iDAO.findBySQL(sql, null);
		System.out.println(countList);
		if (Util.isEmpty(countList)) {
			setRecordCount(0);
		} else {
			setRecordCount(((BigInteger) ((countList).get(0))).intValue());
		}

	}

	public PageModel<T> resetPageCount() {
		pageCount = -1;
		return this;
	}

	public int getPageCount() {
		if (pageCount < 0)
			pageCount = (int) Math.ceil((double) recordCount
					/ (double) pageSize);
		return pageCount;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setPageNumber(int pn) {
		pageNumber = pn;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
		resetPageCount();
	}

	public PageModel<T> setRecordCount(int recordCount) {
		this.recordCount = recordCount <= 0 ? 0 : recordCount;
		pageCount = (int) Math.ceil((double) recordCount / (double) pageSize);
		return this;
	}

	public int getOffset() {
		return pageSize * (pageNumber - 1);
	}

	public boolean isFirst() {
		return pageNumber == 1;
	}

	public boolean isLast() {
		if (pageCount == 0)
			return true;
		else
			return pageNumber == pageCount;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	@Override
	public String toString() {
		JSONObject obj = new JSONObject();
		obj.put("pageCount", pageCount);
		obj.put("list", JSONArray.fromObject(items));
		return obj.toString();
	}

}
