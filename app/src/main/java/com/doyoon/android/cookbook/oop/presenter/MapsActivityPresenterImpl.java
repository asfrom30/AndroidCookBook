package com.doyoon.android.cookbook.oop.presenter;

import android.os.AsyncTask;

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
import java.util.HashSet;

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
        String urlString = buildStringUrl();
        this.fetchJsonData(urlString);
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

    private void fetchJsonData(String stringUrl) {
        // Json으로 가져온다.

        // UI를 업데이트 한다...

        //TODO static으로 callback 또는 Interface를 구현할 수는 없는 걸까?
        AsyncJson asyncJson = new AsyncJsonImpl();
        String ressult = asyncJson.execute(this);

        AsyncJsonImpl.getInstance().execute(this);

        new AsyncTask<String, Void, String>() {
            /* Main Thread */
            @Override
            protected void onPreExecute() {

            }

            /* Sub Thread */
            @Override
            protected String doInBackground(String... params) {
                String result = "";

                try {
                    /* Request */
                    String stringUrl = params[0];
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

            /* Main Thread */
            @Override
            protected void onPostExecute(String result) {
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

            @Override
            protected void onProgressUpdate(Void... values) {

            }


        }.execute(stringUrl);
    }

    /* Callback은 Presenter에서 구현한다. 왜냐하면 View객체를 가지고 있고 Business 로직을 여기에 짜면 관심사를 분리할 수 있다.
    ...... */
    @Override
    public void preExecute() {

    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void postExecute() {

    }
}
