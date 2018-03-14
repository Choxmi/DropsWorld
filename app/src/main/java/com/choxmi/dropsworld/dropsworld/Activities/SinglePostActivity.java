package com.choxmi.dropsworld.dropsworld.Activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.choxmi.dropsworld.dropsworld.Adapters.CommentAdapter;
import com.choxmi.dropsworld.dropsworld.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Choxmi on 11/5/2017.
 */

public class SinglePostActivity extends AppCompatActivity {

    SimpleDraweeView contentImg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RecyclerView commentList = (RecyclerView)findViewById(R.id.comment_list_single_post);
        commentList.setLayoutManager(layoutManager);
        commentList.setAdapter(new CommentAdapter(this));

        contentImg = (SimpleDraweeView)findViewById(R.id.single_post_img);

        //Uri imgUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+getResources().getResourcePackageName(R.drawable.sample_gif)+'/'+getResources().getResourceTypeName(R.drawable.sample_gif)+'/'+getResources().getResourceEntryName(R.drawable.sample_gif));

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .build();
        contentImg.setController(controller);
        contentImg.setImageResource(R.drawable.sample_gif);

        contentImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fullImage = new Intent(SinglePostActivity.this,FullScreenImgActivity.class);
                startActivity(fullImage);
            }
        });
    }
}
