package com.test.spring11.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.spring11.service.FileInfoService;
import com.test.spring11.vo.FileInfoVo;

@Controller
public class FiledownloadController {

	@Autowired private FileInfoService service;
	@Autowired ServletContext sc;
	
	
	@GetMapping(value = "/download")
	public String download(int filenum, Model model) {
		FileInfoVo vo = service.select(filenum);
		String path=sc.getRealPath("/resources/upload");
		File file = new File(path+"\\"+vo.getSavefilename()); //다운로드할 정보를 갖는 객체
		String filename = vo.getOrgfilename();
		Long filesize = vo.getFilesize();
		
		//파일 다운로드에 관련한 정보를 모델에 담기
		
		model.addAttribute("file",file);
		model.addAttribute("filename",filename);
		model.addAttribute("filesize",filesize);
		return "filedownloadView";
	}
	
}
