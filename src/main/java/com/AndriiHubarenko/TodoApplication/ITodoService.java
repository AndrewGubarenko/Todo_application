package com.AndriiHubarenko.TodoApplication;

import java.util.List;
/**
 * 
 * @author Andrii Hubarenko
 * This interface explains the main methods, that must be implemented by any class of services, to get a completely workable application.
 *
 */
public interface ITodoService {
	/**
	 * Calls method create(Todo todo) from TodoService.
	 * @param body object you have just created.
	 * @return todo or print a stacktrase of an exception.
	 */
	Todo create(Todo body);
	/**
	 * Calls method getFirstTodo() from TodoService.
	 * @return todo first object in DB or print a stacktrase of an exception.
	 */
	Todo getFirstTodo();
	/**
	 * Calls method getLastTodo() from TodoService.
	 * @return todo last object in DB or print a stacktrase of an exception.
	 */	
	Todo getLastTodo();
	/**
	 * Calls method getTodoList() from TodoService.
	 * @return result the List of all objects in the DB or print a stacktrase of an exception.
	 */
	List<Todo> getTodoList();
	/**
	 * Calls method countTodo() from TodoService.
	 * @return size the int value of todo`s number in DB or print a stacktrase of an exception.
	 */
	int todoCount();
	/**
	 * Calls method update(Todo todo) from TodoService.
	 * @param body object from DB you are going to update.
	 * @return todo or print a stacktrase of an exception.
	 */
	Todo update(Todo body);
	/**
	 * Calls method remove from TodoService.
	 * @param id the String value of todo`s id, you are going to remove or print a stacktrase of an exception.
	 * @return string response, the time of operation in normal conditions.
	 */
	String remove(String id);
}
