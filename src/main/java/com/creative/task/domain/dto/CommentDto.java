package com.creative.task.domain.dto;

import java.util.Date;

public class CommentDto {
	private Long id;
	private String comment;
	private Date time;

	public CommentDto() { }
	public CommentDto(CommentDto dto) {
		this.id = dto.getId();
		this.comment = dto.getComment();
		this.time = dto.getTime();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
