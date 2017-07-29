package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;
import test.TestBase;

public class TestBookService extends TestBase{

	private BookService bookService;
	@Before
	public void init(){
		bookService=super.getContext().getBean("bookService",BookService.class);
	}
	@Test
	public void test1(){
		NoteResult<List<Book>> result=
				bookService.loadUserBooks(
						"39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
//		System.out.println(result.getData());
		for(Book book:result.getData()){
			System.out.println(book.getCn_notebook_name());
		}
	}
}
