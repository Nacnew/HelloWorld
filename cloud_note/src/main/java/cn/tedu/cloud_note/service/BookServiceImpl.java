package cn.tedu.cloud_note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("bookService")
public class BookServiceImpl implements BookService{

	@Resource
	private BookDao bookDao;
	public NoteResult<List<Book>> loadUserBooks(String userId) {
		NoteResult<List<Book>> result=new NoteResult<List<Book>>();
		List<Book> list=bookDao.findByUserId(userId);
		//对数据库查询结果进行处理，封装为返回给浏览器的处理后的结果.做成一个json???????????????
		result.setStatus(0);//这里是程序员自己设定的，0即为查询成功
		result.setMsg("查询笔记本成功");
		result.setData(list);
		return result;
	}
	public NoteResult<Book> addBook(String userId, String bookName) {
		NoteResult<Book> result=new NoteResult<Book>();
		Book book=new Book();
		book.setCn_notebook_name(bookName);
		book.setCn_user_id(userId);
		String bookId=NoteUtil.createId();
		book.setCn_notebook_id(bookId);
		Timestamp time=new Timestamp(System.currentTimeMillis());
		//我擦嘞，这也行？可以把long类型转换为Timestamp？？？？？？？？
		book.setCn_notebook_createtime(time);
		int rows=bookDao.save(book);
		if(rows==1){
			result.setStatus(0);
			result.setMsg("创建笔记成功");
			result.setData(book);
		}else{
			result.setStatus(1);
			result.setMsg("创建笔记失败");
			return result;
		}
		return result;
	}

}
