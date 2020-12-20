package com.creative.task.domain.dto;

import java.util.Date;

public class NotificationDto {

	private Long id;
	private Long commentId;
	private Date time;
	private boolean delivered;

	public NotificationDto() { }
	public NotificationDto(Long id, Long commentId, Date time, boolean delivered) {
		this.id = id;
		this.commentId = commentId;
		this.time = time;
		this.delivered = delivered;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isDelivered() {
		return delivered;
	}
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
}
