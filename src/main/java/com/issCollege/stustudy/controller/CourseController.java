package com.issCollege.stustudy.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.issCollege.stustudy.po.Course;
import com.issCollege.stustudy.service.CourseService;

/*********************
*@author xue-1
*@time 2018年11月21日  下午3:09:09
*@version V1.0 
**********************/
@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	private Logger logger=Logger.getLogger(CourseController.class);
	
	
	@RequestMapping("getAll")
	@ResponseBody
	public List<Course> getAll() throws Exception {
		List<Course> list;
		try {
			 list = courseService.getAllCourse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("获得科目出错");
		}		
//		throw new Exception("获得科目出错");
		return list;
	}
	
	//异常处理controller
	@ExceptionHandler({RuntimeException.class})
	public String exception(Exception e,Model model) {
		model.addAttribute("message", e.getMessage());		
		return "exception";
		
	}
}
