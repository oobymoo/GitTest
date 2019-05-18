package com.asjy.cloud.model;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class ProjectPage {
	private int totalPage;
	private int currentPage;
	private List<Project> pageList;
	private List<User> userList;
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public static final int PAGE_COUNT=5;
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<Project> getPageList() {
		return pageList;
	}
	public void setPageList(List<Project> pageList) {
		this.pageList = pageList;
	}
	public static int getPageCount() {
		return PAGE_COUNT;
	}
	
	

}
