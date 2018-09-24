package com.AndriiHubarenko.TodoApplication;

import java.util.List;

public interface ITodoService {
	Todo create(Todo body);
	Todo getFirstTodo();
	Todo getLastTodo();
	List<Todo> getTodoList();
	int todoCount();
	Todo update(Todo body);
	String remove(String id);
}
