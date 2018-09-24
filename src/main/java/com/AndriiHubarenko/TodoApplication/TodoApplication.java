package com.AndriiHubarenko.TodoApplication;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TodoApplication {
	/**
	 * @author Andrii Hubarenko
	 *         <p>Method main is for testing the TodoApplication using console.</p>
	 *         <p>To start using the application, you should to follow the
	 *            instructions on a screen and just to write the parameters in a
	 *            correct way.</p>
	 *         <p>Firstly you will be asked to enter the one of the methods:
	 *     	    <ul>
	 *       	  	<li>
	 *        	 		<p>create: needs to write the additional parameters. Just follow the
	 *       	    instructions on the screen.</p>
	 * 				</li>
	 *        	 	<li>
	 *        	 		<p>update: also needs the additional parameters from you. Shows to
	 *         	   you a list of Todo`s objects, then you need to choose the number
	 *         	   of object in a List, starting from 0, you are going to update.
	 *         	   Then continue following the instructions on the screen.</p>
	 * 				</li>
	 *       	  	<li>
	 *        	 		<p>getFirst: no needs any additional parameters, only correct name
	 *         		  of method. Returns the response in string format of first Todo
	 *         		  object in the DB table or a stacktrace of exception</p>
	 * 				</li>
	 *        	 	<li>
	 *         			<p>getLast: the same as the getFirst, just returns the last object
	 *        	    in DB table</p>
	 * 				</li>
	 *       	  	<li>
	 *        		 	<p>getList: also no needs additional parameters and returns a List
	 *        	 	  of all objects in DB table.</p>
	 * 				</li>
	 *        	 	<li>
	 *         			<p>countTodo: returns the int value on Todo`s objects number in a DB</p>
	 * 				</li>
	 *       	  	<li>
	 *         			<p>remove: shows to you a List of Todo`s objects and ask to choose
	 * 				 one, you are going to remove. You need to enter an index of the
	 *        	 	 object in the list, starting count from 0.</p>
	 * 				</li>
	 *        	 </ul>
	 *         	</p>
	 */
	
	/**
	 * The Maim method for running program.
	 * @param args standard array of arguments.
	 * @throws ParseException standard exception for parser.
	 * @throws JsonProcessingException standard exception for Jackson.
	 */
	public static void main(String[] args) throws ParseException, JsonProcessingException {
		String todoName;
		String todoComment;
		Date deadLine;
		boolean isFinished;
		String todoId;
		String strMethod;
		String temp;

		ITodoService service = new TodoService();
		ObjectMapper objectMapper = new ObjectMapper();

		Scanner reader = new Scanner(System.in);
		System.out.print("Enter the method: ");

		while (!(strMethod = reader.nextLine()).equals("exit")) {

			switch (strMethod) {
			/**
			 * <p>Calls method create(Todo todo) from TodoService.</p> 
			 */
			case "create":

				System.out.print("Enter the name of todo: ");
				todoName = reader.nextLine();
				System.out.print("Enter the todoComment: ");
				todoComment = reader.nextLine();
				System.out.print("Enter the end Date of todo in format \"24-09-2018\": ");
				String strCreateDate = reader.nextLine();
				if(strCreateDate.matches("^(((0[1-9]|[12]\\d|3[01])\\-(0[13578]|1[02])\\-((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\-(0[13456789]|1[012])\\-((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\-02\\-((19|[2-9]\\d)\\d{2}))|(29\\-02\\-((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$"))
					deadLine = ((TodoService) service).dateConverter(strCreateDate);
				else {
					System.out.println();
					System.out.println("Wrong date format. Try again ");
					System.out.print("Enter the method: ");
					break;
				}
					
				Todo todo = new Todo();
				todo.setName(todoName);
				todo.setComment(todoComment);
				todo.setDeadLine(deadLine);
				todo.setIsFinished(false);
				service.create(todo);
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			/**
			 * <p>Calls method update(Todo todo) from TodoService.</p> 
			 */
			case "update":

				List<Todo> list = service.getTodoList();
				for (Todo t : list) {
					System.out.println((objectMapper.writeValueAsString(t)) + " todiId: " + t.getObjectId());
				}
				System.out.println();
				System.out.print("Enter the number of todo you are going to update begin from 0: ");
				Integer num = Integer.parseInt(reader.nextLine());
				Todo updateedTodo = list.get(num);

				System.out.print("Enter the name of todo or leave it unchanged by pressed Enter: ");
				if ((temp = reader.nextLine()).isEmpty())
					todoName = updateedTodo.getName();
				else
					todoName = temp;

				System.out.print("Enter the comment of todo or leave it unchanged by pressed Enter: ");
				if ((temp = reader.nextLine()).isEmpty())
					todoComment = updateedTodo.getComment();
				else
					todoComment = temp;

				System.out.print("Enter the end data of todo in format \\\"24-09-2018\\\" or leave it unchanged by pressed Enter: ");
				String strUpdateDate = reader.nextLine();
				if ((temp = reader.nextLine()).isEmpty())
					deadLine = updateedTodo.getDeadLine();
				else
					if(strUpdateDate.matches("^(((0[1-9]|[12]\\d|3[01])\\-(0[13578]|1[02])\\-((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\-(0[13456789]|1[012])\\-((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\-02\\-((19|[2-9]\\d)\\d{2}))|(29\\-02\\-((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$"))
						deadLine = ((TodoService) service).dateConverter(strUpdateDate);
					else {
						System.out.println();
						System.out.println("Wrong date format. Try again ");
						System.out.print("Enter the method: ");
						break;
					}

				System.out.print("Enter is todo out of date in format: \"true\" (means yeas) or \"false\" (means no) or leave it unchanged by pressed Enter: ");
				if ((temp = reader.nextLine()).isEmpty())
					isFinished = updateedTodo.getIsFinished();
				else
					isFinished = Boolean.parseBoolean(temp);

				updateedTodo.setName(todoName);
				updateedTodo.setComment(todoComment);
				updateedTodo.setDeadLine(deadLine);
				updateedTodo.setIsFinished(isFinished);
				service.update(updateedTodo);
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			/**
			 * <p>Calls method getFirstTodo() from TodoService.</p> 
			 */
			case "getFirst":
				Todo todoFirst = service.getFirstTodo();
				System.out.println(objectMapper.writeValueAsString(todoFirst) + " todiId: " + todoFirst.getObjectId());
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			/**
			 * <p>Calls method getLastTodo() from TodoService.</p> 
			 */	
			case "getLast":
				Todo todoLast = service.getLastTodo();
				System.out.println(objectMapper.writeValueAsString(todoLast) + " todiId: " + todoLast.getObjectId());
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			/**
			 * <p>Calls method getTodoList() from TodoService.</p> 
			 */
			case "getList":
				List<Todo> todoList = service.getTodoList();
				for (Todo t : todoList) {
					System.out.println(objectMapper.writeValueAsString(t) + " todiId: " + t.getObjectId());
				}
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			/**
			 * <p>Calls method countTodo() from TodoService.</p> 
			 */
			case "countTodo":
				System.out.println(service.todoCount());
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			/**
			 * <p>Calls method remove from TodoService.</p> 
			 */
			case "remove":
				List<Todo> listForRemove = service.getTodoList();
				for (Todo t : listForRemove) {
					System.out.println(objectMapper.writeValueAsString(t) + " todiId: " + t.getObjectId());
				}
				System.out.println();
				System.out.print("Enter the number of todo you are going to remove begin from 0: ");
				Integer numOfRemove = Integer.parseInt(reader.nextLine());
				todoId = listForRemove.get(numOfRemove).getObjectId();
				System.out.println(service.remove(todoId));
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			/**
			 * <p>Default actions in a case of wrong input datas.</p> 
			 */
			default: 
				System.out.println("Wrong datas input! Try again");
				System.out.println();
				System.out.print("Enter the method: ");
				break;
			}
		}
		reader.close();
	}
}
