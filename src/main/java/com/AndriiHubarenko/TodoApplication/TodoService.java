package com.AndriiHubarenko.TodoApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TodoService implements ITodoService {
	String tableName = "TodoApplication";
	String targetURL = "https://api.backendless.com/006F4BC5-000C-0188-FF4C-BAB117143000/0C5FB588-52CA-F8E5-FF82-8E4947283E00/data/" + tableName;
	HttpURLConnection con = null;

	public TodoBody create(TodoBody body) {
		try {
			URL url = new URL(targetURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			
			//Send request
		    DataOutputStream wr = new DataOutputStream (con.getOutputStream());
		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
		    writer.write(Converter.fromTodoBodyToJson(body).toString());
		    writer.flush();
		    writer.close();

		    //Get Response  
		    InputStream is = con.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuffer response = new StringBuffer();
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    return Converter.fromJsonToTodoBody(new JSONObject(response));
			
		} catch (MalformedURLException ex) {
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

	public void getTodo(Long id) {
		// TODO Auto-generated method stub

	}

	public List<TodoBody> getTodoList() {
		try {
			URL url = new URL(targetURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(false);
			con.setDoInput(true);

		    //Get Response  
		    InputStream is = con.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuffer response = new StringBuffer();
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    JSONArray jsonResponse = new JSONArray(response.toString());
		    List<TodoBody> result = new ArrayList<TodoBody>();
		    
		    for(int i = 0; i < jsonResponse.length(); i++) {
		    	result.add(Converter.fromJsonToTodoBody((JSONObject) jsonResponse.get(i)));
		    }
		    
		    return result;
			
		} catch (MalformedURLException ex) {
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
			URL url = new URL(targetURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			
			//Send request
		    DataOutputStream wr = new DataOutputStream (con.getOutputStream());
		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
		    writer.write(Converter.fromTodoBodyToJson(body).toString());
		    writer.flush();
		    writer.close();

		    //Get Response  
		    InputStream is = con.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuffer response = new StringBuffer();
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    return Converter.fromJsonToTodoBody(new JSONObject(response));
			
		} catch (MalformedURLException ex) {
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
			URL url = new URL(targetURL + "/" + id);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);

			//Get Response  
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
			
		} catch (MalformedURLException ex) {
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
