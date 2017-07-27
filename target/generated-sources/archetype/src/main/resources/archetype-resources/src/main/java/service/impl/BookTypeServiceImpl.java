#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package}.dao.IBookTypeDao;
import ${package}.dto.BookType;
import ${package}.service.IBookTypeService;

@Service
public class BookTypeServiceImpl implements IBookTypeService {
	
	@Autowired
	private IBookTypeDao dao;

	public void save(BookType book) {
		dao.save(book);
	}

	public List<BookType> getBookTypeList() {
		return dao.getBookTypeList();
	}

	public List<BookType> queryForPage(int offset, int length) {
		return dao.queryForPage(offset, length);
	}

	public BookType getBookTypeById(int id) {
		return dao.getBookTypeById(id);
	}

	public void delBookTypeById(int id) {
		dao.delBookTypeById(id);
	}

}

