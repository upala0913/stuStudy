package com.issCollege.stustudy.po;

import java.util.List;

/*********************
*@author TeacherXue
*@time 2018��11��15��  ����9:24:53
*@version V1.0 
**********************/
public class EasyUiDataGridResult {
	
	private Long total;
	private List<?> rows;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	

}
