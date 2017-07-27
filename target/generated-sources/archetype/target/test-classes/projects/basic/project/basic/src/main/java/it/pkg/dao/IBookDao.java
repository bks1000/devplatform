package it.pkg.dao;

import java.util.List;

import it.pkg.dto.Book;

public interface IBookDao {
	
	/**
	 * 查询图书列表
	 * @return
	 */
	List<Book> getBookList();
	
	/**
	 * 获取图书信息
	 * @param id
	 * @return
	 */
	Book getBookById(int id);
	
	/**
	 * 保存或更新书信息
	 * @param book
	 */
	void saveBook(Book book);
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	void delBookById(int id);
}
