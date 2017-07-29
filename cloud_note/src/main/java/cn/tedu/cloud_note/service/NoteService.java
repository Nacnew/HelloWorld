package cn.tedu.cloud_note.service;

import java.util.List;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;

public interface NoteService {

	public NoteResult<List<Note>> loadBookNotes(String bookId);
	//这里设置的返回值类型是在util工具包中事先定义好的，其中返回数据类型采用了泛型，
	//是为了达到重用的效果。利用这个结果类向ajax返回请求的处理结果。
	public NoteResult<Note> loadNote(String noteId);
	public NoteResult<Object> updateNote(String noteId,String title,String body);
	public NoteResult<Note> addNote(String userId, String bookId, String title);
	public NoteResult<Object> deleteNote(String noteId);
	
	//String... 动态参数，就是String[ ] 数组
	void deleteNotes(String... ids);
}
