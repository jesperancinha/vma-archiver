package com.steelzack.vma.controller.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.steelzack.vma.entities.OriginalInfo;

public interface OriginalInfoMapper {
	@Insert("INSERT into OriginalInfo(id,title) VALUES(#{id}, #{title})")
	void insertOriginalInfo(OriginalInfo originalInfo);

	@Delete("delete from OriginalInfo")
	void deleteAllOriginalInfo();
}