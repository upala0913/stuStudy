<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/js/easyui/themes/icon.css" />
</head>
<%-- <script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"
	type="text/javascript"></script> --%>
	
	<script src="${pageContext.servletContext.contextPath}/js/jquery-3.3.1.js"
	type="text/javascript"></script>

<script charset="utf-8"
	src="${pageContext.servletContext.contextPath}/js/easyui/jquery.easyui.min.js"></script>
	<script charset="utf-8"
	src="${pageContext.servletContext.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script 
	src="${pageContext.servletContext.contextPath}/js/common.js"
	type="text/javascript"></script>
<script type="text/javascript">
$(function(e){
	
/*找到ul的tree组件，调用easyUi的tree方法，绑定相关的事件集合，下面绑定了单击事件， node参数是点击的 tree节点对象
 * isLeaf判断当前节点是不是最终的哦叶子节点，node.target 目标节点对象
 *
 */
	$('#treeMenu').tree({onClick:function(node){
		alert(node.text);
		// $('#treeMenu').tree('isLeaf',node.target) 判断当前目标节点是不是叶节点
		if($('#treeMenu').tree('isLeaf',node.target)){
			//页面中的哦easy选项卡对象
			var tabs=$('#tabs');
			//tabs.tabs  easy的选项卡方法
			//getTab 获得选项卡   node.text 当前节点的文本内容（是用节点文本作为选项卡的哦 标题）
			var tab=tabs.tabs('getTab',node.text);
			//如果有这个名字的哦选项卡，说明已经打开，只需要定位
			if(tab){
				//选择当前的选项卡
				tabs.tabs("select",node.text);				
			}else{
				//创建选项卡  调选项卡的add方法，初始化参数
				//标题为当前节点的文本
				//链接为之前叶子节点绑定的哦url属性的值
				//closable 选项卡可以关闭
				//bodyCls 内容样式
				tabs.tabs('add',{
				    title:node.text,
				    href: node.attributes.url,
				    closable:true,
				    bodyCls:"content"
				});
			}
		}
	}});


	/* $('#treeMenu').tree({onClick:function(node){
		if($('#treeMenu').tree('isLeaf',node.target)){
			var tabs=$('#tabs');
			var tab=tabs.tabs('getTab',node.text);
			if(tab){
				tabs.tabs("select",node.text);
			}else{
				tabs.tabs('add',{
				    title:node.text,
				    href: node.attributes.url,
				    closable:true,
				    bodyCls:"content"
				});
			});
	}); */
});
</script>

	<!-- <div data-options="region:'north',border:false"  style="height:60px;background:#B3DFDA;padding:10px; text-shadow:1px 1px 3px black"><span style="font:900 32px/60px 微软雅黑; color:#FFF; letter-spacing: 5px">软通在线学习系统1.0——后台管理</span></div>
 -->
	<body class="easyui-layout" style="margin: 0; padding: 0">
    <div data-options="region:'north',split:false" style="height:90px;background:#B3DFDA; text-shadow:1px 1px 3px black"><span style="font:900 32px/60px 微软雅黑; color:#FFF; letter-spacing: 5px">软通在线学习系统1.0——后台管理</span></div>
    <div data-options="region:'south',split:false" style="height:60px;">版权所有：</div>
    <div data-options="region:'east',title:'说明',split:true" style="width:100px;"></div>
    <div data-options="region:'west',title:'菜单管理',split:true" style="width:160px;">
    	<ul id="treeMenu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
    		<li><span>站点信息</span></li>
    		<li><span>平台管理</span>
	    		<ul>
	    			<li>管理员设置</li>
	    			<li>教师管理</li>	
	    			<li data-options="attributes:{'url':'${pageContext.servletContext.contextPath}/jsp/addTeacherPage.action'}">新增教师</li>    		
	    			<li>上传文件管理</li>
	    		</ul>
	    	</li>
	    	<li><span>课程大纲管理</span>
	    	<ul>
	    	<li>ss</li>
	    	</ul>   	
	    	</li>
	    	<li><span>学习资料管理</span>
	    	<ul>
	    	<li>学习资料列表</li>
	    	<li>增加学习资料</li>
	    	</ul>	    	
	    	</li>
	    	<li><span>学生管理</span>
	    		<ul>
	    			<li data-options="attributes:{'url':'${pageContext.servletContext.contextPath}/jsp/listStuPage.action'}">学员列表</li>
	    			<li data-options="attributes:{'url':'${pageContext.servletContext.contextPath}/jsp/addStuPage.action'}">增加学员</li>
	    		</ul>	    	
	    	</li>
    	</ul>
    </div>
    <div data-options="region:'center',title:'主界面'" style="padding:5px;background:#eee;">
    <div id="tabs" class="easyui-tabs" style="width: 100%">
    		<div data-options="region:'center',title:'站点信息'" style="padding:20px" >
    			<ul>
    			<li>|您当前系统信息——${header["User-Agent"]}</li>   
				<li>|主机IP——${header["Host"]}</li>  
				<li>|您当前的IP地址——${pageContext.request.remoteAddr }      </li>    
				<li>|主机端的服务信息——${pageContext.servletContext.serverInfo}</li>   
				<li>|端口信息——${pageContext.request.serverPort}</li>    
				<li>|服务器名称——${pageContext.request.serverName}</li> 
				<li>|站点名称——${pageContext.servletContext.contextPath}</li>
				<%-- <li>|服务器名称——${pageContext.servletContext.servletNames}</li>    --%>
				<li>|客户机名称——${pageContext.request.remoteHost}</li> 
    			</ul>
    		</div>
		</div>
    </div>
</body>
</html>