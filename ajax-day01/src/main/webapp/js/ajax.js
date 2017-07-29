//依据id查找某个节点
function $(id){
	return document.getElementById(id);
}
//根据id查找节点，然后返回节点的value
function $F(id){
	return $(id).value;
}
//获得ajax对象
function getXhr(){
	var xhr=null;
	if(window.XMLHttpRequest){
		//非ie
		xhr=new XMLHttpRequest();
	}else{
		xhr=new ActiveXObject("Microsoft.XMLHttp");
	}
	return xhr;
}