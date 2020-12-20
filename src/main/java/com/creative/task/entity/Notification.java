package com.creative.task.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "comment_id")
	private Long commentId;
	@Column(name = "time")
	private Date time;
	@Column(name = "delivered")
	private boolean delivered;

	public Notification() { }
	public Notification(Long id, Long comment_id, Date time, boolean delivered) {
		this.id = id;
		this.commentId = comment_id;
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
