package cn.tedu.cloud_note.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect //切面方法类
public class LoggerBean {

//	@Before("within(cn.tedu.cloud_note.controller..*)") //参数是切入点。注解后的方法即为切面方法
//	public void logController(){
//		System.out.println("AOP功能注入");
//	}
	@Before("within(cn.tedu.cloud_note.service..*)")
	public void prac(){
		System.out.println("用注解的方式练习aop功能注入");
	}
}
