package com.asjy.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.asjy.book.model.Book;

public interface AdminDao {
	List<Book> findBookPageDynamic(@Param("no") String no,@Param("name") String name,@Param("nowpage")int nowpage,@Param("pageCount") int pageCount);
	//因为是动态的sql语句，不但只能执行全查询功能，还要执行有条件分页查询，全查询分页，所以传pageCount不能写成固定的用实体类中的常量的PAGE_COUNT,因为不管全查询分页还是有条件查询分页，
	//都需要计算总共可分成的页数，这个页数是由符合条件的条数除以每页显示多少条得到，符合条件的条数就是不加分页得到的结果,如果
	
	int deleteBook(int[] manyNo);
}
