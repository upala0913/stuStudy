package com.issCollege.stustudy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.issCollege.stustudy.po.EasyUiDataGridResult;
import com.issCollege.stustudy.po.QueryStuVo;
import com.issCollege.stustudy.po.Stuinfo;
import com.issCollege.stustudy.service.StuService;
import com.issCollege.stustudy.util.CacheUtil;

@Controller
@RequestMapping("/stu")
public class StuController {
	private Logger logger=Logger.getLogger(StuController.class);
	@Autowired
	private StuService stuService;	
	@RequestMapping("byId")
	public String getStuById(Long id,Model model) throws Exception {
		logger.debug("����id����ѧԱ..."+id);
		Stuinfo stuinfo = stuService.getStuById(id);
		model.addAttribute("stu", stuinfo);
		return "listStu";
	}
	
	@RequestMapping("byName")
	@ResponseBody
	public Map<String, Object> getStuByName(String name,Model model) throws Exception{
		logger.debug("������������..."+name);
		List<Stuinfo> ls = stuService.findStuByName("%"+name+"%");
		PageInfo<Stuinfo> pageInfo=new PageInfo<Stuinfo>(ls);
		model.addAttribute("listStu", ls);
		model.addAttribute("pageInfo",pageInfo);
		HashMap<String, Object> mp = new HashMap<String,Object>();
		mp.put("listStu", ls);
		mp.put("pageinfo",pageInfo);
		return mp ;
	}
	
	@RequestMapping("list")
	@ResponseBody
	public EasyUiDataGridResult getAllStu(int page, int rows, QueryStuVo vo) throws Exception{
		logger.debug("�г�����...");
		EasyUiDataGridResult uiRs = stuService.getAllStu(page, rows, vo);		
		return uiRs ;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Map addStu(Stuinfo stuinfo) throws Exception{
		logger.debug("����ѧԱ��..."+stuinfo);
		HashMap map = new HashMap();
		map.put("error", 0);
		map.put("stu",stuinfo.getName());
		return map ;
	}
	
	@RequestMapping("redis")
    public String redisTest() {
	try {
	    boolean b = CacheUtil.setString("123", "redis");//��redis����ַ��� key-value
	    System.out.println(b);//true�ɹ���
	    System.out.println(CacheUtil.getString("123"));//��radis��ȡ���� key
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
	return "hello";
    }

	public StuController() {
		super();
		logger.debug(this.getClass()+"...ʵ����");
	}
	
	

}
