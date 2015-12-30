package com.steelzack.vma.dao;

import java.util.List;

import com.steelzack.vma.entities.OriginalInfo;

public interface OriginalInfoDao {

	List<OriginalInfo> getOriginalInfos();

	// OriginalInfo getOriginalInfoById(int id);

	// boolean deleteOriginalInfoById(int id);

	boolean createOriginalInfo(OriginalInfo originalInfo);

	// boolean deleteOriginalInfo(OriginalInfo originalInfo);

	// boolean updateOriginalInfo(OriginalInfo originalInfo);
}
