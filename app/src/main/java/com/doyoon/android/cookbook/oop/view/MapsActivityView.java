package com.doyoon.android.cookbook.oop.view;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doyoon.android.cookbook.R;
import com.doyoon.android.cookbook.oop.presenter.MapsActivityPresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by DOYOON on 6/14/2017.
 */

public class MapsActivityView implements OnMapReadyCallback{

    private TextView titleTextView;
    private CustomAdapter adapter;
    // private ArrayAdapter<String> adapter; // change ListView to Recylcer View
    private List<String> toiletList;



    private MapsActivityPresenter presenter;
    private AppCompatActivity activity;
    private RecyclerView recyclerView;
    private SupportMapFragment mapFragment;
    private GoogleMap map;

    public MapsActivityView(AppCompatActivity activity, MapsActivityPresenter presenter) {

        this.presenter = presenter;
        this.activity = activity;

        /* layout */
        this.activity.setContentView(R.layout.activity_toilet_map_main);

        /* dependecy injection */
        this.recyclerView = (RecyclerView) this.activity.findViewById(R.id.rv_toilet_list);
        this.titleTextView = (TextView) this.activity.findViewById(R.id.tv_toeilt_number);

        /* Map Async */
        FragmentManager manager = this.activity.getSupportFragmentManager();
        ((SupportMapFragment) manager.findFragmentById(R.id.fragment_toeilt_map)).getMapAsync(this);

        /* Data List Store Initialize */
        toiletList = new ArrayList<>();

        /* Adapter */
        // adapter = new ArrayAdapter<String>(this.activity, android.R.layout.simple_list_item_1, toiletList); // change ListView to Recylcer View
        this.adapter = new CustomAdapter();
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.activity));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;
        this.presenter.notifyMapOnReady();
    }


    public void addToiletList(String toiletName) {
        this.toiletList.add(toiletName);
    }

    public void addMarker(MarkerOptions marker) {
        this.map.addMarker(marker);
    }

    /* Update View Set */
    public void updateAllView(){
        this.updateListView();
    }

    public void updateListView() {
        this.adapter.notifyDataSetChanged();
        Log.e("Main", "화장실개수는...." + this.toiletList.size());
    }

    public void updateTitleTextView(int totalToiletNumber){
        this.titleTextView.setText(totalToiletNumber + "");
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        /* 여기에 List를 선언하면 View가 모델을 가지게 된다. 그러면 MVC가 아니라 MVP가 된다. */

        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_toilet_layout, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
            String titleName = toiletList.get(position);
            holder.setTitle(titleName);
            Log.e("Main", "이름세팅." + titleName);
        }

        @Override
        public int getItemCount() {
            return toiletList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView titleTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                this.titleTextView = (TextView) itemView.findViewById(R.id.tv_toilet_name);
            }

            public void setTitle(String title){
                this.titleTextView.setText(title);
            }
        }
    }
}
