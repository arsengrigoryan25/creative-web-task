package com.creative.task.domain.dto;

public class CommentDto_ extends CommentDto{

	private boolean commentDeleted;

	public CommentDto_(CommentDto dto, boolean commentCreated) {
		super(dto);
		this.commentDeleted = commentCreated;
	}

	public boolean isCommentDeleted() {
		return commentDeleted;
	}
	public void setCommentDeleted(boolean commentDeleted) {
		this.commentDeleted = commentDeleted;
	}
}
