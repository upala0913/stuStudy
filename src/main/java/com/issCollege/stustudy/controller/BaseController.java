package com.issCollege.stustudy.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.issCollege.stustudy.po.EditorRs;

/*********************
*@author xue-1
*@time 2018年11月15日  上午8:42:26
*@version V1.0 
**********************/
@Controller
@PropertySource("classpath:uploadFile.properties")
public class BaseController {
	private Logger logger=Logger.getLogger(BaseController.class);
	@Value("${file_baseFile}")
	private String baseFile;
	@Value("${file_imgBaseURL}")
	private String imgBaseURL;
	@RequestMapping("/upload")
	@ResponseBody
	public Map uploadFile(MultipartFile file) {
		String oldName = file.getOriginalFilename();
		logger.debug("文件上传..."+oldName);
		HashMap map = new HashMap();
		String newName=UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));
		try {
			file.transferTo(new File(baseFile, newName));
			map.put("error", 0);
			map.put("url",imgBaseURL+newName);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			map.put("error", 1);
			map.put("message", "文件上传失败，请稍后重新尝试！");
		}		
		return map;
	}

}
