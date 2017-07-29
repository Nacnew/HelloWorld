package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller //作为spring容器组件
@RequestMapping("/book") //设置请求响应路径
public class LoadBooksController {

	@Resource //注入Service层
	private BookService bookService;
	@RequestMapping("/loadbooks.do")//请求路径中传递的参数userId是从哪里来的？？？？？？？？？？？？？
	@ResponseBody
	public NoteResult<List<Book>> execute(String userId){
		//请求路径中传递的参数名字就是在这里定义的
		NoteResult<List<Book>> result=bookService.loadUserBooks(userId);
		return result;
	}
}
