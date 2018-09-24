package com.AndriiHubarenko.TodoApplication;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author Andrii Hubarenko
 *<p>The Todo object</p>
 */
public class Todo {

	private String objectId;
	private String name;
	private String comment;
	private Date deadLine;
	private Boolean isFinished;
	
	@JsonProperty("objectId")
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty("comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	@JsonProperty("deadLine")
	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	@JsonProperty("isFinished")
	public Boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}

}
