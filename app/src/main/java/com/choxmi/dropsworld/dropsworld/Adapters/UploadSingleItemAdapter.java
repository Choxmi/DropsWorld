package com.choxmi.dropsworld.dropsworld.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choxmi.dropsworld.dropsworld.Activities.FullScreenImgActivity;
import com.choxmi.dropsworld.dropsworld.Activities.SinglePostActivity;
import com.choxmi.dropsworld.dropsworld.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Choxmi on 11/1/2017.
 */

public class UploadSingleItemAdapter extends RecyclerView.Adapter<UploadSingleItemAdapter.ViewHolder>{

    Context context;
    LayoutInflater layoutInflater;

    public UploadSingleItemAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View model = layoutInflater.inflate(R.layout.child_my_upload,parent,false);
        return new ViewHolder(model);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SimpleDraweeView myUpload = (SimpleDraweeView)holder.itemView.findViewById(R.id.uploaded_img);
        myUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent singlePost = new Intent(context,SinglePostActivity.class);
                context.startActivity(singlePost);
            }
        });
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
