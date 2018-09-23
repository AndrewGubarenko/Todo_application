package com.AndriiHubarenko.TodoApplication;

import org.json.JSONArray;

public class Main {

	public static void main(String[] args) {
//		TodoBody taskOne = new TodoBody();
//		taskOne.setName("Task 1");
//		taskOne.setComment("First test comment of todo");
//		taskOne.setDeadLine(new Date());
//		taskOne.setIsFinished(false);

		ITodoService service = new TodoService();
//		service.create(taskOne);
		JSONArray list = service.getTodoList();		
 		System.out.println(list);
	}

}
