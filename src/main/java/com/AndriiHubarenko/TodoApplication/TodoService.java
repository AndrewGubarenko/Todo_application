package com.AndriiHubarenko.TodoApplication;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TodoService implements ITodoService {
	HttpURLConnection con = null;

	public TodoBody create(TodoBody body) {
		try {
			con = ConnectionUtils.getConnection(true, "", "POST");
			
			ConnectionUtils.sendRequest(con, body);
			System.out.println(ConnectionUtils.getResponse(con));
			return body;

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}

	public TodoBody getTodo(String parameter) {
		TodoBody result = new TodoBody();
		
		return result;
	}

	public int todoCount() {
		try {
			con = ConnectionUtils.getConnection(false, "/count", "GET");	
			return Integer.parseInt(ConnectionUtils.getResponse(con).trim());

		} catch (IOException ex) {
			ex.printStackTrace();
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}

	public List<TodoBody> getTodoList() {
		try {
			con = ConnectionUtils.getConnection(false, "", "GET");
			JSONArray jsonResponse = new JSONArray(ConnectionUtils.getResponse(con));
			List<TodoBody> result = new ArrayList<TodoBody>();
			for (int i = 0; i < jsonResponse.length(); i++) {
				result.add(Converter.fromJsonToTodoBody((JSONObject) jsonResponse.get(i)));
			}
			return result;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}

	public TodoBody update(TodoBody body) {
		try {
			con = ConnectionUtils.getConnection(true, "/" + body.getObjectId(), "PUT");

			ConnectionUtils.sendRequest(con, body);
			System.out.println(ConnectionUtils.getResponse(con));
			return body;

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}

	public String remove(String id) {
		try {
			con = ConnectionUtils.getConnection(false, "/" + id, "DELETE");

			return ConnectionUtils.getResponse(con);

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
}
