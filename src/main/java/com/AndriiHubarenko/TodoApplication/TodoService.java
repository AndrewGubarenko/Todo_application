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

public class TodoService implements ITodoService {
	String tableName = "TodoApplication";
	String targetURL = "https://api.backendless.com/006F4BC5-000C-0188-FF4C-BAB117143000/0C5FB588-52CA-F8E5-FF82-8E4947283E00/data/" + tableName;
	HttpURLConnection con = null;

	public String create(TodoBody body) {
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
		    writer.write(body.toJson().toString());
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

	public void getTodo(Long id) {
		// TODO Auto-generated method stub

	}

	public void getTodoList() {
		// TODO Auto-generated method stub

	}

	public void update(TodoBody body) {
		// TODO Auto-generated method stub

	}

	public void remove(Long id) {
		// TODO Auto-generated method stub

	}

}
