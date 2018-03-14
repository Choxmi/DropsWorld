package com.choxmi.dropsworld.dropsworld.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.choxmi.dropsworld.dropsworld.Activities.FullScreenImgActivity;
import com.choxmi.dropsworld.dropsworld.Activities.SinglePostActivity;
import com.choxmi.dropsworld.dropsworld.DropsWorldApplication;
import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.choxmi.dropsworld.dropsworld.R;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.facebook.imagepipeline.request.ImageRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Choxmi on 10/16/2017.
 */

public class PostItemAdapter extends RecyclerView.Adapter<PostItemAdapter.ViewHolder>{

    String feedList[] = {"img","vid", "game","quote","story","quote","story","img","vid","img","vid","quote","story","quote","story"};
    Context context;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    TextView userNameTxt,captionTxt,username;
    Typeface segoe;
    ImageButton likeBtn,commentBtn;
    private PopupWindow popWindow;
    Activity activity;
    SimpleDraweeView img_drawee_view,dp_post;
    ArrayList<Post> posts;
    private MediaController mMediaController;
    int vidPos = 1;

    public PostItemAdapter(Context context, Activity activity, ArrayList<Post> posts){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.activity = activity;
        this.posts = posts;
    }

    @Override
    public PostItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View model = layoutInflater.inflate(R.layout.model_post_item,parent,false);
        return new ViewHolder(model);
    }

    @Override
    public void onBindViewHolder(final PostItemAdapter.ViewHolder holder, int position) {
        frameLayout = (FrameLayout)holder.itemView.findViewById(R.id.post_container);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View appender = null;

        segoe = Typeface.createFromAsset(context.getAssets(), "fonts/segoeuil.ttf");

        captionTxt = (TextView) holder.itemView.findViewById(R.id.captionTxt);
        captionTxt.setTypeface(segoe);

        if(Post.POST_TYPE.PHOTO.toString().equals(posts.get(holder.getAdapterPosition()).getType())){
            appender = inflater.inflate(R.layout.container_photos,null);
            img_drawee_view = (SimpleDraweeView)appender.findViewById(R.id.img_container_photos);
            img_drawee_view.setImageURI(posts.get(holder.getAdapterPosition()).getUrl1());
            captionTxt.setText(posts.get(holder.getAdapterPosition()).getCaption());
        }if(Post.POST_TYPE.QUOTE.toString().equals(posts.get(holder.getAdapterPosition()).getType())){
            appender = inflater.inflate(R.layout.container_quote,null);
            ImageView quoteBackground = (ImageView)appender.findViewById(R.id.quote_screen_quote);
            TextView quoteTxt = (TextView)appender.findViewById(R.id.quote_txt);
            quoteTxt.setText(posts.get(holder.getAdapterPosition()).getCaption());
            quoteTxt.setTextColor(Integer.valueOf(posts.get(holder.getAdapterPosition()).getComponent1()));
            quoteBackground.setBackgroundColor(Integer.valueOf(posts.get(holder.getAdapterPosition()).getComponent3()));
            quoteBackground.setImageDrawable(context.getResources().getDrawable(Integer.valueOf(posts.get(holder.getAdapterPosition()).getComponent2())));
            captionTxt.setVisibility(View.GONE);
        }if(Post.POST_TYPE.DOODLE.toString().equals(posts.get(holder.getAdapterPosition()).getType())){
            appender = inflater.inflate(R.layout.container_photos,null);
            img_drawee_view = (SimpleDraweeView)appender.findViewById(R.id.img_container_photos);
            img_drawee_view.setImageURI(posts.get(holder.getAdapterPosition()).getUrl1());
            Log.e("DOODLEURI",posts.get(holder.getAdapterPosition()).getUrl1());
            captionTxt.setVisibility(View.GONE);
        }if(Post.POST_TYPE.ALBUM.toString().equals(posts.get(holder.getAdapterPosition()).getType())){
            appender = inflater.inflate(R.layout.container_photos,null);
            captionTxt.setText("ALBUM GOES HERE");
        }if(Post.POST_TYPE.VIDEO.toString().equals(posts.get(holder.getAdapterPosition()).getType())){
            mMediaController = new MediaController(context);
            appender = inflater.inflate(R.layout.container_videos,null);
            final VideoView vid = appender.findViewById(R.id.video_container);
            final ImageButton playBtn = (ImageButton) appender.findViewById(R.id.playBtn);

            vid.setVideoPath(posts.get(holder.getAdapterPosition()).getUrl1());

//            vid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    if(hasFocus){
//                        vid.seekTo(vidPos);
//                        vid.start();
//                    }else {
//                        vid.pause();
//                        vidPos = vid.getCurrentPosition();
//                    }
//                }
//            });
//            vid.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if(vid.isPlaying()){
//                        vid.seekTo(100);
//                        vid.pause();
//                        vidPos = vid.getCurrentPosition();
//                        mMediaController.hide();
//                        return false;
//                    }else {
//                        vid.seekTo(vidPos);
//                        mMediaController.show(0);
//                        vid.start();
//                        return false;
//                    }
//                }
//            });

            playBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(vid.isPlaying()){
                        vid.pause();
                        vidPos = vid.getCurrentPosition();
                        mMediaController.show(0);
                        playBtn.setVisibility(View.INVISIBLE);
                    }else {
                        vid.seekTo(vidPos);
                        vid.start();
                        mMediaController.hide();
                        playBtn.setVisibility(View.INVISIBLE);
                    }
                }
            });

            vid.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer)
                {
                    vid.seekTo(1);
                    vidPos = 1;
                    playBtn.setVisibility(View.VISIBLE);
                }
            });

            vid.seekTo(50);
        }

