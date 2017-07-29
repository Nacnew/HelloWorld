<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="js/ajax.js"></script>
	<script type="text/javascript">
		function check_adminCode(){
			//step1.获得ajax对象
			var xhr=getXhr();
			//step2.发送请求
			var uri='check_admin.do?adminCode='+$F('adminCode');
			xhr.open('get',encodeURI(uri),true);//encodeURI(arg) 将参数转换成ASCII码
			xhr.onreadystatechange=function(){
				//step4.处理服务器返回的数据
				if(xhr.readyState==4&&xhr.status==200){
					//获取服务器返回的数据
					var text=xhr.responseText;
					//更新页面
					$('adminCode_msg').innerHTML=text;
				}
			};
			xhr.send(null);
		}
	</script>
</head>
<body style="font-size:30px;">
	<form action="" method="post">
		账号：<input id="adminCode" name="adminCode" onblur="check_adminCode();">
		<span id="adminCode_msg"></span><br/>
		密码：<input type="password" name="pwd"><br/>
		<input type="submit" value="确定">
	</form>
</body>
</html>