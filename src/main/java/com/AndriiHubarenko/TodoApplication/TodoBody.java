package com.AndriiHubarenko.TodoApplication;

import java.util.Date;

import org.json.JSONObject;

public class TodoBody{

	private int objectId;
	private String name;
	private String comment;
	private Date deadLine;
	private Boolean isFinished;

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public Boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("Name", name);
		json.put("Comment", comment);
		json.put("DeadLine", deadLine);
		json.put("IsFinished", isFinished);
		return json;
	}
}
