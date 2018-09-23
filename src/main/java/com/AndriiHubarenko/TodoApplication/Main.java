package com.AndriiHubarenko.TodoApplication;
import java.util.List;

public class Main {

	public static void main(String[] args) {
//		TodoBody taskOne = new TodoBody();
//		taskOne.setName("Task 6");
//		taskOne.setComment("Number 6 test comment of todo");
//		taskOne.setDeadLine(new Date());
//		taskOne.setIsFinished(false);

		ITodoService service = new TodoService();
//		service.create(taskOne);
		
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
