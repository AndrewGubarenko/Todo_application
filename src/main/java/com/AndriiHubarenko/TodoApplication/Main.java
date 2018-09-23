package com.AndriiHubarenko.TodoApplication;

import java.util.List;

public class Main {

	public static void main(String[] args) {
//		TodoBody taskOne = new TodoBody();
//		taskOne.setName("Task 1");
//		taskOne.setComment("First test comment of todo");
//		taskOne.setDeadLine(new Date());
//		taskOne.setIsFinished(false);

		ITodoService service = new TodoService();
//		service.create(taskOne);
		List<TodoBody> list = service.getTodoList();
		TodoBody updatedTodo = list.get(0);
		updatedTodo.setComment("NEW UPDATED COMMENT FOR TESTING");
		
 		System.out.println(service.update(updatedTodo));
	}

}
