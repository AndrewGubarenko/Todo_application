package com.AndriiHubarenko.TodoApplication;

import java.util.List;

public interface ITodoService {
	TodoBody create(TodoBody body);
	void getTodo(Long id);
	List<TodoBody> getTodoList();
	TodoBody update(TodoBody body);
	String remove(String id);
}
