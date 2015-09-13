package com.iamtuhao.handout_android;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiUtils {
    public static final String SERVER_IP = "52.21.48.183";
    public static final String HTTP_PREFIX = "http://" + SERVER_IP;

    public static final String QUERY_URL = HTTP_PREFIX + "/json/present/";

    private static final String TITLE = "title";
    private static final String OWNER = "owner";
    private static final String DOWNLOAD_URL = "file_url";
    private static final String LAT = "lat";
    private static final String LNG = "lng";
    private static final String PREVIEW_URL = "img_url";
    private static final String DISTANCE = "distance";
    private static final String CREATED = "created";
    private static final String UPDATED = "updated";

    public static class Presentation {
        public String title;
        public String owner;
        public String download_url;
        public String lat;
        public String lng;
        public String preview_url;
        public String distance;
        public String create_time;
        public String update_time;
    }

    public static Presentation converToPresentation(JSONObject jsonObject) {
        Presentation p = new Presentation();
        try {
            p.title = jsonObject.getString(TITLE);
            p.owner = jsonObject.getString(OWNER);
            p.download_url = HTTP_PREFIX  + jsonObject.getString(DOWNLOAD_URL);
            p.lat = jsonObject.getString(LAT);
            p.lng = jsonObject.getString(LNG);
            p.preview_url = HTTP_PREFIX + jsonObject.getString(PREVIEW_URL);
            p.distance = jsonObject.getString(DISTANCE);
            p.create_time = jsonObject.getString(CREATED);
            p.update_time = jsonObject.getString(UPDATED);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return p;
    }
}
