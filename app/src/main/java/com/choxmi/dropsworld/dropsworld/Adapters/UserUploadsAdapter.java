package com.choxmi.dropsworld.dropsworld.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 11/1/2017.
 */

public class UserUploadsAdapter extends RecyclerView.Adapter<UserUploadsAdapter.ViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    String categories[] = {"Photos","Videos","HeadWord","Games","Quotes"};

    public UserUploadsAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public boolean onFailedToRecycleView(ViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View model = layoutInflater.inflate(R.layout.model_profile_set,parent,false);
        return new UserUploadsAdapter.ViewHolder(model);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView child = (RecyclerView)holder.itemView.findViewById(R.id.upload_items);
        child.setLayoutManager(layoutManager);
        child.setAdapter(new UploadSingleItemAdapter(context));

        TextView category = (TextView)holder.itemView.findViewById(R.id.upload_category_txt);
        category.setText(categories[holder.getAdapterPosition()]);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
