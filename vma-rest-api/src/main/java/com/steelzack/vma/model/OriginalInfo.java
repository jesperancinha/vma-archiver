package com.steelzack.vma.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class OriginalInfo {
	private Integer id;

	private String title;

	@ApiModelProperty(required = true)
	public Integer getId() {
		return id;
	}

	@ApiModelProperty(required = true)
	public String getTitle() {
		return title;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
