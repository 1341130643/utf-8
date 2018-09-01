<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script>
$(function(){
	//alert(1111)
})

function add(){
	var rows = $("#T130add");
		var newRow = rows.children('input').eq(0).clone();
		
		newRow.appendTo(rows);
		rows.append('<br/>');
}

</script>
</head>
<body>

<form action="upload" method="post" enctype="multipart/form-data">  
	  	   名称: <input id="t1" type="text" name="name" value="zhangsan"/><br/>  
		    上传头像: 
		  <div id="T130add">
		    <input type="file" name="myfiles"/><br/> 
		   </div>
	    <input type="button" value="增加上传控件" onclick="add()"/>  
	    <input type="submit" value="添加新用户"/>  
	</form>
	
	<a href="download?fileName=img.jpg">下载2</a>

</body>
</html>