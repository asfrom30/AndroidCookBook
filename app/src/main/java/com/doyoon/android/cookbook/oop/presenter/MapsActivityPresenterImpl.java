package com.doyoon.android.cookbook.oop.presenter;

import com.doyoon.android.cookbook.oop.domain.RemoteToiletDAO;
import com.doyoon.android.cookbook.oop.domain.asynctask.AsyncJson;
import com.doyoon.android.cookbook.oop.domain.asynctask.AsyncJsonImpl;
import com.doyoon.android.cookbook.oop.domain.toilet.GeoInfoPublicToiletWGS;
import com.doyoon.android.cookbook.oop.domain.toilet.Toilet;
import com.doyoon.android.cookbook.oop.view.MapsActivityView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DOYOON on 6/14/2017.
 */

public class MapsActivityPresenterImpl implements MapsActivityPresenter, AsyncJson.Callback {

    // http://openapi.seoul.go.kr:8088/6a734e76676d697235397548734e42/json/GeoInfoPublicToiletWGS/1/10
    static final String URL_PREFIX = "http://openapi.seoul.go.kr:8088/";
    static final String URL_CERT = "6a734e76676d697235397548734e42/";
    static final String URL_MID = "json/GeoInfoPublicToiletWGS";
    private static final int PAGE_OFFSET = 10;

    private RemoteToiletDAO remoteToiletDAO;
    private int page = 1;
    private GoogleMap map;
    private MapsActivityView view;


    @Override
    public void setToiletDAO(RemoteToiletDAO remoteToiletDAO) {
        this.remoteToiletDAO = remoteToiletDAO;
    }

    @Override
    public void setView(MapsActivityView mapsActivityView) {
        this.view = mapsActivityView;
    }

    @Override
    public void notifyMapOnReady() {
        /* 이 함수를 쪼갤 수 없는 이유가...
        * 응답을 기다렸다가 나중에 UI를 갱신하는 방식이기 때문이다...
        * 여기에서 함수를 쪼개버리면. 밑에 함수를 실행하고 바로 넘어가 버린다.
        * 즉 응답을 요청하고, 받아오고 받아온뒤에 실행하는 함수들은 아래에 넣어주어야한다.
        * 그에 맞춰서 Naming도 변경해야 한다. */
        // this.fetchJsonData(urlString);

        String urlString = buildStringUrl();
        this.fetchDataOnAsync(urlString);
    }

    private String buildStringUrl() {
        int end = this.page * PAGE_OFFSET;
        int start = end  - PAGE_OFFSET + 1;
        StringBuilder builder = new StringBuilder();
        builder.append(URL_PREFIX);
        builder.append(URL_CERT);
        builder.append(URL_MID);
        builder.append("/" + start + "/" + end);
        return builder.toString();
    }

    private void fetchDataOnAsync(String stringUrl) {
        //TODO static으로 callback 또는 Interface를 구현할 수는 없는 걸까?
        // AsyncJson asyncJson = new AsyncJsonImpl();
        // String ressult = asyncJson.execute(this);

        AsyncJsonImpl.getInstance().execute(this, stringUrl);

    }

    /* Callback은 Presenter에서 구현한다. 왜냐하면 View객체를 가지고 있고 Business 로직을 여기에 짜면 관심사를 분리할 수 있다.
    ...... */
    @Override
    public void preExecute() {

    }

    @Override
    public String doInBackground(String stringUrl) {
        String result = "";

        try {
            /* Request */
            URL url = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            /* Response */
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    result += temp;
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void postExecute(String result) {
        // Json을 Gson으로 변환한다.
        Gson gson = new Gson();
        Toilet toiletStructure = gson.fromJson(result, Toilet.class);

        int totalToiletNumber = Integer.parseInt(toiletStructure.getGeoInfoPublicToiletWGS().getList_total_count());

        // 어댑터에 설정한다
        GeoInfoPublicToiletWGS.Row toilets[] = toiletStructure.getGeoInfoPublicToiletWGS().getRow();
        for (GeoInfoPublicToiletWGS.Row toilet : toilets) {
            /* prepare data */
            String toiletName = toilet.getGU_NM() + " " + toilet.getHNR_NAM();
            Double lat = Double.parseDouble(toilet.getLAT());
            Double lng = Double.parseDouble(toilet.getLNG());
            LatLng latLng= new LatLng(lat, lng);

            /* List View */
            // view.clearAllList();
            view.addToiletList(toiletName);

            /* Add and Create Map marker for View */
            MarkerOptions marker = new MarkerOptions();
            marker.position(latLng);
            marker.title(toiletName);

            view.addMarker(marker);
        }

        view.updateTitleTextView(totalToiletNumber);
        view.updateListView();
    }
}
