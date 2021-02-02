package com.test.spring11.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.spring11.vo.FileInfoVo;

@Repository
public class FileInfoDao {
	@Autowired
	private SqlSession sqlsession;
	
	private final String NAMESPACE = "com.test.spring11.mapper.FileinfoMapper";
	
	public int insert(FileInfoVo vo) {
		return sqlsession.insert(NAMESPACE+".insert",vo);
	}
	
	public int delete(int num) {
		return sqlsession.delete(NAMESPACE+".delete",num);
	}
	public List<FileInfoVo> list() {
		return sqlsession.selectList(NAMESPACE+".list");
	}
	
	public FileInfoVo select(int num) {
		return sqlsession.selectOne(NAMESPACE+".select",num);
	}
	
	public int update(FileInfoVo vo) {
		return sqlsession.update(NAMESPACE+".edit",vo);
	}
	
}
