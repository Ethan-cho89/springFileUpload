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
 * 	커스텀 뷰 만들기 - > 사용자가 직접 만드는 뷰
 */


public class FiledownloadView extends AbstractView {
	//뷰에서 해야할 작업 구현 - 다운로드 창으로 응답하기
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1. 다운로드창으로 응답하기
		
		setContentType("application/octet-stream"); //이해할수 없을땐 다운로드하는 속성 이용 (8비트인 데이터다라는 뜻)
		File f = (File)model.get("file");
		long filesize = (Long)model.get("filesize");
		String filename = (String)model.get("filename");
		
		response.setContentLength((int)filesize);
		filename= URLEncoder.encode(filename,"utf-8");
		filename.replaceAll("\\+", "%20"); //인코딩하면 공백이 +로 바뀌어서 다시 공백으로 바꾸는 과정
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		
		
		// 2. 파일다운로드하기(복사)
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(f);
		FileCopyUtils.copy(fis,os);
		fis.close();
		os.close();
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
