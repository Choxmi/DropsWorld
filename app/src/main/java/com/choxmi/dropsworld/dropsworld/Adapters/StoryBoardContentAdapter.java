package com.choxmi.dropsworld.dropsworld.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 10/27/2017.
 */

public class StoryBoardContentAdapter extends RecyclerView.Adapter<StoryBoardContentAdapter.ViewHolder>{

    //private List<PostModel> modelList;
    Context context;
    LayoutInflater layoutInflater;

    public StoryBoardContentAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public StoryBoardContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View model = layoutInflater.inflate(R.layout.model_storyboard_content,parent,false);
        return new ViewHolder(model);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

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
