package test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import test.TestBase;

public class TestBookDao extends TestBase{


	ApplicationContext ac=super.getContext();
//	@Before
//	public void init(){
//		String[] conf={"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
//		ac=new ClassPathXmlApplicationContext(conf);
//	}
	@Test
	public void test1(){
		BookDao dao=ac.getBean("bookDao",BookDao.class);
		List<Book> books=dao.findByUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		for(Book book:books){
			System.out.println(book.getCn_notebook_id()+","+book.getCn_notebook_name());
		}
	}
}
