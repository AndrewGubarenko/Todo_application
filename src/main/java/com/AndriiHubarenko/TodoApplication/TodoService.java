package com.AndriiHubarenko.TodoApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * 
 * @author Andrii Hubarenko
 * <p>This class implements an interfase ITodoService and has some additional methods to compact the code and make it easier to read.</p> 
 *
 */
public class TodoService implements ITodoService {
	/**
	 * <p>The name of table in a server side</p>
	 */
	private static String TABLE_NAME = "TodoApplication";
	/**
	 * <p>The basic URL to the DB</p>
	 */
	private static String TARGET_URL = "https://api.backendless.com/006F4BC5-000C-0188-FF4C-BAB117143000/0C5FB588-52CA-F8E5-FF82-8E4947283E00/data/" + TABLE_NAME;
	private HttpURLConnection con = null;
	/**
	 * <p>Method getConnection(Boolean doOutput, String UrlParams, String method) from TodoService.</p> 
	 * @param doOutput Sets the value of the doOutput field for this URLConnection to the specified value. 
	 * @param urlParams String parameter? which is used for creating correct URl to REST request.
	 * @param method one of the REST methods: POST, GET, PUT, DELETE.
	 * @throws IOException.
	 * @return HttpURLConnection.
	 */
	private HttpURLConnection getConnection(Boolean doOutput, String urlParams, String method) throws IOException {
		HttpURLConnection con = null;
		URL url = new URL(TARGET_URL + urlParams);
	 	con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(method);
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(doOutput);

		return con;
	}
	/**
	 * <p>Method sendRequest(HttpURLConnection con, Todo body) from TodoService.</p> 
	 * @param con The HttpURLConnection instance to the DB.
	 * @param body Todo object you need to get.
	 * @throws IOException.
	 * @return HttpURLConnection.
	 */
	private void sendRequest(HttpURLConnection con, Todo body) throws IOException {
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
		writer.write(fromTodoBodyToJson(body).toString());
		writer.flush();
		writer.close();
	}
	/**
	 * <p>Method getResponse(HttpURLConnection con) from TodoService.</p> 
	 * @param con The HttpURLConnection instance to the DB.
	 * @throws IOException .
	 * @return HttpURLConnection.
	 */
	private String getResponse(HttpURLConnection con) throws IOException {
		InputStream is = con.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		StringBuffer response = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			response.append(line);
			response.append('\r');
		}
		rd.close();
		return response.toString();
	}
	/**
	 * <p>Method dateConverter(String stringDate) to remove the time from the date. </p> 
	 * @param stringDate.
	 * @return date in a format without time.
	 */
	static Date dateConverter(String stringDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date = sdf.parse(stringDate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 *<p>Method fromJsonToTodoBody(JSONObject target) get aJSON object and convert it to Todo object.</p> 
	 * @param target.
	 * @return result.
	 * @throws ParseException.
	 */
	static Todo fromJsonToTodoBody(JSONObject target) throws ParseException {
		Todo result = new Todo();

		result.setObjectId(target.getString("objectId"));
		result.setName(target.getString("Name"));
		result.setComment(target.getString("Comment"));
		result.setDeadLine(new Date((Long) target.get("DeadLine")));
		result.setIsFinished(target.getBoolean("IsFinished"));

		return result;
	}
	/**
	 * <p>Method fromTodoBodyToJson(Todo target) get the Todo object and convert it to JSON object.</p> 
	 * @param target.
	 * @return result.
	 */
	static JSONObject fromTodoBodyToJson(Todo target) {
		JSONObject result = new JSONObject();

		result.put("Name", target.getName());
		result.put("Comment", target.getComment());
		result.put("DeadLine", target.getDeadLine());
		result.put("IsFinished", target.getIsFinished());

		return result;
	}
	/**
	 * <p>Calls method create(Todo todo) from TodoService.</p> 
	 * @param todo object you have just created.
	 * @return todo or print a stacktrase of an exception.
	 */
	public Todo create(Todo body) {
		try {
			con = getConnection(true, "", "POST");

			sendRequest(con, body);
			System.out.println(getResponse(con));
			return body;

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	/**
	 * <p>Calls method getFirstTodo() from TodoService.</p> 
	 * @return todo first object in DB or print a stacktrase of an exception.
	 */
	public Todo getFirstTodo() {
		try {
			con = getConnection(false, "/first", "GET");
			System.out.println();
			JSONObject jsonResult = new JSONObject(getResponse(con));
			return fromJsonToTodoBody(jsonResult);
		} catch (IOException | ParseException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	/**
	 * <p>Calls method getLastTodo() from TodoService.</p> 
	 * @return todo last object in DB or print a stacktrase of an exception.
	 */	
	public Todo getLastTodo() {
		try {
			con = getConnection(false, "/last", "GET");
			System.out.println();
			JSONObject jsonResult = new JSONObject(getResponse(con));
			return fromJsonToTodoBody(jsonResult);
		} catch (IOException | ParseException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	/**
	 * <p>Calls method countTodo() from TodoService.</p> 
	 * @return size the int value of todo`s number in DB or print a stacktrase of an exception.
	 */
	public int todoCount() {
		try {
			con = getConnection(false, "/count", "GET");
			return Integer.parseInt(getResponse(con).trim());

		} catch (IOException ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	/**
	 * <p>Calls method getTodoList() from TodoService.</p> 
	 * @return result the List<Todo> of all objects in the DB or print a stacktrase of an exception.
	 */
	public List<Todo> getTodoList() {
		try {
			con = getConnection(false, "", "GET");
			JSONArray jsonResponse = new JSONArray(getResponse(con));
			List<Todo> result = new ArrayList<Todo>();
			for (int i = 0; i < jsonResponse.length(); i++) {
				result.add(fromJsonToTodoBody((JSONObject) jsonResponse.get(i)));
			}
			return result;
		} catch (IOException | JSONException | ParseException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	/**
	 * <p>Calls method update(Todo todo) from TodoService.</p> 
	 * @param todo object from DB you are going to update.
	 * @return todo or print a stacktrase of an exception.
	 */
	public Todo update(Todo body) {
		try {
			con = getConnection(true, "/" + body.getObjectId(), "PUT");

			sendRequest(con, body);
			System.out.println(getResponse(con));
			return body;

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	/**
	 * <p>Calls method remove from TodoService.</p> 
	 * @param todoId the String value of todo`s id, you are going to remove or print a stacktrase of an exception.
	 */
	public String remove(String id) {
		try {
			con = getConnection(false, "/" + id, "DELETE");

			return getResponse(con);

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
}
