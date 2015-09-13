package com.iamtuhao.handout_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectActivity extends Activity {

    public static final String LAT = "latitude";
    public static final String LNG = "longitude";

    private double lat = 0;
    private double lng = 0;

    private ListView lv_presentations;
    private PresentationListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_select);

        Bundle data = getIntent().getExtras();

        lat = data.getDouble(LAT, 0);
        lng = data.getDouble(LNG, 0);

        setProgressBarVisibility(true);

        setViews();

        getPresentationListFromServer();
    }

    private void setViews() {
        lv_presentations = (ListView) findViewById(R.id.presentations_ListView);
    }

    private void initAdpater(String response) {
        Log.d("TAG", response);
        try {
            JSONArray ja = new JSONArray(response);

            ApiUtils.Presentation[] ps = new ApiUtils.Presentation[ja.length()];

            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                ps[i] = ApiUtils.converToPresentation(jo);
            }

            mAdapter = new PresentationListAdapter(this, ps);
            lv_presentations.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            Log.d("TAG", "setup adapter down");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("TAG", "json exception");
            Toast.makeText(this, "Server data error", Toast.LENGTH_SHORT).show();
        } finally {
            setProgressBarVisibility(false);
            Log.d("TAG", "init adpater complete");
        }
    }

    private void getPresentationListFromServer() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = ApiUtils.QUERY_URL + "?lat=" + lat + "&lng=" + lng;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                initAdpater(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SelectActivity.this, "Cannot connect to server", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}

