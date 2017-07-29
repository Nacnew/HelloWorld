//根据用户id显示笔记本列表
function loadUserBooks(){
	//获取userId
	var userId = getCookie("userId");
	//alert(userId);
	//判断是否获取到有效userID
	if(userId==null){
		window.location.href("log_in.html"); 
	}else{  //发送ajax请求
		$.ajax({
			url:path+"/book/loadbooks.do",
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				if(result.status==0){//判断查询是否成功
					//获取笔记本集合
					var books=result.data;
					for(var i=0;i<books.length;i++){
						//获取笔记本id
						var bookId=books[i].cn_notebook_id;
						//获取笔记本名称
						var bookName=books[i].cn_notebook_name;
						//创建一个笔记本列表的li元素
						createBookLi(bookId,bookName);
					};
				}
			},
			error:function(){
				alert("笔记本加载失败");
			}
		});
	}
}
//创建一个笔记本li元素
function createBookLi(bookId,bookName){
	var sli="";
	sli+='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli+='</i>'; 
	sli+=bookName;
	sli+='</a>';
	sli+='</li>';
	//将sli字符串转换成jQuery对象的li元素
	var $li=$(sli);
	//将bookId值与jQuery对象绑定
	$li.data("bookId",bookId);
	//将li元素添加到笔记本ul列表中
	$("#book_ul").append($li);
}
function addBook(){
	//发送事件
	var userId=getCookie("userId");
	var bookName=$("#input_notebook").val();
	$.ajax({
		url:path+"/book/add.do",
		type:"post",
		data:{"userId":userId,
			  "bookName":bookName},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var bookId=result.data.cn_notebook_id;
				createBookLi(bookId,bookName);
			};
		},
		error:function(){
			alert("创建笔记失败");
		}
	});
	closeAlertWindow();
	//如果两个js文件都被加载到HTML文件中，那么这两个JS文件中的方法可以直接相互调用。
	//jQuery中调用方法后面需要省略小括号，因为jQuery的原则write less,do more.
}





















