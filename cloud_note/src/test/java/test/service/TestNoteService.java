package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;
import test.TestBase;

public class TestNoteService extends TestBase{

	private NoteService noteService;
	@Before
	public void init(){
		noteService=
				super.getContext().getBean("noteService",NoteService.class);
	}
	@Test
	public void test1(){
		NoteResult<List<Note>> result=
				noteService.loadBookNotes(
						"fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		System.out.println(result.getStatus()+","+result.getMsg());
	}
	@Test
	public void test2(){
		NoteResult<Note> result=noteService.loadNote("fed920a0-573c-46c8-ae4e-368397846efd");
		System.out.println(result.getMsg());
	}
	@Test
	public void test3(){
		String noteId="fed920a0-573c-46c8-ae4e-368397846efd";
		String title="service层中设计的";
		String body="service的测试";
		NoteResult<Object> result=noteService.updateNote(noteId, title, body);
		System.out.println(result);
	}
	@Test
	public void test4(){
		//调用动态参数时，可以不创建数组，直接写参数
		noteService.deleteNotes("fed920a0-573c-46c8-ae4e-368397846efd","id2","id3");
		
	}
}













