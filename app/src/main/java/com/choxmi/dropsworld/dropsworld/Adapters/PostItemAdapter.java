package com.choxmi.dropsworld.dropsworld.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 10/16/2017.
 */

public class PostItemAdapter extends RecyclerView.Adapter<PostItemAdapter.ViewHolder>{

    //private List<PostModel> modelList;
    Context context;
    LayoutInflater layoutInflater;

    public PostItemAdapter(Context context){
        Log.e("PostAdap","Inside");
        //this.modelList = list;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public PostItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View model = layoutInflater.inflate(R.layout.model_post_item,parent,false);
        return new ViewHolder(model);
    }

    @Override
    public void onBindViewHolder(final PostItemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
