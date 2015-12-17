package com.steelzack.vma.controller.mapper;

import org.apache.ibatis.annotations.Insert;

import com.steelzack.vma.controller.pojo.OriginalInfo;

public interface OriginalInfoMapper {
	@Insert("INSERT into OriginalInfo(id,title) VALUES(#{id}, #{title})")
	void insertOriginalInfo(OriginalInfo originalInfo);
}