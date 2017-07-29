package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller //��Ϊspring�������
@RequestMapping("/book") //����������Ӧ·��
public class LoadBooksController {

	@Resource //ע��Service��
	private BookService bookService;
	@RequestMapping("/loadbooks.do")//����·���д��ݵĲ���userId�Ǵ��������ģ�������������������������
	@ResponseBody
	public NoteResult<List<Book>> execute(String userId){
		//����·���д��ݵĲ������־��������ﶨ���
		NoteResult<List<Book>> result=bookService.loadUserBooks(userId);
		return result;
	}
}
