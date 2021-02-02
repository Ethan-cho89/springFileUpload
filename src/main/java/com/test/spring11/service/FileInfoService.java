package com.test.spring11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring11.dao.FileInfoDao;
import com.test.spring11.vo.FileInfoVo;

@Service
public class FileInfoService {
	@Autowired
	private FileInfoDao dao;
	
	public int insert(FileInfoVo vo) {
		return dao.insert(vo);
	}

	public int delete(int num) {
		return dao.delete(num);
	}
	public List<FileInfoVo> list() {
		return dao.list();
	}
	public FileInfoVo select(int num) {
		return dao.select(num);
	}
	
	public int update(FileInfoVo vo) {
		return dao.update(vo);
	}
	
	
	
}
