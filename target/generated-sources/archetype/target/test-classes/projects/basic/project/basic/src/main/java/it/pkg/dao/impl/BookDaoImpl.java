package it.pkg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import it.pkg.core.utils.HibernateDao;
import it.pkg.dao.IBookDao;
import it.pkg.dto.Book;

@Repository
public class BookDaoImpl extends HibernateDao implements IBookDao {

	public List<Book> getBookList() {
		return this.find("from Book");
	}

	public Book getBookById(int id) {
		return get(Book.class, id);
	}

	public void saveBook(Book book) {
		saveOrUpdate(book);
	}

	public void delBookById(int id) {
		executeSql("DELETE FROM book WHERE bookid=?", id);
	}

}
