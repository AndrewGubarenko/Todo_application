package com.AndriiHubarenko.TodoApplication;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		TodoBody taskOne = new TodoBody();
		taskOne.setName("Task 2");
		taskOne.setComment("Second test comment of todo");
		taskOne.setDeadLine(new Date());
		taskOne.setIsFinished(false);

		ITodoService service = new TodoService();
		service.create(taskOne);
	}

}
