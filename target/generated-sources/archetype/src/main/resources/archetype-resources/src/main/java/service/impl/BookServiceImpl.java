#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package}.dao.IBookDao;
import ${package}.dto.Book;
import ${package}.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private IBookDao dao;
	
	public List<Book> getBookList() {
		return dao.getBookList();
	}

	public Book getBookById(int id) {
		return dao.getBookById(id);
	}

	public void saveBook(Book book) {
		dao.saveBook(book);
		
	}

	public void delBookById(int id) {
		dao.delBookById(id);
	}

}
