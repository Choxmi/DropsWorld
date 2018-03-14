package com.choxmi.dropsworld.dropsworld.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.choxmi.dropsworld.dropsworld.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Choxmi on 11/5/2017.
 */

public class FullScreenImgActivity extends AppCompatActivity {

    ImageButton comment_btn,back_btn;
    FrameLayout comment_layout;
    SimpleDraweeView content;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        content = (SimpleDraweeView)findViewById(R.id.fullImgView);
        String uri = getIntent().getStringExtra("URI");
        if(uri.equals("")){
            content.setVisibility(View.GONE);
        }
        content.setImageURI(uri);

        comment_layout = (FrameLayout)findViewById(R.id.comment_frame);

        comment_btn = (ImageButton)findViewById(R.id.comment_btn);
        comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment_layout.setVisibility(View.VISIBLE);
            }
        });

        back_btn = (ImageButton)findViewById(R.id.cmt_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comment_layout.getVisibility()==View.VISIBLE){
                    comment_layout.setVisibility(View.GONE);
                }else{
                    onBackPressed();
                }
            }
        });
    }
}
