package com.test.spring11.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/*
 * 	Ŀ���� �� ����� - > ����ڰ� ���� ����� ��
 */


public class FiledownloadView extends AbstractView {
	//�信�� �ؾ��� �۾� ���� - �ٿ�ε� â���� �����ϱ�
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1. �ٿ�ε�â���� �����ϱ�
		
		setContentType("application/octet-stream"); //�����Ҽ� ������ �ٿ�ε��ϴ� �Ӽ� �̿� (8��Ʈ�� �����ʹٶ�� ��)
		File f = (File)model.get("file");
		long filesize = (Long)model.get("filesize");
		String filename = (String)model.get("filename");
		
		response.setContentLength((int)filesize);
		filename= URLEncoder.encode(filename,"utf-8");
		filename.replaceAll("\\+", "%20"); //���ڵ��ϸ� ������ +�� �ٲ� �ٽ� �������� �ٲٴ� ����
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		
		
		// 2. ���ϴٿ�ε��ϱ�(����)
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(f);
		FileCopyUtils.copy(fis,os);
		fis.close();
		os.close();
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
