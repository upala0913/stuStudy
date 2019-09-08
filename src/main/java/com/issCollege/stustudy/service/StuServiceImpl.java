package com.issCollege.stustudy.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.issCollege.stustudy.mapper.ClassesMapper;
import com.issCollege.stustudy.mapper.StuinfoMapper;
import com.issCollege.stustudy.po.EasyUiDataGridResult;
import com.issCollege.stustudy.po.QueryStuVo;
import com.issCollege.stustudy.po.Stuinfo;
import com.issCollege.stustudy.po.StuinfoExample;
import com.issCollege.stustudy.po.StuinfoExample.Criteria;

@Service("stuService")
public class StuServiceImpl implements StuService {
	
	@Autowired(required=false)
	private StuinfoMapper stuinfoMapper;
	@Autowired(required=false)
	private ClassesMapper classesMapper;
	
	private Logger logger=Logger.getLogger(StuServiceImpl.class);
	public StuServiceImpl() {
		super();
		logger.debug(this.getClass()+"...实例化");
	}
	
	@Override	
	public Stuinfo getStuById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Stuinfo stuinfo = stuinfoMapper.selectByPrimaryKey(id);
		return stuinfo;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor= {Exception.class})
	public Stuinfo addStu(Stuinfo stu) throws Exception {
		// TODO Auto-generated method stub
		
		Stuinfo s1 = new Stuinfo();
		Stuinfo s2 = new Stuinfo();
		
		s1.setName("qqq");
		s1.setRealname("去去去");
		s1.setSex("男");
		s2.setName("www");
		s2.setRealname("呜呜呜");
		s2.setSex("女");
		
		stuinfoMapper.insertSelective(s1);
		/*if(3>0) {
			throw new Exception("发生异常插入失败");
		}*/
		stuinfoMapper.insertSelective(s2);
		
		return null;
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<Stuinfo> findStuByName(String name) throws Exception {
		// TODO Auto-generated method stub
		StuinfoExample example = new StuinfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameLike(name);
		PageHelper.startPage(1, 10);
		List<Stuinfo> list = stuinfoMapper.selectByExample(example);
		return list;
	}

	@Override
	public EasyUiDataGridResult getAllStu(int page, int rows, QueryStuVo vo) {
		// TODO Auto-generated method stub
		logger.debug("分页获得所有学员...page:"+page+"\trows:"+rows);
		StuinfoExample example = new StuinfoExample();
		Criteria c1 = example.createCriteria();
		example.setOrderByClause("id desc");
		PageHelper helper=new PageHelper();
		Page<Stuinfo> startPage = helper.startPage(page,rows);
		logger.debug("startPage..."+startPage);
		List<Stuinfo> list = stuinfoMapper.selectByExample(example);
		PageInfo<Stuinfo> pageInfo = new PageInfo<Stuinfo>(list);
		EasyUiDataGridResult result = new EasyUiDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
}
