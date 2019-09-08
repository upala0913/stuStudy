package com.issCollege.stustudy.po;

import java.sql.Date;
import java.util.List;

/*********************
*@author TeacherXue
*@time 2018年11月26日  上午6:39:10
*@version V1.0 
**********************/
public class QueryStuVo {
	private String findName;
	private Boolean findNAmeLike;
	private Integer id;
	private List	ids;
	private Date beginBirth;
	private Date endBirth;
	private Date beginCreated;
	private Date endCreated;
	private String sex;
	private Short state;
	private Integer beginCredits;
	private Integer endCredits;
	private Integer beginAge;
	private Integer endAge;
	public Integer getBeginAge() {
		return beginAge;
	}
	public Date getBeginBirth() {
		return beginBirth;
	}
	public Date getBeginCreated() {
		return beginCreated;
	}
	public Integer getBeginCredits() {
		return beginCredits;
	}
	public Integer getEndAge() {
		return endAge;
	}
	public Date getEndBirth() {
		return endBirth;
	}
	public Date getEndCreated() {
		return endCreated;
	}
	public Integer getEndCredits() {
		return endCredits;
	}
	public String getFindName() {
		return findName;
	}
	public Boolean getFindNAmeLike() {
		return findNAmeLike;
	}
	public Integer getId() {
		return id;
	}
	public List getIds() {
		return ids;
	}
	public String getSex() {
		return sex;
	}
	public Short getState() {
		return state;
	}
	public void setBeginAge(Integer beginAge) {
		this.beginAge = beginAge;
	}
	public void setBeginBirth(Date beginBirth) {
		this.beginBirth = beginBirth;
	}
	public void setBeginCreated(Date beginCreated) {
		this.beginCreated = beginCreated;
	}
	public void setBeginCredits(Integer beginCredits) {
		this.beginCredits = beginCredits;
	}
	public void setEndAge(Integer endAge) {
		this.endAge = endAge;
	}
	public void setEndBirth(Date endBirth) {
		this.endBirth = endBirth;
	}
	public void setEndCreated(Date endCreated) {
		this.endCreated = endCreated;
	}
	public void setEndCredits(Integer endCredits) {
		this.endCredits = endCredits;
	}
	public void setFindName(String findName) {
		this.findName = findName;
	}
	public void setFindNAmeLike(Boolean findNAmeLike) {
		this.findNAmeLike = findNAmeLike;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setIds(List ids) {
		this.ids = ids;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setState(Short state) {
		this.state = state;
	}

}
