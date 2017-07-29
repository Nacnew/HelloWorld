//加载分享笔记列表的page+1页
function moreSearchShare(){
	//获取参数
	var keyword=$("#search_note").val().trim();
	page=page+1;
	//发送ajax请求，加载列表
	loadMoreShare(keyword,page);
}
//按回车加载搜索结果的第page页
function loadMoreShare(keyword,page){
	//发送ajax请求
	$.ajax({
		url:path+"/share/search.do",
		type:"post",
		data:{"keyword":keyword,"page":page},
		dataType:"json",
		success:function(result){
			var lists=result.data;
			if(lists.length>0){
				//alert(typeof list); js中一切皆对象，typeof obj 返回对象类型
				alert("搜索完成");
				//$("#note_ul").empty();
				for(var i=0;i<lists.length;i++){
					var shareId=lists[i].cn_share_id;
					var shareTitle=lists[i].cn_share_title;
					//createNoteLi(noteId,noteTitle);
					//偷懒用cn_note表中的数据不好
					var sli="";
					sli+='<li class="online">';
					sli+='<a>';
					sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli+=shareTitle;
					sli+='</a>';
					sli+='</li>';
					var $li=$(sli);
					//把shareId绑定到li上
					$li.data("shareId",shareId);
					//将li添加到ul当中
					$("#search_ul").append($li);
					//切换显示区
					$("#pc_part_2").hide();
					$("#pc_part_6").show();
				}
			}else{
				alert("无搜索结果");
			}
		},
		error:function(){
			alert("搜索失败");
		}
	});
}
//根据搜索关键字，分页显示分享笔记
function searchShare(event){//将操作事件作为参数传入
	//$("#search_ul").remove();
	var code=event.keyCode;//获取按键的code
	if(code==13){
		//获取请求参数值 //一般从网页上获取的文本，都会trim()一下
		var keyword=$("#search_note").val().trim();
		page=1;
		loadMoreShare(keyword,page);
	}
}
















