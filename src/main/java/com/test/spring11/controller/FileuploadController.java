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
	//multipartfile : ���۵� ���Ͽ� ���� ������ ���� ��ü
	public String fileuploadOk(String writer, String title, String content, MultipartFile file1){
		String path=sc.getRealPath("/resources/upload");
		System.out.println(path);
		
		String orgfilename = file1.getOriginalFilename();//���۵� ���ϸ�
		String savefilename = UUID.randomUUID()+ "_" + orgfilename; //�ߺ����� �ʴ� ���ϸ� �����
		long filesize = file1.getSize();
		
		try {
			InputStream is = file1.getInputStream();//���۵� ������ �о���� ���� ��Ʈ��
			FileOutputStream fos = new FileOutputStream(path+"\\"+savefilename); //������ ������ ���� ��Ʈ����ü
			FileCopyUtils.copy(is, fos); //���Ϻ����ϱ�
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
