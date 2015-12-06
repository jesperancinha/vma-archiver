package com.steelzack.vma.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.base.Preconditions;
import com.steelzack.vma.model.OriginalInfo;

@Service
public class OriginalInfoService {

	private final Map<String, OriginalInfo> originalInfos = new ConcurrentHashMap<String, OriginalInfo>();

	@PostConstruct
	public void init() throws IOException {
	}

	public OriginalInfo findByOriginalInfoName(final String originalInfoName) {
		return originalInfos.get(originalInfoName);
	}

	public Collection<OriginalInfo> getAll() {
		return originalInfos.values();
	}

	public OriginalInfo createOriginalInfo(final OriginalInfo originalInfo) throws Exception {
		Preconditions.checkNotNull(originalInfo.getId(), //
				"Id cannot be null!!");
		Preconditions.checkArgument(StringUtils.hasText(originalInfo.getTitle()), //
				"Original title cannot be null!!");

		originalInfos.put(originalInfo.getTitle(), originalInfo);

		return originalInfo;
	}
}
