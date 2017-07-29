package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import test.TestBase;

public class TestNoteDao extends TestBase{

	private NoteDao dao;
	@Before
	public void init(){
		dao=super.getContext().getBean("noteDao",NoteDao.class);
	}
	@Test
	public void test1(){
		List<Note> notes=dao.findByBookId("fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		for(Note note:notes){
			System.out.println(note.getCn_note_id()+","+note.getCn_note_title());
		}
	}
	@Test
	public void test2(){
		String noteId="fed920a0-573c-46c8-ae4e-368397846efd";
		Note note=dao.findByNoteId(noteId);
		System.out.println(note.getCn_note_id());
		System.out.print(note.getCn_note_title());
	}
	@Test
	public void test3(){
		String noteId="fed920a0-573c-46c8-ae4e-368397846efd";
		Note note=new Note();
		note.setCn_note_id(noteId);
		String title="后端开发程序员";
		note.setCn_note_title(title);
		String body="数据库，框架，基本语法，语言结构，JS……";
		note.setCn_note_body(body);
		Long time=System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		int num=dao.updateNote(note);
		System.out.println(num);
	}
	@Test //测试动态sql语句
	public void testUpdateNoteByMap(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", "java");
		map.put("noteId","fed920a0-573c-46c8-ae4e-368397846efd");
		//故意省略了参数body和time
		dao.updateNoteByMap(map);
	}
	@Test //测试批量删除
	public void testDeleteNotes(){
		Map<String,Object> map=new HashMap<String,Object>();
		String[] ids={"id1","id2","id3"};
		map.put("ids",ids);
		map.put("status", 2);
		int n=dao.deleteNotes(map);
		System.out.println(n);
	}
}











