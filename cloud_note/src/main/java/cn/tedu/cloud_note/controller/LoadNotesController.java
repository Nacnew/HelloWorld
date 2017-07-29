package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadNotesController {

	@Resource
	private NoteService noteService;
	@ResponseBody
	@RequestMapping("/loadnotes.do")
	public NoteResult<List<Note>> execute(String bookId){
		NoteResult<List<Note>> result=noteService.loadBookNotes(bookId);
		return result;
	}
}
