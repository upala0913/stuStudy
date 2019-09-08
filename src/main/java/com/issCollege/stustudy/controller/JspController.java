package com.issCollege.stustudy.controller;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*********************
*@author xue-1
*@time 2018年11月19日  下午10:38:36
*@version V1.0 
**********************/
@Controller
@RequestMapping("/jsp")
public class JspController {
	
	private Logger logger = Logger.getLogger(JspController.class);
	
	@RequestMapping("addTeacherPage")
	public String jumpTeacherAddPage() {		
		return "addTeacherPage";		
	}
	
	@RequestMapping("listStuPage")
	public String  jumpStuListPage() {
		return "listStu";
	}
	
	@RequestMapping("addStuPage")
	public String  jumpAddStuPage() {
		return "addStu";
	}

}
