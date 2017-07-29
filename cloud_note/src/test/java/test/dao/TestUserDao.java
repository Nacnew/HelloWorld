package test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;

public class TestUserDao {

	@Test
	public void test1(){
	ApplicationContext ac= new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
	UserDao dao=ac.getBean("userDao",UserDao.class);
	User user=dao.findByName("demo");
	System.out.println(user);
	}
	@Test
	public void test2(){
		String [] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
	ApplicationContext ac= new ClassPathXmlApplicationContext(conf);
	//获取UserDao对象
	UserDao dao=ac.getBean("userDao",UserDao.class);
	User user=new User();
	user.setCn_user_id("123456789");
	user.setCn_user_name("张三丰");
	user.setCn_user_password("123456");
	user.setCn_user_nick("君宝");
	dao.save(user);
	System.out.println(user);
	}
	
}
