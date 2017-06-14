package com.doyoon.android.cookbook.oop.interfaces;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.doyoon.android.cookbook.R;
import com.doyoon.android.cookbook.oop.interfaces.util.Remote;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsActivityMain extends AppCompatActivity implements Remote.Callback {

    ProgressDialog progressDialog;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps_main);
        /* Initialize */
        String urlString = "http://openapi.seoul.go.kr:8088/6a734e76676d697235397548734e42/json/GeoInfoPublicToiletWGS/1/10";
        this.setUrlString(urlString);

        progressDialog = new ProgressDialog(this);

        Remote remote = new Remote();
        remote.getData(this);
    }

    private void setUrlString(String urlString){
        this.url = urlString;
    }
    @Override
    public String getUrlString(){
        return url;
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }
    @Override
    public ProgressDialog getProgress(){
        return this.progressDialog;
    }
    @Override
    public void postExecute(String jsonString){
        try {
            // 1. Json String 전체를 JSON Object로 변환
            JSONObject urlObject = new JSONObject(jsonString);
            // 2. JsonObject에서 최상위의 object를 꺼낸다.
            JSONObject rootObject = urlObject.getJSONObject("GeoInfoPublicToiletWGS");
            // 3. 사용하려는 주차장 정보 (복수개)들을 JsonArray로 꺼낸다.
            // 이 json에서는 rootObject 바로 아래에 실제 정보가 있찌만 계층 구조상 더 아래에 존재할 수도 있다. rows

            JSONArray rows = rootObject.getJSONArray("row");
            int arrayLength = rows.length();

            List<String> toiletCodeList = new ArrayList<>();
            for (int i = 0; i < arrayLength; i++) {
                JSONObject toilet = rows.getJSONObject(i);
                String code = toilet.getString("OBJECTID");

                /* 화장실 코드 중복 검사. */
                if (toiletCodeList.contains(code)) {
                    continue;   // 중복된 값이 있으면 로직을 실행하지 않고 for문 상단으로 이동
                }

                toiletCodeList.add(code);

//                double lat = getDouble(park,"LAT");
//                double lng = getDouble(park,"LNG");
//                LatLng parking = new LatLng(lat, lng);
//
//                int capacity = getInt(park, "CAPACITY");
//                int current = getInt(park, "CUR_PARKING");
//                int space = capacity - current;
//
//                mMap.addMarker(new MarkerOptions().position(parking).title(space + "/" + capacity));

            }
            Log.e("HTTPConnection", "total code is : " + toiletCodeList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        progressDialog.dismiss();

    }



}
