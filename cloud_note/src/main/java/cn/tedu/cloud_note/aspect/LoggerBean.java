package cn.tedu.cloud_note.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect //���淽����
public class LoggerBean {

//	@Before("within(cn.tedu.cloud_note.controller..*)") //����������㡣ע���ķ�����Ϊ���淽��
//	public void logController(){
//		System.out.println("AOP����ע��");
//	}
	@Before("within(cn.tedu.cloud_note.service..*)")
	public void prac(){
		System.out.println("��ע��ķ�ʽ��ϰaop����ע��");
	}
}
