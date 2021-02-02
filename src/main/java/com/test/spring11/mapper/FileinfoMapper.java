package com.test.spring11.mapper;

import org.apache.ibatis.annotations.Insert;

import com.test.spring11.vo.FileInfoVo;

public interface FileinfoMapper {
	
	@Insert("insert into fileinfo values(fileinfo_seq.nextval,#{writer},#{title},#{content},#{orgfilename},#{savefilename},#{filesize})")
	public int insert(FileInfoVo vo);
}
