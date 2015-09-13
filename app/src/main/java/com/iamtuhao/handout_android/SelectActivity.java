package com.iamtuhao.handout_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class SelectActivity extends Activity {

    public static final String LAT = "latitude";
    public static final String LNG = "longitude";

    private double lat = 0;
    private double lng = 0;

    private ListView lv_presentations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Bundle data = getIntent().getExtras();

        lat = data.getDouble(LAT, 0);
        lng = data.getDouble(LNG, 0);

        Toast.makeText(this, lat + "," + lng, Toast.LENGTH_SHORT).show();

        // TODO async request.

        setViews();
    }

    private void setViews() {
        lv_presentations = (ListView) findViewById(R.id.presentations_ListView);
    }
}
