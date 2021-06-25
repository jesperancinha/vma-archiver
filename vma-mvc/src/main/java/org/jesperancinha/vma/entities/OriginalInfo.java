package org.jesperancinha.vma.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OriginalInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4860255649558522067L;

	private Integer id;

	private String title;

	private Double duration;

	public OriginalInfo() {
		// TODO Auto-generated constructor stub
		System.out.println("POOOO");
	}

	public OriginalInfo(Integer id, String title, Double duration) {
		super();
		this.id = id;
		this.title = title;
		this.duration = duration;
	}

	public OriginalInfo(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}
}
