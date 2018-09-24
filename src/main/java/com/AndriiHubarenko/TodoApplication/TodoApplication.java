package com.AndriiHubarenko.TodoApplication;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TodoApplication {
	/**
	 * 
	 * @param args
	 * @throws ParseException
	 */

	public static void main(String[] args) throws ParseException {
		String todoName;
		String todoComment;
		Date deadLine;
		boolean isFinished;
		String todoId;
		String strMethod;
		String temp;

		ITodoService service = new TodoService();

		Scanner reader = new Scanner(System.in);
		System.out.print("Enter the method: ");

		while (!(strMethod = reader.nextLine()).equals("exit")) {

			switch (strMethod) {
			case "create":
				
				System.out.print("Enter the name of todo: ");
				todoName = reader.nextLine();
				System.out.print("Enter the todoComment: ");
				todoComment = reader.nextLine();
				System.out.print("Enter the end Date of todo in format \"24-09-2018\": ");
				deadLine = TodoService.dateConverter(reader.nextLine());
				Todo todo = new Todo();
				todo.setName(todoName);
				todo.setComment(todoComment);
				todo.setDeadLine(deadLine);
				todo.setIsFinished(false);
				service.create(todo);
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			case "update":
				
				List<Todo> list = service.getTodoList();
				for (Todo t : list) {
					System.out.println(TodoService.fromTodoBodyToJson(t).toString() + " todiId: " + t.getObjectId());
				}
				System.out.println();
				System.out.print("Enter the number of todo you are going to update begin from 0: ");
				Integer num = Integer.parseInt(reader.nextLine());
				Todo updateedTodo = list.get(num);
				
				System.out.print("Enter the name of todo or leave it unchanged by pressed Enter: ");
				if((temp = reader.nextLine()).isEmpty()) 
					todoName = updateedTodo.getName();
				 else  todoName = temp;
				
				System.out.print("Enter the comment of todo or leave it unchanged by pressed Enter: ");
				if((temp = reader.nextLine()).isEmpty()) 
					todoComment = updateedTodo.getComment();
				 else  todoComment = temp;
				
				System.out.print("Enter the end data of todo in format \\\"24-09-2018\\\" or leave it unchanged by pressed Enter: ");
				if((temp = reader.nextLine()).isEmpty()) 
					deadLine = updateedTodo.getDeadLine();
				 else  deadLine = TodoService.dateConverter(temp);
				
				System.out.print("Enter is todo out of date in format: \"true\" (means yeas) or \"false\" (means no) or leave it unchanged by pressed Enter: ");
				if((temp = reader.nextLine()).isEmpty()) 
					isFinished = updateedTodo.getIsFinished();
				 else  isFinished = Boolean.parseBoolean(temp);
				
				updateedTodo.setName(todoName);
				updateedTodo.setComment(todoComment);
				updateedTodo.setDeadLine(deadLine);
				updateedTodo.setIsFinished(isFinished);
				service.update(updateedTodo);
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			case "getFirst":
				Todo todoFirst = service.getFirstTodo();
				System.out.println(TodoService.fromTodoBodyToJson(todoFirst).toString() + " todiId: " + todoFirst.getObjectId());
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			case "getLast":
				Todo todoLast = service.getLastTodo();
				System.out.println(TodoService.fromTodoBodyToJson(todoLast).toString() + " todiId: " + todoLast.getObjectId());
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			case "getList":
				List<Todo> todoList = service.getTodoList();
				for (Todo t : todoList) {
					System.out.println(TodoService.fromTodoBodyToJson(t).toString() + " todiId: " + t.getObjectId());
				}
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			case "countTodo":
				System.out.println(service.todoCount());
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			case "remove":
				List<Todo> listForRemove = service.getTodoList();
				for (Todo t : listForRemove) {
					System.out.println(TodoService.fromTodoBodyToJson(t).toString() + " todiId: " + t.getObjectId());
				}
				System.out.println();
				System.out.print("Enter the number of todo you are going to remove begin from 0: ");
				Integer numOfRemove = Integer.parseInt(reader.nextLine());
				todoId = listForRemove.get(numOfRemove).getObjectId();
				System.out.println(service.remove(todoId));
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			}
		}
		reader.close();
	}
}
