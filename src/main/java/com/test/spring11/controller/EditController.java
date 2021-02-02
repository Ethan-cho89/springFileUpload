package com.test.spring11.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.test.spring11.service.FileInfoService;
import com.test.spring11.vo.FileInfoVo;

@Controller
public class EditController {
	@Autowired ServletContext sc; 
	
	@Autowired private FileInfoService service;
	
	@GetMapping("/edit")
	public String editform(int num, Model model) {
		FileInfoVo vo = service.select(num);
		model.addAttribute("vo",vo);
		return "edit";
	}
	
	@PostMapping("/edit")
	public String edit(int filenum, String writer, String title, String content, MultipartFile file1, HttpServletRequest req, Model model) {
		
		String path=sc.getRealPath("/resources/upload");
		System.out.println("//path:" +path);
		System.out.println("//req.getContextPath:" + req.getContextPath());
		String orgfilename=null;
		String savefilename=null;
		long filesize=0;
		if(!file1.isEmpty()) { //첨부된 파일이 있는경우
			orgfilename = file1.getOriginalFilename();//전송된 파일명
			savefilename = UUID.randomUUID()+ "_" + orgfilename; //중복되지 않는 파일명 만들기
			filesize = file1.getSize();

			try {
				InputStream is = file1.getInputStream();//전송된 파일을 읽어오기 위한 스트림
				FileOutputStream fos = new FileOutputStream(path+"\\"+savefilename); //서버에 저장할 파일 스트림객체
				FileCopyUtils.copy(is, fos); //파일복사하기
				is.close();
				fos.close();
				
				FileInfoVo oldvo = service.select(filenum); //전에 있던 파일 지우기 위한 작업
				File file = new File(path+"\\"+oldvo.getSavefilename()); //전에 있던 파일 위치
				System.out.println(file.delete()); // 전에 저장된 파일 삭제 
				
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
		}
		service.update(new FileInfoVo(filenum, writer, title, content, orgfilename, savefilename, filesize));
		return "success";
	}
}
