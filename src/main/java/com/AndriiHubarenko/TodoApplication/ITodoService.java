package com.AndriiHubarenko.TodoApplication;

import java.util.List;
/**
 * 
 * @author Andrii Hubarenko
 * <p>This interface explains the main methods, that must be implemented by any class of services, to get a completely workable application.</p> 
 *
 */
public interface ITodoService {
	Todo create(Todo body);
	Todo getFirstTodo();
	Todo getLastTodo();
	List<Todo> getTodoList();
	int todoCount();
	Todo update(Todo body);
	String remove(String id);
}
