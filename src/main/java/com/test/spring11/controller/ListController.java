package com.test.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.spring11.service.FileInfoService;
import com.test.spring11.vo.FileInfoVo;


@Controller
public class ListController {

	@Autowired
	private FileInfoService service;
	
	@RequestMapping(value = "/filelist")
	public String list(Model model ){
		List<FileInfoVo> list =  service.list();
		model.addAttribute("list",list);
		return "list";
	}	
}
