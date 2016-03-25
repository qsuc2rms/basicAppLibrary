package com.baplib.util.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BapJsonParser {

	public static Gson getGson() {
		return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
	}

	public static String getJson(Object obj) {
		Gson gson = getGson();
		return gson.toJson(obj);
	}

	public static <T> T parseJson2Object(String json, Class<T> classofT) {
		Gson gson = getGson();
		return gson.fromJson(json, classofT);
	}

	public static JSONObject parseObject2JSONObject(Object obj) throws JSONException {
		String json = getJson(obj);
		JSONObject jobj = new JSONObject(json);
		return jobj;
	}

	public static JSONObject parseJson2JSONObject(String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		return obj;
	}
}
