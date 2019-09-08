<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/js/easyui/themes/icon.css" />
</head>
<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"
	type="text/javascript"></script>

<script charset="utf-8"
	src="${pageContext.servletContext.contextPath}/js/easyui/jquery.easyui.min.js"></script>

<body>
<script>
$(function(e){
	$('#setSearch').switchbutton({
		checked : false,
		onChange : function(checked) {
			if (checked) {
				$(".searchField").slideDown(500);
			} else {
				$(".searchField").slideUp(500);
				$("#findName").textbox('reset');
				$("#sex").textbox('reset');
				$("#state").textbox('reset');
				$("#beginCreated").textbox('reset');
				$("#endCreated").textbox('reset');
				$("#beginBirth").textbox('reset');
				$("#endBirth").textbox('reset');
				$("#beginAccount").textbox('reset');
				$("#endAccount").textbox('reset');
				$("#beginCredits").textbox('reset');
				$("#endCredits").textbox('reset');					
				doSearch();
			}
		}
	});	
	
});
</script>
	<table class="easyui-datagrid" id="stuList" title="学员列表"
	data-options="fitColumns:true,singleSelect:false,collapsible:false,pagination:true,url:'${pageContext.servletContext.contextPath}/stu/list.action',method:'get',pageSize:10,toolbar:'#tb'">
	
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',align:'center',width:60">学员ID</th>
			<th data-options="field:'name',align:'center',width:80">学员姓名</th>
			<th data-options="field:'sex',align:'center',width:100">性别</th>
			<!-- <th data-options="field:'age',width:100,align:'center',formatter:BANBI.birthToAge">年龄</th> -->
			<th data-options="field:'age',width:100,align:'center'">年龄</th>
			<th
				data-options="field:'birth',width:130,align:'center',formatter:issCollegeTools.formatBirth">生日</th>
			<th
				data-options="field:'state',width:60,align:'center',formatter:issCollegeTools.formatCustomerStatus">状态</th>
			<th
				data-options="field:'created',width:200,align:'center',formatter:issCollegeTools.formatDateTime">创建日期</th>
			
		</tr>
	</thead>
	
	</table>
	
	<div id="tb" style="padding: 2px 5px; height: auto; ">
	<span>批量操作:</span> <a href="javascript:void(0)"
		class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true"
		onclick="deleteByIds()">删除</a> <a href="javascript:void(0)"
		class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
		onclick="accept()">启用</a> <a href="javascript:void(0)"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-remove',plain:true" onclick="accept()">禁用</a>
	<input class="easyui-switchbutton" style="width: 40px; height: 18px"
		id="setSearch" onChange="setSerch()"
		data-options="onText:'on',offText:'off'"> <span>高级查询</span>
	<ul class="searchField">
		<li class="sr"><span>查询名称:</span> <input type="text"
			class="easyui-textbox" id="findName" style="width: 80px" /> <span>性别:</span>
			<select class="easyui-combobox" id="sex" panelHeight="auto"
			style="width: 60px">
				<option value="">不限</option>
				<option value="男">男</option>
				<option value="女">女</option>
		</select> <span>状态:</span> 
			<select class="easyui-combobox" id="state"
			panelHeight="auto" style="width: 60px">
				<option value="">所有</option>
				<option value="1">正常</option>
				<option value="2">禁用</option>
		</select></li>
		<li class="sr"><span class="easyui-tooltip"
			title="未输入开始时间则查询结束时间之前记录<br/>未输入结束时间则查询开始时间之后的记录">注册时间:</span> <input
			class="easyui-datebox " id="beginCreated" labelPosition="left"
			style="width: 120px;">to <input class="easyui-datebox"
			id="endCreated" labelPosition="left" style="width: 120px;"></li>
		<li class="sr"><span>出生日期:</span> <input class="easyui-datebox"
			id="beginBirth" labelPosition="left" style="width: 120px;">to
			<input class="easyui-datebox" id="endBirth" labelPosition="left"
			style="width: 120px;"></li>
		<li class="sr"><span>账户余额:</span> <input
			class="easyui-textbox easyui-numberbox" style="width: 120px"
			id="beginAccount" />to <input
			class="easyui-textbox easyui-numberbox" style="width: 120px"
			id="endAccount" /></li>
		<li class="sr"><span>积分范围:</span> <input class="easyui-numberbox"
			style="width: 120px" id="beginCredits" />to <input
			class="easyui-numberbox" style="width: 120px" id="endCredits" /></li>
		<li class="sr"><a href="#"
			class="easyui-linkbutton easyui-tooltip" title="查询"
			iconCls="icon-search" onclick="doSearch()">Search</a></li>
	</ul>
</div>
</body>
</html>