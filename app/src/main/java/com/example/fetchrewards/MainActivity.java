package com.example.fetchrewards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv_info;
    RecyclerView rv_data;
    Button btn_id, btn_name, btn_list;

    public static final String websiteInfo = "https://fetch-hiring.s3.amazonaws.com/hiring.json";

    List<UserInfo> theMainList;
    MyAdapter adapter;
    boolean swapper = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_info = findViewById(R.id.tv_info);
        rv_data = findViewById(R.id.rv_data);
        theMainList = new ArrayList<>();
        btn_id = findViewById(R.id.btn_id);
        btn_name = findViewById(R.id.btn_name);
        btn_list = findViewById(R.id.btn_list);




        btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapper = !swapper;
                theMainList = new ArrayList<>();
                extractInfoByName(swapper);

            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapper = !swapper;
                theMainList = new ArrayList<>();
                extractInfoByIdList(swapper);

            }
        });




        btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapper = !swapper;
                theMainList = new ArrayList<>();
                extractInfoById(swapper);

            }
        });






    }


    private void extractInfoByIdList(boolean swap) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, websiteInfo, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject userObject = response.getJSONObject(i);

                        UserInfo userInfo = new UserInfo();
                        userInfo.setId(userObject.getString("id"));
                        userInfo.setListId(userObject.getString("listId"));
                        userInfo.setName(userObject.getString("name"));
                        //if(!userObject.getString("name").equals("null") || !userObject.getString("name").equals("")){
                        if(!userInfo.getName().equals("null") && !userInfo.getName().equals("")){
                            theMainList.add(userInfo);
                        }


                        if(swap){

                            Collections.sort(theMainList, new Comparator<UserInfo>() {
                                @Override
                                public int compare(UserInfo o1, UserInfo o2) {
                                    if(Integer.parseInt(o1.getListId())  > Integer.parseInt(o2.getListId())){
                                        return -1;
                                    }
                                    else if (Integer.parseInt(o1.getListId())  == Integer.parseInt(o2.getListId())){
                                        return  0;
                                    }
                                    else {
                                        return 1;
                                    }
                                }
                            });
                        }
                        else {

                            Collections.sort(theMainList, new Comparator<UserInfo>() {
                                @Override
                                public int compare(UserInfo o1, UserInfo o2) {
                                    if(Integer.parseInt(o1.getListId())  < Integer.parseInt(o2.getListId())){
                                        return -1;
                                    }
                                    else if (Integer.parseInt(o1.getListId())  == Integer.parseInt(o2.getListId())){
                                        return  0;
                                    }
                                    else {
                                        return 1;
                                    }
                                }
                            });
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rv_data.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new MyAdapter(getApplicationContext(), theMainList);
                rv_data.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Tag","JSonNotWorking: " + error.getMessage());

            }
        });

        queue.add(jsonArrayRequest);
    }


    private void extractInfoById(boolean swap) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, websiteInfo, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject userObject = response.getJSONObject(i);

                        UserInfo userInfo = new UserInfo();
                        userInfo.setId(userObject.getString("id"));
                        userInfo.setListId(userObject.getString("listId"));
                        userInfo.setName(userObject.getString("name"));
                        //if(!userObject.getString("name").equals("null") || !userObject.getString("name").equals("")){
                        if(!userInfo.getName().equals("null") && !userInfo.getName().equals("")){
                            theMainList.add(userInfo);
                        }

                        if(swap){
                            Collections.sort(theMainList, new Comparator<UserInfo>() {
                                @Override
                                public int compare(UserInfo o1, UserInfo o2) {
                                    if(Integer.parseInt(o1.getId())  > Integer.parseInt(o2.getId())){
                                        return -1;
                                    }
                                    else if(Integer.parseInt(o1.getId())  == Integer.parseInt(o2.getId())){
                                        return  0;
                                    }
                                    else {
                                        return 1;
                                    }
                                }
                            });
                        }
                        else{
                            Collections.sort(theMainList, new Comparator<UserInfo>() {
                                @Override
                                public int compare(UserInfo o1, UserInfo o2) {
                                    if(Integer.parseInt(o1.getId())  < Integer.parseInt(o2.getId())){
                                        return -1;
                                    }
                                    else if(Integer.parseInt(o1.getId())  == Integer.parseInt(o2.getId())){
                                        return  0;
                                    }
                                    else {
                                        return 1;
                                    }
                                }
                            });
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rv_data.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new MyAdapter(getApplicationContext(), theMainList);
                rv_data.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Tag","JSonNotWorking: " + error.getMessage());

            }
        });

        queue.add(jsonArrayRequest);
    }

    private void extractInfoByName(boolean swap) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, websiteInfo, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject userObject = response.getJSONObject(i);

                        UserInfo userInfo = new UserInfo();
                        userInfo.setId(userObject.getString("id"));
                        userInfo.setListId(userObject.getString("listId"));
                        userInfo.setName(userObject.getString("name"));
                        //if(!userObject.getString("name").equals("null") || !userObject.getString("name").equals("")){
                        if(!userInfo.getName().equals("null") && !userInfo.getName().equals("")){
                            theMainList.add(userInfo);
                        }

                        if(swap){
                            Collections.sort(theMainList, new Comparator<UserInfo>() {
                                @Override
                                public int compare(UserInfo o1, UserInfo o2) {
                                    if(Integer.parseInt(o1.getName().substring(5))  > Integer.parseInt(o2.getName().substring(5))){
                                        return -1;
                                    }
                                    else if(Integer.parseInt(o1.getName().substring(5))  == Integer.parseInt(o2.getName().substring(5))){
                                        return  0;
                                    }
                                    else {
                                        return 1;
                                    }
                                }
                            });
                        }
                        else{
                            Collections.sort(theMainList, new Comparator<UserInfo>() {
                                @Override
                                public int compare(UserInfo o1, UserInfo o2) {
                                    if(Integer.parseInt(o1.getName().substring(5))  < Integer.parseInt(o2.getName().substring(5))){
                                        return -1;
                                    }
                                    else if(Integer.parseInt(o1.getName().substring(5))  == Integer.parseInt(o2.getName().substring(5))){
                                        return  0;
                                    }
                                    else {
                                        return 1;
                                    }
                                }
                            });
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rv_data.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new MyAdapter(getApplicationContext(), theMainList);
                rv_data.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Tag","JSonNotWorking: " + error.getMessage());

            }
        });

        queue.add(jsonArrayRequest);
    }
}