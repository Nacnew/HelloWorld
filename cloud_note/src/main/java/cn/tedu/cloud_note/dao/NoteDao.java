package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Note;

public interface NoteDao {

	public List<Note> findByBookId(String bookId);
//	public List<Map> findByBookId(String bookId);//���ͳ�MapҲ����
	public Note findByNoteId(String noteId);
	
	public int updateNote(Note note);
	
	public int updateNoteByMap(Map<String,Object> map);//��̬sql��������
	
	public int saveNote(Note note);
	
	public int deleteNote(String noteId);
	
	/**
	 * map ����Ҫ��Ӳ�����
	 * map={ids:[id1,id2,id3...],status:2}
	 * ids ������Ҫɾ���ıʼǵ�ID����/����
	 * status ������Ҫɾ���ıʼǵ�״̬����
	 * @param map
	 * @return
	 */
	public int deleteNotes(Map<String,Object> map);//����ɾ��
	
	int delete(String id);
}













