package cn.tedu.cloud_note.service;

import java.util.List;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteResult;

public interface ShareService {

	NoteResult<Object> addShare(String noteId);

	NoteResult<List<Share>> searchShare(String keyword,int page);

}
