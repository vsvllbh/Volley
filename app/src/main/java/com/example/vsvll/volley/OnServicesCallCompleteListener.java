package com.example.vsvll.volley;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public interface OnServicesCallCompleteListener {

    void  onJSONObjectResponse(JSONObject jsonObject);
    void  onJSONArrayResponse(JSONArray jsonArray);
    void  onErrorResponse(VolleyError jsonObject);

}
