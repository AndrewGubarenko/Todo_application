package com.AndriiHubarenko.TodoApplication;

import java.text.ParseException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws ParseException {
		TodoBody taskOne = new TodoBody();

//		taskOne.setName("Task 321");
//		taskOne.setComment("Test new data format");
//		taskOne.setDeadLine(Converter.dateConverter("22-01-2015"));
//		taskOne.setIsFinished(false);

		ITodoService service = new TodoService();
//		service.create(taskOne);
//		
		List<TodoBody> list = service.getTodoList();
//		TodoBody updatedTodo = list.get(0);
//		updatedTodo.setComment("final test today");
//		service.update(updatedTodo);
		for(TodoBody l: list) {
			System.out.println(Converter.fromTodoBodyToJson(l));
		}
 		System.out.println(service.todoCount());
	}

}
