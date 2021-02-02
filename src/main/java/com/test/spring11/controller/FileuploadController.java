package com.test.spring11.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.test.spring11.service.FileInfoService;
import com.test.spring11.vo.FileInfoVo;

@Controller
public class FileuploadController {
	@Autowired ServletContext sc; 
	@Autowired FileInfoService service;
	@GetMapping(value = "/fileupload")
	public String fileupload() {
		return "fileupload";
	}
	
	@PostMapping(value = "/fileupload")
	//multipartfile : 전송된 파일에 대한 정보를 갖는 객체
	public String fileuploadOk(String writer, String title, String content, MultipartFile file1){
		String path=sc.getRealPath("/resources/upload");
		System.out.println(path);
		
		String orgfilename = file1.getOriginalFilename();//전송된 파일명
		String savefilename = UUID.randomUUID()+ "_" + orgfilename; //중복되지 않는 파일명 만들기
		long filesize = file1.getSize();
		
		try {
			InputStream is = file1.getInputStream();//전송된 파일을 읽어오기 위한 스트림
			FileOutputStream fos = new FileOutputStream(path+"\\"+savefilename); //서버에 저장할 파일 스트림객체
			FileCopyUtils.copy(is, fos); //파일복사하기
			is.close();
			fos.close();
			service.insert(new FileInfoVo(0, writer, title, content, orgfilename, savefilename, filesize));
			return "success";
			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		
		
	}
}
