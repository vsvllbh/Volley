package com.example.vsvll.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   private  static  String URL = "http://www.pizzaboy.de/app/pizzaboy.json";

   private ArrayList<pojoClass> pojoClassArrayList;
   private RecyclerView recyclerView;
    MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pojoClassArrayList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
         GetViollyresponse();
    }

    private void GetViollyresponse() {
        try {
        NetworkCalling calling = new NetworkCalling(MainActivity.this,true);
        calling.setOnServicesCallCompleteListener(new ServicesCallCompleteListener());
        calling.makeJSONArraGetResponse(URL,Request.Priority.IMMEDIATE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class ServicesCallCompleteListener implements OnServicesCallCompleteListener {
        @Override
        public void onJSONObjectResponse(JSONObject jsonObject) {

        }

        @Override
        public void onJSONArrayResponse(JSONArray jsonArray) {
          GetJsonArrResponse(jsonArray);
        }

        @Override
        public void onErrorResponse(VolleyError jsonObject) {

        }
    }

    private void GetJsonArrResponse(JSONArray jsonArray) {
        try {

            for (int i=0;i<jsonArray.length();i++) {
              JSONObject object = jsonArray.getJSONObject(i);
                pojoClass aClass = new pojoClass();
                aClass.setName(object.getString("Name"));
                aClass.setAddress1(object.getString("Address1"));
                aClass.setZip(object.getString("Zip"));
                aClass.setCity(object.getString("City"));
                aClass.setPhone(object.getString("Phone"));
                aClass.setLat(object.getString("Lat"));
                aClass.setLon(object.getString("Lon"));
                aClass.setLink(object.getString("Link"));
                pojoClassArrayList.add(aClass);
             }

        }catch (Exception e){
            e.printStackTrace();
        }
        //Callind RecyclearView Adapter
        mAdapter = new MoviesAdapter(MainActivity.this,pojoClassArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        }
    }

