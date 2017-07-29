package cn.tedu.cloud_note.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditBean {

	@Around("within(cn.tedu.cloud_note.service..*)") //参数也是切入点
	public Object audit(ProceedingJoinPoint point) throws Throwable{
		Object obj=null;
		try {
			long timeStart=System.currentTimeMillis();
			obj=point.proceed(); //运行切入点方法
			long timeEnd=System.currentTimeMillis();
			String str=point.getSignature().toString(); //返回方法签名的字符串类型
			System.out.println(str+"耗时："+(timeEnd-timeStart));
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
		return obj;
	}
}
