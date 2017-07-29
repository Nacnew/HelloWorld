package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet{

	public void service(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		System.out.println("service()");
		request.setCharacterEncoding("utf-8");
		//��ȡ������Դ·��
		//http://ip:port/ajax-day01/check.do
		String uri=request.getRequestURI();
		System.out.println("uri:"+uri);
		//����������Դ·��
		String action=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		//��ȡ�ַ�������ͷ����β
		System.out.println("action:"+action);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//���ݷ����Ľ�������в�ͬ�Ĵ���
		if("/check_admin".equals(action)){
			String adminCode=request.getParameter("adminCode");
			System.out.println("adminCode:"+adminCode);
			if("King".equals(adminCode)){
				out.println("�˺��Ѿ�����");
			}else{
				out.println("����ʹ��");
			}
		}
	}
}
