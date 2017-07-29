function alertAddBookWindow(){
	$("#can").load("alert/alert_notebook.html");
	$(".opacity_bg").show();
}
//关闭对话框
function closeAlertWindow(){
	//清空div内容
	$("#can").html("");
	//隐藏背景色
	$(".opacity_bg").hide();
}
//弹出新建笔记对话框
function alertAddNoteWindow(){
	//判断是否选中笔记本
	var $li=$("#book_ul a.checked").parent();
	if($li.length==0){
		alert("请选择笔记本");
	}else{
		//弹出创建笔记对话框
		$("#can").load("alert/alert_note.html");
		$(".opacity_bg").show();
	}
}
