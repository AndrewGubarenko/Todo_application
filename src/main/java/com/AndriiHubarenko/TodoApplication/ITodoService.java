package com.AndriiHubarenko.TodoApplication;

import java.util.List;

public interface ITodoService {
	TodoBody create(TodoBody body);
	TodoBody getFirstTodo();
	TodoBody getLastTodo();
	List<TodoBody> getTodoList();
	int todoCount();
	TodoBody update(TodoBody body);
	String remove(String id);
}
