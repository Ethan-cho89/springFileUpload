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
		if(!file1.isEmpty()) { //÷�ε� ������ �ִ°��
			orgfilename = file1.getOriginalFilename();//���۵� ���ϸ�
			savefilename = UUID.randomUUID()+ "_" + orgfilename; //�ߺ����� �ʴ� ���ϸ� �����
			filesize = file1.getSize();

			try {
				InputStream is = file1.getInputStream();//���۵� ������ �о���� ���� ��Ʈ��
				FileOutputStream fos = new FileOutputStream(path+"\\"+savefilename); //������ ������ ���� ��Ʈ����ü
				FileCopyUtils.copy(is, fos); //���Ϻ����ϱ�
				is.close();
				fos.close();
				
				FileInfoVo oldvo = service.select(filenum); //���� �ִ� ���� ����� ���� �۾�
				File file = new File(path+"\\"+oldvo.getSavefilename()); //���� �ִ� ���� ��ġ
				System.out.println(file.delete()); // ���� ����� ���� ���� 
				
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
		}
		service.update(new FileInfoVo(filenum, writer, title, content, orgfilename, savefilename, filesize));
		return "success";
	}
}
