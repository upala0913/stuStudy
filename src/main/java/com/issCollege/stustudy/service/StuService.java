package com.issCollege.stustudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.issCollege.stustudy.mapper.ClassesMapper;
import com.issCollege.stustudy.mapper.StuinfoMapper;
import com.issCollege.stustudy.po.EasyUiDataGridResult;
import com.issCollege.stustudy.po.QueryStuVo;
import com.issCollege.stustudy.po.Stuinfo;


public interface StuService {
	
	public Stuinfo getStuById(Long id) throws Exception;
	public Stuinfo addStu(Stuinfo stu) throws Exception;
	public List<Stuinfo> findStuByName(String name) throws Exception;
	public EasyUiDataGridResult  getAllStu(int page,int rows,QueryStuVo vo);


}
