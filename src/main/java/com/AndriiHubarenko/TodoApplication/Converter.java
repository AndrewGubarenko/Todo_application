package com.AndriiHubarenko.TodoApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class Converter {
	
	public static Date dateConverter(String stringDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		try {
			Date date = sdf.parse(stringDate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static TodoBody fromJsonToTodoBody(JSONObject target) throws ParseException {
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
