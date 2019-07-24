package com.atguigu.bookstore.bean;

import java.util.List;

public class Page<T> {

	private List<T> list; // 用来保存每页查询出来的记录的集合
	public static final int PAGE_SIZE = 4; // 指定每页显示的记录数
	private int pageNo; // 当前页
	private int totalPageNo; // 总页数，通过计算得到
	private int totalRecord; // 总记录数

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNo() {
		if(pageNo < 1){
			return 1;
		}else if(pageNo > getTotalPageNo()){
			return getTotalPageNo();
		}else{
			return pageNo;
		}
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	//总页数是由总记录数与每页显示的条数计算得到
	public int getTotalPageNo() {
		if(totalRecord%PAGE_SIZE == 0){
			return totalRecord / PAGE_SIZE;
		}else{
			return totalRecord / PAGE_SIZE + 1;
		}
	}

//	public void setTotalPageNo(int totalPageNo) {
//		this.totalPageNo = totalPageNo;
//	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public static int getPageSize() {
		return PAGE_SIZE;
	}

}
