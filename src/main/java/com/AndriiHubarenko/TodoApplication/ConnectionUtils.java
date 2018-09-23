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

public class ConnectionUtils {
	static String tableName = "TodoApplication";
	static String targetURL = "https://api.backendless.com/006F4BC5-000C-0188-FF4C-BAB117143000/0C5FB588-52CA-F8E5-FF82-8E4947283E00/data/" + tableName;

	public static HttpURLConnection getConnection(Boolean doOutput, String UrlParams, String method) throws IOException {
		HttpURLConnection con = null;
		URL url = new URL(targetURL + UrlParams);
	 	con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(method);
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(doOutput);

		return con;
	}
	
	public static void sendRequest(HttpURLConnection con, TodoBody body) throws IOException {
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
		writer.write(Converter.fromTodoBodyToJson(body).toString());
		writer.flush();
		writer.close();
	}
	
	public static String getResponse(HttpURLConnection con) throws IOException {
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
}
