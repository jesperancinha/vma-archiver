package org.jesperancinha.vma.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class OriginalInfoList {

	private List<OriginalInfo> users = new ArrayList<OriginalInfo>();

	public OriginalInfoList() {
	}

	public OriginalInfoList(final Collection<OriginalInfo> users) {
		this.users.addAll(users);
	}

	@ApiModelProperty(required = true, position = 1)
	public List<OriginalInfo> getOriginalInfos() {
		return users;
	}

	@ApiModelProperty(required = true, position = 2)
	public int getCount() {
		return users.size();
	}
}
