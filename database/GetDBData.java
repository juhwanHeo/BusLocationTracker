package database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.URL;

public class GetDBData {

	// DB Connection
	private static final String address = "http://ipaddress getting data";
	private static final String TAG_RESULTS = "result";
	private static final String TAG_ID = "str_user_id";
	private static final String TAG_TIME = "str_datetime";
	private static final String TAG_LAT = "str_latitude";
	private static final String TAG_LON = "str_longitude";

	// json
	private String myJSON;
	private JSONArray locations = null;

	// value getting at DB
	private String id;
	private String time;
	private String latitude;
	private String longitude;

	public String getJSONData() throws Exception {
		String buf;
		URL url = new URL(address);
		URLConnection conn = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		while ((buf = br.readLine()) != null) {
			myJSON = buf;
		}
		return myJSON;
	}

	public void getDBData() {
		if (myJSON != null) {
			try {
				JSONObject jsonobj = (JSONObject) JSONValue.parse(myJSON);
				locations = (JSONArray) jsonobj.get(TAG_RESULTS);
				JSONObject c = (JSONObject) locations.get(0);

				id = (String) c.get(TAG_ID);
				time = (String) c.get(TAG_TIME);
				latitude = (String) c.get(TAG_LAT);
				longitude = (String) c.get(TAG_LON);
			} catch (Exception e) {
				System.out.println("DB데이터 없음");
			}
		}
		// JSONObject jObj = (JSONObject) JSONValue.parse(jsonString);
		// JSONArray jArray = (JSONArray) jObj.get("results");
		// jObj = (JSONObject) jArray.get(0);
		// return (String) jObj.get("formatted_address");
	}

	public double getLatitude() {
		return Double.parseDouble(latitude);
	}

	public double getLongitude() {
		return Double.parseDouble(longitude);
	}

	public String getDBId() {
		return this.id;
	}

	public String getDBTime() {
		return this.time;
	}

	public String getDBLatitude() {
		return this.latitude;
	}

	public String getDBLongitude() {
		return this.longitude;
	}
}
