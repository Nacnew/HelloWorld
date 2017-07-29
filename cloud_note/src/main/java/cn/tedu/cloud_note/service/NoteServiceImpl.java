package cn.tedu.cloud_note.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("noteService")
public class NoteServiceImpl implements NoteService{

	@Resource //把NoteDao注入进该service，以使用dao接口中的方法
	private NoteDao dao;
	public NoteResult<List<Note>> loadBookNotes(String bookId) {
		NoteResult<List<Note>> result=new NoteResult<List<Note>>();
		List<Note> notes=dao.findByBookId(bookId);
		result.setStatus(0);//这里是程序员自己设定的，
		//用于在将结果返回给页面时，ajax的回调函数能够依据此状态值，进行相对应的处理
		result.setMsg("加载笔记成功");
		result.setData(notes);//等会ajax接收到返回结果后，会怎样进行json解析？？？？？？？
		return result;
	}
	public NoteResult<Note> loadNote(String noteId) {
		NoteResult<Note> result=new NoteResult<Note>();
		Note data=dao.findByNoteId(noteId);
		if(data==null){
			result.setStatus(1);
			result.setMsg("未找到数据");
			return result;
		}
		result.setStatus(0);
		result.setMsg("笔记内容加载成功");
		result.setData(data);
		return result;
	}
	public NoteResult<Object> updateNote(String noteId, String title, String body) {
		/*分层的设计思想比较灵活，这里service层方法的参数列表可以和dao层的关联方法的参数列表不同，
		只要能够在方法中处理好就行。*/
		//service层一般就开始返回result结果。
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
		Long time=System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		int rows=dao.updateNote(note);
		NoteResult<Object> result=new NoteResult<Object>();
		if(rows==1){
			result.setStatus(0);
			result.setMsg("保存笔记成功");
			//没有返回具体的数据，可以不设置data
		}else{
			result.setStatus(1);
			result.setMsg("保存笔记失败");
			return result;
		}
		return result;
	}
	public NoteResult<Note> addNote(String userId, String bookId, String title) {
		Note note=new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(title);
		note.setCn_note_id(NoteUtil.createId());
		Long time=System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_last_modify_time(time);
		note.setCn_note_body("");
		note.setCn_note_status_id("1");
		dao.saveNote(note);
		NoteResult<Note> result=new NoteResult<Note>();
		result.setMsg("创建笔记成功");
		result.setStatus(0);
		result.setData(note);
		return result;
	}
	public NoteResult<Object> deleteNote(String noteId) {
		NoteResult<Object> result=new NoteResult<Object>();
		int rows=dao.deleteNote(noteId);
		if(rows==1){
			result.setMsg("删除笔记成功");
			result.setStatus(0);
			return result;
		}else{
			result.setMsg("删除笔记失败");
			result.setStatus(1);
			return result;
		}
	}
	
	@Transactional //spring框架中的transaction事务的annotation注解,是方法具有事务性
	public void deleteNotes(String... ids) {
		//String... 就是String[]数组
		for(String id:ids){
			int n=dao.delete(id);
			if(n!=1){
				//抛出异常，触发事务的回滚
				throw new RuntimeException("删错了");
			}
		}
	}

}











