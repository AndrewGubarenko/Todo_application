package com.AndriiHubarenko.TodoApplication;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;

public class TodoServiceTest {

	@Test
	public void testCreateTodo() {
		TodoBody testTodo = new TodoBody();
		String name = "Test 4";
		String comment = "Test for creation";
		Date deadline = Converter.dateConverter("24-09-2018");
		boolean isFinished = false;
		ITodoService testService = new TodoService();
		testTodo.setName(name);
		testTodo.setComment(comment);
		testTodo.setDeadLine(deadline);
		testTodo.setIsFinished(isFinished);
		
		testService.create(testTodo);
		TodoBody test2Todo = testService.getLastTodo();
		
		assertEquals(name, test2Todo.getName());
		assertEquals(comment, test2Todo.getComment());
		assertEquals(deadline, test2Todo.getDeadLine());
		assertEquals(isFinished, test2Todo.getIsFinished());
	}
	
	@Test
	public void testUpdateTodo() {
		String comment = "#2 test Comment";

		ITodoService testService = new TodoService();
		TodoBody testTodo = testService.getFirstTodo();
		testTodo.setComment(comment);
		testService.update(testTodo);
		
		assertEquals(comment, testService.getFirstTodo().getComment());
	}
	
	@Test
	public void testGetTodoList() {
		ITodoService testService = new TodoService();
		List<TodoBody> list = testService.getTodoList();
		
		assertEquals(list.size(), testService.todoCount());
	}
	
}
