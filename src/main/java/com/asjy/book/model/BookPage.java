package com.asjy.book.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BookPage {
	private int currentPage;
	private int totalPage;
	private List<Book> bookPageList;
	public static final int PAGE_COUNT=5;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<Book> getBookPageList() {
		return bookPageList;
	}
	public void setBookPageList(List<Book> bookPageList) {
		this.bookPageList = bookPageList;
	}
	public static int getPageCount() {
		return PAGE_COUNT;
	}
	
	
	
}
