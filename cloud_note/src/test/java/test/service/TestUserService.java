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
	@Test//用例-1：预期结果：用户名不存在
	public void test1(){
		
		NoteResult<User> result=service.checkLogin("刘大师", "123456");
		System.out.println(service.getClass().getName());
//		System.out.println(result.getStatus());
//		System.out.println(result.getMsg());
//		System.out.println(result.getData());
	}
	@Test //用例-2:预期结果:密码错误
		public void test2(){
			NoteResult<User> result
				=service.checkLogin("demo", "123");
			System.out.println(result.getStatus());
			System.out.println(result.getMsg());
			System.out.println(result.getData());
		}
	@Test  //用例-3:预期结果:登录成功
		public void test3(){
			NoteResult<User> result
				=service.checkLogin(
						"demo", "c8837b23ff8aaa8a2dde915473ce0991");
			System.out.println(result.getStatus());
			System.out.println(result.getMsg());
			System.out.println(result.getData());
		}

	@Test  //用例-4：预期结果：注册成功
	public void test4(){
		String name = "苍老师";
		String password="123456";
		String nick ="二蛋儿";
		NoteResult<Object> result = service.addUser(name,password,nick);
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	
	
}
