function createNoteLi(noteId,noteTitle){
	var sli="";
	sli+='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli+=noteTitle;
	sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
	sli+='<i class="fa fa-chevron-down"></i></button>';
	sli+='</a>';
	sli+='<div class="note_menu" tabindex="-1">';
	sli+='<dl>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli+='</dl>';
	sli+='</div>';
	sli+='</li>';
	//转换成jQuery对象
	var $li=$(sli);//怎么转换成jQuery对象的？？？？？？？？？？？？？？
	//保存noteId
	$li.data("noteId",noteId);
	//将li追加到ul中
	$("#note_ul").append($li);
};
//点击笔记本，加载笔记
function loadBookNotes(){//用动态绑定事件，当元素出现后就会自动调用该方法
	$("#pc_part_2").show();
	$("#pc_part_6").hide();
	$("#search_ul").empty();
	//设置选中效果
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//获取参数
	var bookId=$(this).data("bookId");
	//alert(bookId);
	//用ajax发送请求，并解析返回的json
	$.ajax({
		url:path+"/note/loadnotes.do",//path指代路径前面可以加任意路径
		type:"post",
		data:{"bookId":bookId},//请求数据做成json，属性名从哪里来的？？？？
		dataType:"json",
		success:function(result){/*这里的参数从controller中得到，
			那么参数名称是否要和controller的返回值名字保持一致？？？？？？？？？*/
			//解析json数据，生成笔记li，添加到笔记列表中
			//alert("ceshi");
			var notes=result.data;
			//清除原列表信息
			$("#note_ul").empty();
			//$("#note_ul li").remove();
			for(var i=0;i<notes.length;i++){
				var noteId=notes[i].cn_note_id;
				var noteTitle=notes[i].cn_note_title;
				//$("#note_ul li").html(notes[i]);
				createNoteLi(noteId,noteTitle);
			}
		},
		error:function(){
			alert("笔记加载失败");
		}
	});
};
//选中某条笔记后，加载笔记内容
function loadNote(){
	//设置选中效果
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//获取请求参数
	var noteId=$(this).data("noteId");
	//alert(noteId);
	//发送ajax请求
	$.ajax({
		url:path+"/note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var note=result.data;
				var title=note.cn_note_title;
				var body=note.cn_note_body;
				$("#input_note_title").val(title);
				um.setContent(body);
			}
		},
		error:function(){
			alert("笔记内容加载失败");
		}
	});
};
//更新笔记内容
function updateNote(){
	var $li=$("#note_ul a.checked").parent();
	var noteId=$li.data("noteId");
	var noteTitle=$("#input_note_title").val().trim();
	//alert(noteTitle);
	var noteBody=um.getContent();
	alert(noteBody);
	alert(noteId+","+noteTitle+","+noteBody);
	$.ajax({
		url:path+"/note/update.do",
		type:"post",
		data:{"noteId":noteId,
			  "title":noteTitle,
			  "body":noteBody},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var sli="";
				sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				sli+=noteTitle;
				sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
				sli+='<i class="fa fa-chevron-down"></i></button>';
				//将str替换到li中的a元素里
				$li.find("a").html(sli);
				alert(result.msg);
			}
		},
		error:function(){
			alert("保存笔记失败");
		}
	});
}
//添加笔记
function addNote(){
	//获取请求参数
	var userId=getCookie("userId");
	var title=$("#input_note").val().trim();
	var $li=$("#book_ul a.checked").parent();
	var bookId=$li.data("bookId");
	//检查输入数据
	var ok=true;//设个开关
	if(title==""){
		ok=false;
		$("#title_span").html("标题不能为空");
	}
	if(userId==null){//检查cookie中存的userId是否失效
		ok=false;
		window.location.href="log_in.html";
	}
	if(ok){
		$.ajax({
			url:path+"/note/add.do",
			type:"post",
			data:{"userId":userId,"bookId":bookId,"title":title},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					alert(result.msg);
					var note=result.data;
					var noteTitle=note.cn_note_title;
					var noteId=note.cn_note_id;
					createNoteLi(noteId,noteTitle);
					//closeAlertWindow();
				}
			},
			error:function(){
				alert("创建笔记失败");
			}
		});
	}
}
//笔记下拉菜单
function noteMenu(){
	//alert("测试是否绑定成功");
	//隐藏笔记菜单
	$("#note_ul div").hide();
	$("#note_ul a").removeClass("checked");
	$(this).parents("li").find("a").addClass("checked");
	//显示点击菜单
	var note_menu=$(this).parents("li").find("div");
	note_menu.slideDown(500);//slidedown(time)让列表标签滑落打开，打开时间为500毫秒
	return false;//阻止冒泡事件
}
//分享笔记
function shareNote(){
	var $li=$(this).parents("li");
	var noteId=$li.data("noteId");//.parents(最接近的父元素名)
	//alert(noteId);
	$.ajax({
		url:path+"/share/add.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			var noteTitle=$li.text();
			var sli="";
			sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
			sli+=noteTitle;
			sli+='<i class="fa fa-sitemap"></i>';
			sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
			sli+='<i class="fa fa-chevron-down"></i></button>';
			//将笔记li元素里的<a>标签的内容替换
			$li.find("a").html(sli);
			alert("笔记分享成功");
		},
		error:function(){
			alert("分享失败");
		}
	});
}
//删除笔记
function deleteNote(){
	var $li=$(this).parents("li");
	var noteId=$li.data("noteId");
	$.ajax({
		url:path+"/note/delete.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			alert("删除成功");
			$li.remove();//元素.remove() 删除操作的元素
		},
		error:function(){
			alert("删除失败");
		}
	});
}







































