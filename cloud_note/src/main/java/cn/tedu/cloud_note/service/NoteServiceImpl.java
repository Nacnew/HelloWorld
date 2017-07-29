package cn.tedu.cloud_note.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("noteService")
public class NoteServiceImpl implements NoteService{

	@Resource //��NoteDaoע�����service����ʹ��dao�ӿ��еķ���
	private NoteDao dao;
	public NoteResult<List<Note>> loadBookNotes(String bookId) {
		NoteResult<List<Note>> result=new NoteResult<List<Note>>();
		List<Note> notes=dao.findByBookId(bookId);
		result.setStatus(0);//�����ǳ���Ա�Լ��趨�ģ�
		//�����ڽ�������ظ�ҳ��ʱ��ajax�Ļص������ܹ����ݴ�״ֵ̬���������Ӧ�Ĵ���
		result.setMsg("���رʼǳɹ�");
		result.setData(notes);//�Ȼ�ajax���յ����ؽ���󣬻���������json������������������
		return result;
	}
	public NoteResult<Note> loadNote(String noteId) {
		NoteResult<Note> result=new NoteResult<Note>();
		Note data=dao.findByNoteId(noteId);
		if(data==null){
			result.setStatus(1);
			result.setMsg("δ�ҵ�����");
			return result;
		}
		result.setStatus(0);
		result.setMsg("�ʼ����ݼ��سɹ�");
		result.setData(data);
		return result;
	}
	public NoteResult<Object> updateNote(String noteId, String title, String body) {
		/*�ֲ�����˼��Ƚ�������service�㷽���Ĳ����б���Ժ�dao��Ĺ��������Ĳ����б�ͬ��
		ֻҪ�ܹ��ڷ����д���þ��С�*/
		//service��һ��Ϳ�ʼ����result�����
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
		Long time=System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		int rows=dao.updateNote(note);
		NoteResult<Object> result=new NoteResult<Object>();
		if(rows==1){
			result.setStatus(0);
			result.setMsg("����ʼǳɹ�");
			//û�з��ؾ�������ݣ����Բ�����data
		}else{
			result.setStatus(1);
			result.setMsg("����ʼ�ʧ��");
			return result;
		}
		return result;
	}
	public NoteResult<Note> addNote(String userId, String bookId, String title) {
		Note note=new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(title);
		note.setCn_note_id(NoteUtil.createId());
		Long time=System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_last_modify_time(time);
		note.setCn_note_body("");
		note.setCn_note_status_id("1");
		dao.saveNote(note);
		NoteResult<Note> result=new NoteResult<Note>();
		result.setMsg("�����ʼǳɹ�");
		result.setStatus(0);
		result.setData(note);
		return result;
	}
	public NoteResult<Object> deleteNote(String noteId) {
		NoteResult<Object> result=new NoteResult<Object>();
		int rows=dao.deleteNote(noteId);
		if(rows==1){
			result.setMsg("ɾ���ʼǳɹ�");
			result.setStatus(0);
			return result;
		}else{
			result.setMsg("ɾ���ʼ�ʧ��");
			result.setStatus(1);
			return result;
		}
	}
	
	@Transactional //spring����е�transaction�����annotationע��,�Ƿ�������������
	public void deleteNotes(String... ids) {
		//String... ����String[]����
		for(String id:ids){
			int n=dao.delete(id);
			if(n!=1){
				//�׳��쳣����������Ļع�
				throw new RuntimeException("ɾ����");
			}
		}
	}

}











