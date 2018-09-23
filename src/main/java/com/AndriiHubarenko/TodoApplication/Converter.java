package com.AndriiHubarenko.TodoApplication;

import java.util.Date;

import org.json.JSONObject;

public class Converter {
	public static TodoBody fromJsonToTodoBody(JSONObject target) {
		TodoBody result = new TodoBody();
		
		result.setObjectId(target.getString("objectId"));
		result.setName(target.getString("Name"));
		result.setComment(target.getString("Comment"));
		result.setDeadLine(new Date((Long) target.get("DeadLine")));
		result.setIsFinished(target.getBoolean("IsFinished"));
		
		return result;
	}
	
	public static JSONObject fromTodoBodyToJson(TodoBody target) {
		JSONObject result = new JSONObject();
		
		result.put("Name", target.getName());
		result.put("Comment", target.getComment());
		result.put("DeadLine", target.getDeadLine());
		result.put("IsFinished", target.getIsFinished());
		
		return result;
	}
}
