package com.example.vsvll.volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;



public class NetworkCalling {

    private Context context;
    private ProgressDialog pDialog;
    private  boolean isProgressDialoges;
    private OnServicesCallCompleteListener listener;

    public void setOnServicesCallCompleteListener(OnServicesCallCompleteListener listeners){
        this.listener = listeners;
    }

    public NetworkCalling(Context context, boolean isProgressDialoges) {
        this.context = context;
        this.isProgressDialoges = isProgressDialoges;
    }


    public void  makeJSONArraGetResponse(String url, final com.android.volley.Request.Priority priority) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (isProgressDialoges) {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            pDialog.setIndeterminate(true);
            pDialog.setCanceledOnTouchOutside(false);
            if (isProgressDialoges) {
                pDialog.show();
            }
        }
        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try{
                            if (isProgressDialoges) {
                                pDialog.dismiss();
                            }
                            listener.onJSONArrayResponse(response);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        try{
                            if (isProgressDialoges) {
                                pDialog.dismiss();
                            }
                            listener.onErrorResponse(error);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }
}
