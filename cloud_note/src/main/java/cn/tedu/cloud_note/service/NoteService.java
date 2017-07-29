package cn.tedu.cloud_note.service;

import java.util.List;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;

public interface NoteService {

	public NoteResult<List<Note>> loadBookNotes(String bookId);
	//�������õķ���ֵ��������util���߰������ȶ���õģ����з����������Ͳ����˷��ͣ�
	//��Ϊ�˴ﵽ���õ�Ч������������������ajax��������Ĵ�������
	public NoteResult<Note> loadNote(String noteId);
	public NoteResult<Object> updateNote(String noteId,String title,String body);
	public NoteResult<Note> addNote(String userId, String bookId, String title);
	public NoteResult<Object> deleteNote(String noteId);
	
	//String... ��̬����������String[ ] ����
	void deleteNotes(String... ids);
}
