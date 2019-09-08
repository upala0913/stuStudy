<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#imgList {
	display: block;
}

#imgList li {
	list-style: none;
	float: left;
}
</style>
</head>
<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"
	type="text/javascript"></script>

	<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/js/easyui/themes/icon.css" />

<script charset="utf-8"
	src="${pageContext.servletContext.contextPath}/js/easyui/jquery.easyui.min.js"></script>

<body>
	hello-----firstPage!
	
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/js/editor/themes/default/default.css" />
<script charset="utf-8"
	src="${pageContext.servletContext.contextPath}/js/editor/kindeditor-all-min.js"></script>
<script charset="utf-8"
	src="${pageContext.servletContext.contextPath}/js/editor/lang/zh-CN.js"></script>
<script type="text/javascript">
var editor;
	$(function(e) {
		
		var options = {
			allowFileManager : true,
			uploadJson : '${pageContext.servletContext.contextPath}/upload.action',
			filePostName : 'file',
			afterUpload : function(data) {
				alert(data);
			},
			urlType : 'absolute'
		};

		/* KindEditor.ready(function(K) { */
			editor = KindEditor.create('#editor_id', options);
		/* }); */

		var uploadbutton = KindEditor
				.uploadbutton({
					button : KindEditor("#upload")[0],
					filedName : 'file',
					url : '${pageContext.servletContext.contextPath}/upload.action',
					afterUpload : function(data) {
						if (data.error == 0) {
							$('#imgList')
									.append(
											"<li><img src='"+data.url+"' style='width:80px;max-height:120px'><input type='hidden'  value='"+data.url+"' /></li>");
						} else {
							alert(data.message);
						}
					}
		})

		uploadbutton.fileBox.change(function(e) {
			uploadbutton.submit();
		}); 
	})
	
		function submitStuinfo(){		
			/* $('#addStuForm').form('submit',{
				url:'${pageContext.servletContext.contextPath}/stu/add.action',
				onSubmit:function(param){	
					var names="";
					$('#imgList li img').each(function(){
						names=names+$(this).prop('src');
					});
					param.pic=names;
					window.editor.sync();
				},
				success:function(data){
					if(data){
						alert(data+"添加成功");
					}
				}
			}) */
			//easyUI的表单处理，调表单的提交方法，初始化参数，easyui的表单提交是基于ajax的
			
			$('#addStuForm').form('submit',{
				//表单提交的哦地址
				url:'${pageContext.servletContext.contextPath}/stu/add.action',
				//当提交时的处理方法，param 当前表单的参数集会注入进来，也可在里面增加新的参数
				onSubmit:function(param){
					var names="";
					$('#imgList li img').each(function(){
						names=names+$(this).prop('src');
					});
					//增加一个图片参数
					param.pic=names;
					//提交前同步kind父文本编辑器的内容
				//	window.editor.sync();
					editor.sync();
					alert(editor.html());
				},
				//表单提交成功的回调函数，data服务端返回的json结果
				success:function(data){
					var jsonarray= $.parseJSON(data);
					alert(jsonarray.stu.name);
					/* if(data){
						alert(data+"添加成功");
					} */
				}
			});
		}
</script>
	
	<form id="addStuForm">
	<label>用户名：</label><input name="name" type="text" />
	<br />
	<input type="button" value="上传图片" id="upload">
	<br />
	<ul id="imgList">
	</ul>
<textarea id="editor_id" name="info"
			style="width: 700px; height: 300px; ">	
</textarea>
<button onclick="submitStuinfo()" type="button">增加</button>
</form>
	<a
		href="${pageContext.servletContext.contextPath}/upload.action?file=111.jpg">eee</a>

<script type="text/javascript">
function menu(){	
	var menu = KindEditor.menu({
	    width : 200,
	    x : 100,
	    y : 200,
	    z : 1000,
	    centerLineMode : false
	});
	KindEditor.each('9px,10px,12px,14px,16px,18px,24px,32px'.split(','), function(i, val) {
	    menu.addItem({
	            title : '<span style="font-size:' + val + ';">' + val + '</span>',
	            click : function() {
	                    alert(val);
	            },
	            height : parseInt(val, 10) + 12,
	            checked : val === '12px'
	    });
	});
}
</script>

<hr/>
<table class="easyui-datagrid" data-option="url:'{pageContext.servletContext.contextPath}/stu/byName.action?name=a'">
	 <thead>
		<tr>
			<th data-options="field:'listStu.id',width:100">ID</th>
			<th data-options="field:'listStu.name',width:100">NAME</th>
			<th data-options="field:'listStu.sex',width:100">SEX</th>
		</tr>
    </thead>
  
</table>
</body>
</html>