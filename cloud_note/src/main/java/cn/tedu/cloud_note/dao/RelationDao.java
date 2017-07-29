package cn.tedu.cloud_note.dao;
import java.util.List;

import cn.tedu.cloud_note.entity.Book;
/**
 * MyBatis������ѯ���ܽӿ�
 */
import cn.tedu.cloud_note.entity.User;

public interface RelationDao {

	//�����������
	public User findUserAndBooks(String userId);
	public User findUserAndBooks1(String userId);
	
	//������������
	public List<Book> findBookAndUser();
}
