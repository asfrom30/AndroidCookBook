package com.doyoon.android.cookbook.mvp.comport.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doyoon.android.cookbook.R;
import com.doyoon.android.cookbook.mvp.comport.ComportMvpActivityDetail;
import com.doyoon.android.cookbook.mvp.comport.domain.Memo;

import java.util.ArrayList;

/**
 * Created by DOYOON on 6/4/2017.
 */

/*  In this case, View is implemented as inner Class
    It has advantage, View class can access easily to Presenter Class because of Closure(No need to parameter)
    Note : If you want to see separted view and presenter, refer to STRICT package.
 */
public class ComportMvpListRvAdapter extends RecyclerView.Adapter<ComportMvpListRvAdapter.ViewHolder> {


    private  ArrayList<Memo> memoList;

    public ComportMvpListRvAdapter(ArrayList<Memo> memoList) {
        this.memoList = memoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comport_mvp_list_rv, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Memo memo = memoList.get(position);
        holder.setId(memo.getId());
        holder.setName(memo.getId());
        holder.setTitle(memo.getTitle());
        holder.setDate(memo.getDate());
    }

    @Override
    public int getItemCount() {
        return this.memoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        String id;
        TextView textViewTitle, textViewName, textViewDate;


        public ViewHolder(View itemView) {
            super(itemView);

            /* Dependency Injection */
            this.textViewTitle = (TextView) itemView.findViewById(R.id.comportMvpList_tv_title);
            this.textViewName = (TextView) itemView.findViewById(R.id.comportMvpList_tv_name);
            this.textViewDate = (TextView) itemView.findViewById(R.id.comportMvpList_tv_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* You can access to Presenter Class Method, because of closure */
                    goDetail(id, v.getContext());
                }
            });
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.textViewTitle.setText(title);
        }

        public void setName(String name) {
            this.textViewName.setText(name);
        }

        public void setDate(String date) {
            this.textViewDate.setText(date);
        }
    }

    private void goDetail(String id, Context context) {
        Intent intent = new Intent(context, ComportMvpActivityDetail.class);
        intent.putExtra("ID_KEY", id);
        context.startActivity(intent);
    }
}
