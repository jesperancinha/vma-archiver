package org.jesperancinha.vma.controller.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import org.jesperancinha.vma.entities.OriginalInfo;

public interface OriginalInfoMapper {
	@Insert("INSERT into OriginalInfo(id,title) VALUES(#{id}, #{title})")
	void insertOriginalInfo(OriginalInfo originalInfo);

	@Delete("delete from OriginalInfo")
	void deleteAllOriginalInfo();
}