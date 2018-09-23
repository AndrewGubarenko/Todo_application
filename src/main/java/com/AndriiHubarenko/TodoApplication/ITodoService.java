package com.AndriiHubarenko.TodoApplication;

public interface ITodoService {
	String create(TodoBody body);
	void getTodo(Long id);
	void getTodoList();
	void update(TodoBody body);
	void remove(Long id);
}
