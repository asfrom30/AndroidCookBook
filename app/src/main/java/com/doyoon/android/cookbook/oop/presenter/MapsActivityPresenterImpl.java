package com.doyoon.android.cookbook.oop.presenter;

import com.doyoon.android.cookbook.oop.domain.RemoteToiletDAO;
import com.doyoon.android.cookbook.oop.domain.RemoteToiletDAOImpl;
import com.doyoon.android.cookbook.oop.domain.asynctask.CustomAsyncTask;
import com.doyoon.android.cookbook.oop.domain.asynctask.CustomAsyncTaskImpl;
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

public class MapsActivityPresenterImpl implements MapsActivityPresenter, CustomAsyncTask.Callback {

    // http://openapi.seoul.go.kr:8088/6a734e76676d697235397548734e42/json/GeoInfoPublicToiletWGS/1/10
    static final String URL_PREFIX = "http://openapi.seoul.go.kr:8088/";
    static final String URL_CERT = "6a734e76676d697235397548734e42/";
    static final String URL_MID = "json/GeoInfoPublicToiletWGS";
    private static final int PAGE_OFFSET = 10;

    private RemoteToiletDAO remoteToiletDAO;
    private int page = 0;
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
        this.getNextData();
    }

    private void getNextData(){
        this.nextPage();
        this.fetchDataOnAsync(buildStringUrl());
    }

    private void nextPage(){
        this.page++;
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
        CustomAsyncTaskImpl.getInstance().execute(this, stringUrl);

        /* Domain 생성 및 상호참조 */
//      RemoteToiletDAO dao = DummyToiletDAOImp.getInstance();// 더미테스트를 할 수 있다.
        RemoteToiletDAO dao = RemoteToiletDAOImpl.getInstance();
        // presenter.setToiletDAO(dao);
    }

    @Override
    public void preExecute() {

    }

    @Override
    public String doInBackground(String... params) {

        String result = "";
        String stringUrl = params[0];

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
