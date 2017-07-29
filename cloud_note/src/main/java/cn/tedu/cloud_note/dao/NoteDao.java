package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Note;

public interface NoteDao {

	public List<Note> findByBookId(String bookId);
//	public List<Map> findByBookId(String bookId);//泛型成Map也可以
	public Note findByNoteId(String noteId);
	
	public int updateNote(Note note);
	
	public int updateNoteByMap(Map<String,Object> map);//动态sql更新数据
	
	public int saveNote(Note note);
	
	public int deleteNote(String noteId);
	
	/**
	 * map 中需要添加参数：
	 * map={ids:[id1,id2,id3...],status:2}
	 * ids 代表需要删除的笔记的ID集合/数组
	 * status 代表需要删除的笔记的状态属性
	 * @param map
	 * @return
	 */
	public int deleteNotes(Map<String,Object> map);//批量删除
	
	int delete(String id);
}













