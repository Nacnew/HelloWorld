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
		//�����ݿ��ѯ������д�����װΪ���ظ�������Ĵ����Ľ��.����һ��json???????????????
		result.setStatus(0);//�����ǳ���Ա�Լ��趨�ģ�0��Ϊ��ѯ�ɹ�
		result.setMsg("��ѯ�ʼǱ��ɹ�");
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
		//�Ҳ��ϣ���Ҳ�У����԰�long����ת��ΪTimestamp����������������
		book.setCn_notebook_createtime(time);
		int rows=bookDao.save(book);
		if(rows==1){
			result.setStatus(0);
			result.setMsg("�����ʼǳɹ�");
			result.setData(book);
		}else{
			result.setStatus(1);
			result.setMsg("�����ʼ�ʧ��");
			return result;
		}
		return result;
	}

}
