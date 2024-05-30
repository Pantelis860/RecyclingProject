package com.example.recyclingapp;

import android.os.StrictMode;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

    public class OkHttpHandler {

        public OkHttpHandler() {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        public void logHistory(String url) throws Exception {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = RequestBody.create("",
                    MediaType.parse("text/plain"));
            Request request = new Request.Builder().url(url).method("POST",
                    body).build();
            Response response = client.newCall(request).execute();
            System.out.println("My Response: " + response.body().string());
        }





           ArrayList<User> fetching(String url) throws IOException, JSONException {
                ArrayList<User> uList = new ArrayList<>();
                OkHttpClient client = new OkHttpClient().newBuilder().build();
                RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
                Request request = new Request.Builder().url(url).method("POST", body).build();
                Response response = client.newCall(request).execute();
                String data = response.body().string();

                JSONObject json = new JSONObject(data);
                Iterator<String> keys = json.keys();
                while (keys.hasNext()) {
                    String ID = keys.next();
                    JSONArray infoArray = json.getJSONArray(ID);
                    uList.add(new User(
                            Integer.parseInt(ID),
                            infoArray.getJSONObject(0).getString("Name"),
                            infoArray.getJSONObject(1).getString("Email"),
                            infoArray.getJSONObject(2).getString("Username"),
                            infoArray.getJSONObject(3).getString("Password"),
                            Integer.parseInt(infoArray.getJSONObject(4).getString("Points")),
                            Double.parseDouble(infoArray.getJSONObject(5).getString("Aluminium_kg")),
                            Double.parseDouble(infoArray.getJSONObject(6).getString("Glass_kg")),
                            Double.parseDouble(infoArray.getJSONObject(7).getString("Paper_kg")),
                            Double.parseDouble(infoArray.getJSONObject(8).getString("Plastic_kg"))
                    ));
                }
                return uList;
            }
        }

