package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareNoteController {

	@Resource
	private ShareService shareService;
	@RequestMapping("/add.do")
	@ResponseBody //将结果以json格式返回
	public NoteResult<Object> execute(String noteId){
		NoteResult<Object> result=shareService.addShare(noteId);
		return result;
	}
	
}
