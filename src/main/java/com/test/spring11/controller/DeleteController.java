package com.test.spring11.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.spring11.service.FileInfoService;
import com.test.spring11.vo.FileInfoVo;

@Controller
public class DeleteController {
	@Autowired private ServletContext sc;
	@Autowired
	private FileInfoService service;
	
	@RequestMapping(value = "/delete")
	public String delete(int num, HttpServletRequest req) {
		String path=sc.getRealPath("/resources/upload");
		
		FileInfoVo vo = service.select(num);
		File file = new File(path+"\\"+vo.getSavefilename());
		System.out.println(path+"\\"+vo.getSavefilename());
		
		
		boolean check =file.delete();
		if(check==true) {
			int n = service.delete(num);
			return "success";
		}else {
			return "error";
		}
		
	}
}
