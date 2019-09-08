package com.issCollege.stustudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.issCollege.stustudy.mapper.CourseMapper;
import com.issCollege.stustudy.po.Course;
import com.issCollege.stustudy.po.CourseExample;
import com.issCollege.stustudy.po.CourseExample.Criteria;

/*********************
*@author xue-1
*@time 2018年11月21日  下午1:59:20
*@version V1.0 
**********************/
@Service("CourseService")
public class CourseServiceImpl implements CourseService {
	
	@Autowired(required=false)
	private CourseMapper CourseMapper;

	@Override
	@Transactional(rollbackFor={Exception.class})
	public List<Course> getAllCourse() throws Exception {
		// TODO Auto-generated method stub
		CourseExample courseExample = new CourseExample();
		courseExample.isDistinct();
		Criteria criteria = courseExample.createCriteria();
		List<Course> list = CourseMapper.selectByExample(courseExample);
		return list;
	}

}
