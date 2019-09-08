<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/js/easyui/themes/icon.css">
<!-- 	<link rel="stylesheet" type="text/css" href="../demo.css"> -->
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<div id="w" class="easyui-window" title="Basic Window" data-options="iconCls:'icon-save',closed:'false'" style="width:500px;height:200px;padding:10px;">
		The window content.
	</div>
	<ul class="easyui-tree" data-options="animate:true,lines:true">
	<li><span>java基础</span>
	<ul>
		<li><span>第一章</span>
			<ul>
				<li>第一节</li>
				<li>第二节</li>
				<li>第三节</li>
				<li>第四节</li>
			</ul>
		</li>
		<li><span>第二章</span>
			<ul>
				<li>第一节</li>
				<li>第二节</li>
				<li>第三节</li>
				<li>第四节</li>
			</ul>
		</li>
		<li><span>第三章</span>
			<ul>
				<li>第一节</li>
				<li>第二节</li>
				<li>第三节</li>
				<li>第四节</li>
			</ul>
		</li>
	</ul>
	</li>
	<li>数据库设计</li>
	<li>web前端</li>
	</ul>
	<input class="easyui-combobox" name="course"  data-options="url:'${pageContext.servletContext.contextPath}/course/getAll.action',valueField:'id',textField:'name'" />
</body>
</html>