//        if("img".equals(feedList[holder.getAdapterPosition()])){
//            appender = inflater.inflate(R.layout.container_photos,null);
//            img_drawee_view = (SimpleDraweeView)appender.findViewById(R.id.img_container_photos);
//            Uri imageUri = Uri.parse("https://choxcreations.000webhostapp.com/Dropsworld/img/1510492952440.jpg");
//            img_drawee_view.setImageURI(imageUri);
//
//            TextView txt = (TextView)appender.findViewById(R.id.img_container_txt);
//            txt.setText("Blaaah");
//        }if("vid".equals(feedList[holder.getAdapterPosition()])){
//            appender = inflater.inflate(R.layout.container_videos,null);
//        }if("quote".equals(feedList[holder.getAdapterPosition()])){
//            appender = inflater.inflate(R.layout.container_quote,null);
//        }if("story".equals(feedList[holder.getAdapterPosition()])){
//            appender = inflater.inflate(R.layout.container_storyboard,null);
//        }if("game".equals(feedList[holder.getAdapterPosition()])){
//            appender = inflater.inflate(R.layout.container_games,null);
//        }else {
//
//        }

        frameLayout.addView(appender);

        userNameTxt = (TextView)holder.itemView.findViewById(R.id.user_post_txt);
        userNameTxt.setTypeface(segoe);
        userNameTxt.setText(posts.get(holder.getAdapterPosition()).getUserName());

        dp_post = (SimpleDraweeView) holder.itemView.findViewById(R.id.dp_post);
        dp_post.setImageURI(posts.get(holder.getAdapterPosition()).getUserUrl());

        likeBtn = (ImageButton)holder.itemView.findViewById(R.id.like_btn);
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton imb = (ImageButton)v;
                if(imb.getDrawable()==context.getDrawable(R.drawable.ic_favorite_full)){
                    imb.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_emp));
                }else {
                    imb.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_full));
                }
            }
        });

        commentBtn = (ImageButton)holder.itemView.findViewById(R.id.comment_btn);
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onShowPopup(holder.itemView);
            }
        });

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fullPost = new Intent(context,FullScreenImgActivity.class);
                fullPost.putExtra("URI",(posts.get(holder.getAdapterPosition()).getUrl1()));
                context.startActivity(fullPost);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
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
