package cn.tedu.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("userService")//ɨ��spring����
@Transactional //����������
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	public NoteResult<User> checkLogin(String name, String password) {
		//���ܽ������
		NoteResult<User> result = new NoteResult<User>();
		//������name��ѯ���ݿ�
		User user=userDao.findByName(name);
		//�û������
		if(user==null){
			result.setStatus(1);
			result.setMsg("�û���������");
			return result;
		}
		//�������
		if(user.getCn_user_password().equals(password)){
			result.setStatus(2);
			result.setMsg("�û��������");
			return result;
		}
		//�û��������붼��ȷ
		result.setStatus(0);
		result.setMsg("��½�ɹ�");
		result.setData(user);
		return result;
	}
	
	
	public NoteResult<Object> addUser(String name, String password, String nick) {
		NoteResult<Object> result = new NoteResult<Object>();
		//�û����
		User hasUser = userDao.findByName(name);
		if(hasUser!=null){ //�û�������
			result.setStatus(1);
			result.setMsg("�û��ѱ�ռ��");
			return result;
		}
		
		//����û�
		User user = new User();
		//�����û���
		user.setCn_user_name(name);
		//�����û�����
		String md5Password = NoteUtil.md5(password);
		user.setCn_user_password(md5Password);
		//�����ǳ�
		user.setCn_user_nick(nick);
		//�����û�ID
		String id = NoteUtil.createId();
		//�����û�ID
		user.setCn_user_id(id);
		//�����û�����
		userDao.save(user);
		
		//�������ؽ��
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		return result;
	}

	
	
}
