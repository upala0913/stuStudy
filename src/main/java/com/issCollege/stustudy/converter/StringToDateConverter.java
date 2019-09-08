package com.issCollege.stustudy.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {
	
	private SimpleDateFormat[] fmts= {
			new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS"),
			new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new  SimpleDateFormat("yyyy-MM-dd"),
			new  SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS"),
			new  SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
			new  SimpleDateFormat("yyyy/MM/dd"),
			new  SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss��SSS����"),
			new  SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss��"),
			new  SimpleDateFormat("yyyy��MM��dd��")			
	};
	private Logger logger=Logger.getLogger(StringToDateConverter.class);

	@Override
	public Date convert(String arg0) { 
		// TODO Auto-generated method stub

		Date date=null;
		if(arg0!=null) {
			for (SimpleDateFormat fmt : fmts) {
				try {
					date=fmt.parse(arg0);
					return date;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					logger.debug("���ڸ�ʽת���쳣��Դ�ַ�����"+arg0);							
				}
			}
		}
		return date;
	}

	public StringToDateConverter() {
		super();
		logger.debug(this.getClass()+"....ʵ����");
	}
	
	

}
