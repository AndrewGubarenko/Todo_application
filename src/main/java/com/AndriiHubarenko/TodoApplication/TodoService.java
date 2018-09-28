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

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @author Andrii Hubarenko
 * <p>This class implements an interfase ITodoService and has some additional methods to compact the code and make it easier to read.</p> 
 *
 */
public class TodoService implements ITodoService {
	private static final Logger LOGGER = Logger.getLogger(TodoService.class.getSimpleName());
	/**
	 * <p>The name of table in a server side</p>
	 */
	private static final String TABLE_NAME = "TodoApplication";
	/**
	 * <p>The basic URL to the DB</p>
	 */
	private static final String TARGET_URL = "https://api.backendless.com/006F4BC5-000C-0188-FF4C-BAB117143000/0C5FB588-52CA-F8E5-FF82-8E4947283E00/data/" + TABLE_NAME;
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
		ObjectMapper objectMapper = new ObjectMapper();
		writer.write(objectMapper.writeValueAsString(body));
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
	Date dateConverter(String stringDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date = sdf.parse(stringDate);
			return date;
		} catch (ParseException ex) {
			LOGGER.warn(ex.getMessage());
			return null;
		}
	}
	
	public Todo create(Todo body) {
		try {
			con = getConnection(true, "", "POST");

			sendRequest(con, body);
			System.out.println(getResponse(con));
			return body;

		} catch (IOException ex) {
			LOGGER.warn(ex.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	public Todo getFirstTodo() {
		try {
			con = getConnection(false, "/first", "GET");
			System.out.println();
			JSONObject jsonResult = new JSONObject(getResponse(con));
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(jsonResult.toString(), Todo.class);
		} catch (IOException ex) {
			LOGGER.warn(ex.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	public Todo getLastTodo() {
		try {
			con = getConnection(false, "/last", "GET");
			System.out.println();
			JSONObject jsonResult = new JSONObject(getResponse(con));
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(jsonResult.toString(), Todo.class);
		} catch (IOException ex) {
			LOGGER.warn(ex.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	
	public int todoCount() {
		try {
			con = getConnection(false, "/count", "GET");
			return Integer.parseInt(getResponse(con).trim());

		} catch (IOException ex) {
			LOGGER.warn(ex.getMessage());
			return 0;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	public List<Todo> getTodoList() {
		try {
			con = getConnection(false, "", "GET");
			JSONArray jsonResponse = new JSONArray(getResponse(con));
			List<Todo> result = new ArrayList<Todo>();
			for (int i = 0; i < jsonResponse.length(); i++) {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				result.add(objectMapper.readValue(jsonResponse.get(i).toString(), Todo.class));
			}
			return result;
		} catch (IOException | JSONException ex) {
			LOGGER.warn(ex.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	
	public Todo update(Todo body) {
		try {
			con = getConnection(true, "/" + body.getObjectId(), "PUT");

			sendRequest(con, body);
			System.out.println(getResponse(con));
			return body;

		} catch (IOException ex) {
			LOGGER.warn(ex.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
	
	public String remove(String id) {
		try {
			con = getConnection(false, "/" + id, "DELETE");

			return getResponse(con);

		} catch (IOException ex) {
			LOGGER.warn(ex.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
}
