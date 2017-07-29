package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;
@Service("shareService")
@Transactional //利用AOP声明，使具有事务性。该注解也可以单独添加到方法前
public class ShareServiceImpl implements ShareService{

	@Resource
	private ShareDao shareDao;
	@Resource
	private NoteDao noteDao;
	
	public NoteResult<Object> addShare(String noteId) {
		NoteResult<Object> result=new NoteResult<Object>();
		Note note=noteDao.findByNoteId(noteId);
		Share share=new Share();//new的过程就是初始化的过程
		share.setCn_share_id(NoteUtil.createId());
		share.setCn_note_id(noteId);
		share.setCn_share_body(note.getCn_note_body());
		share.setCn_share_title(note.getCn_note_title());
		shareDao.saveShare(share);
		//模拟一个异常
		String str=null;
		str.length();
		
		result.setMsg("分享成功");
		result.setStatus(0);
		return result;
	}

	public NoteResult<List<Share>> searchShare(String keyword,int page) {
		NoteResult<List<Share>> result=new NoteResult<List<Share>>();
		keyword="%"+keyword+"%";
		int begin=(page-1)*3;
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("keyword",keyword);
		params.put("begin",begin);
		List<Share> list=shareDao.findLikeTitle(params);
		result.setData(list);
		result.setMsg("搜索成功");
		result.setStatus(0);
		return result;
	}

}
