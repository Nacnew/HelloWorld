package test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestUserService {
	 UserService service;
	@Before
	public void init(){
		String [] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-transaction.xml"};
		ApplicationContext ac= new ClassPathXmlApplicationContext(conf);
		service=ac.getBean("userService",UserService.class);
	}
	@Test//����-1��Ԥ�ڽ�����û���������
	public void test1(){
		
		NoteResult<User> result=service.checkLogin("����ʦ", "123456");
		System.out.println(service.getClass().getName());
//		System.out.println(result.getStatus());
//		System.out.println(result.getMsg());
//		System.out.println(result.getData());
	}
	@Test //����-2:Ԥ�ڽ��:�������
		public void test2(){
			NoteResult<User> result
				=service.checkLogin("demo", "123");
			System.out.println(result.getStatus());
			System.out.println(result.getMsg());
			System.out.println(result.getData());
		}
	@Test  //����-3:Ԥ�ڽ��:��¼�ɹ�
		public void test3(){
			NoteResult<User> result
				=service.checkLogin(
						"demo", "c8837b23ff8aaa8a2dde915473ce0991");
			System.out.println(result.getStatus());
			System.out.println(result.getMsg());
			System.out.println(result.getData());
		}

	@Test  //����-4��Ԥ�ڽ����ע��ɹ�
	public void test4(){
		String name = "����ʦ";
		String password="123456";
		String nick ="������";
		NoteResult<Object> result = service.addUser(name,password,nick);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	
	
}
