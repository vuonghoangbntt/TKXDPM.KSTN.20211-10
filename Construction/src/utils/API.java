package utils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Class cung cap cac phuong thuc giup gui request len server va nhan du lieu tra ve tu server
 */
public class API {
	/**
	 * Thuoc tinh giup format ngay thang theo dinh dang
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	/**
	 * Thuoc tinh giup log thong tin ra console
	 */
	private static final Logger LOGGER = Utils.getLogger(Utils.class.getName());
	/**
	 * Phuong thuc giup goi cac api dang GET
	 * @param url: duong dan toi server can request
	 * @param token: doan ma de xac thuc nguoi dung
	 * @return response: phan hoi tu server (type String)
	 * @throws Exception
	 * */
	public static String get(String url, String token) throws Exception {

		// phan 1: setup
		HttpURLConnection conn = setupConnection(url, "GET", token);

		// phan 2: doc du lieu tra ve tu server
		String response = readResponse(conn);
		return response;
	}

	int var;
	/**
	 * Phuong thuc giup goi cac API dang post (thanh toan,...)
	 * @param url : duong dan toi server can request
	 * @param data : du lieu dua toi server (dang JSON)
	 * @return response: phan hoi tu server (type String)
	 * @throws Exception
	 */
	public static String post(String url, String data
//			, String token
	) throws IOException {

		// phan 1: setup
		HttpURLConnection conn = setupConnection(url, "PATCH", null);

		// phan 2: gui du lieu
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(data);
		writer.close();

		// phan 3: doc du lieu gui lai
		String response = readResponse(conn);
		return response;
	}
	/**
	 * Phuong thuc cho phep goi cac giao thuc API khac nhau nhu PATCH, PUT,... (chi hoat dong voi Java 11)
	 * @deprecated chi hoat dong voi java <= 11
	 * @param methods : giao thuc muon su dung (PATCH, PUT)
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Phuong thuc thiet lap connection den server
	 * @param url: duong dan toi server can request
	 * @param method: giao thuc api
	 * @param token : doan ma cung cap de xac thuc nguoi dung
	 * @return connection
	 * @throws IOException
	 */
	private static HttpURLConnection setupConnection(String url, String method, String token) throws IOException{
		LOGGER.info("Request URL: " + url + "\n");
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		if(token!=null) {
			conn.setRequestProperty("Authorization", "Bearer " + token);
		}
		return conn;
	}

	/**
	 * Phuong thuc doc du lieu tra ve tu server
	 * @param conn : connection toi server
	 * @return response: phan hoi tra ve tu server
	 * @throws IOException
	 */
	private static String readResponse(HttpURLConnection conn) throws IOException{
		BufferedReader in;
		String inputLine;
		if (conn.getResponseCode() / 100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);
		in.close();
		LOGGER.info("Respone Info: " + response.substring(0, response.length()-1));
		return response.substring(0, response.length()-1);
	}
}
