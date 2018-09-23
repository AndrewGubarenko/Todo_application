package com.AndriiHubarenko.TodoApplication;

import org.json.JSONArray;

public interface ITodoService {
	String create(TodoBody body);
	void getTodo(Long id);
	JSONArray getTodoList();
	void update(TodoBody body);
	String remove(String id);
}
