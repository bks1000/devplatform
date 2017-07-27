package it.pkg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.pkg.dao.IBookTypeDao;
import it.pkg.dto.BookType;
import it.pkg.service.IBookTypeService;

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

