package com.choxmi.dropsworld.dropsworld.Publisher;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.choxmi.dropsworld.dropsworld.Config;
import com.choxmi.dropsworld.dropsworld.Models.Post;
import com.choxmi.dropsworld.dropsworld.R;
import com.choxmi.dropsworld.dropsworld.Transaction.AssetUploader;
import com.choxmi.dropsworld.dropsworld.Transaction.ProgressNotification;
import com.facebook.drawee.view.SimpleDraweeView;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by Choxmi on 10/27/2017.
 */

public class QuotePublisher extends AppCompatActivity {
    int screens[] = {R.drawable.quoteframe_1r,R.drawable.quoteframe_2r,R.drawable.quoteframe_3r,R.drawable.quoteframe_4r,R.drawable.quoteframe_5r,R.drawable.quoteframe_6r,
            R.drawable.quoteframe_7r,R.drawable.quoteframe_8r,R.drawable.quoteframe_9r,R.drawable.quoteframe_10r,R.drawable.quoteframe_11r,R.drawable.quoteframe_12r,R.drawable.quoteframe_13r};

    int backgrounds[] = new int[49];
    ViewPager pager,backgroundPager;
    ImageButton redBtn,greenBtn,whiteBtn,blackBtn,purpleBtn,yellowBtn,backgroundBtn,fontBtn,fontColorBtn;
    EditText quote;
    ImageView quoteScreen;
    int selectedTxtColor,selectedBackColor;
    Button quotePubBtn;
    RelativeLayout quoteBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publisher_quote);
        pager = (ViewPager)findViewById(R.id.quote_pager);
        pager.setAdapter(new ScreenAdapter(this));
        backgroundPager = (ViewPager)findViewById(R.id.quote_background_pager);
        backgroundPager.setAdapter(new BackgroundAdapter(this));

        quote = (EditText)findViewById(R.id.quote_txt);
        quote.setTextColor(Color.BLACK);

        for(int i=0;i<49;i++){
            backgrounds[i] = getResources().getIdentifier("quoteBackground"+(i+1),"color",getPackageName());
        }

        fontColorBtn = (ImageButton)findViewById(R.id.btnFontColor);
        fontColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(QuotePublisher.this)
                        .setTitle("Choose Brush Color")
                        .initialColor(Color.WHITE)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                Log.e("Color", ""+selectedColor);
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int col, Integer[] allColors) {
                                quote.setTextColor(col);
                                selectedTxtColor = col;
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });

        quoteBack = (RelativeLayout)findViewById(R.id.quoteBack);
        selectedBackColor = Color.WHITE;

        backgroundBtn = (ImageButton)findViewById(R.id.btnBackgroundColor);
        backgroundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(QuotePublisher.this)
                        .setTitle("Choose Brush Color")
                        .initialColor(Color.WHITE)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                Log.e("Color", ""+selectedColor);
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int col, Integer[] allColors) {
                                quoteBack.setBackgroundColor(col);
                                selectedBackColor = col;
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });

//
//        redBtn = (ImageButton)findViewById(R.id.color_red);
//        redBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                quote.setTextColor(((ColorDrawable)redBtn.getBackground()).getColor());
//                selectedTxtColor = ((ColorDrawable)redBtn.getBackground()).getColor();
//            }
//        });
//
//        greenBtn = (ImageButton)findViewById(R.id.color_green);
//        greenBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                quote.setTextColor(((ColorDrawable)greenBtn.getBackground()).getColor());
//                selectedTxtColor = ((ColorDrawable)greenBtn.getBackground()).getColor();
//            }
//        });
//
//        whiteBtn = (ImageButton)findViewById(R.id.color_white);
//        whiteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                quote.setTextColor(((ColorDrawable)whiteBtn.getBackground()).getColor());
//                selectedTxtColor = ((ColorDrawable)whiteBtn.getBackground()).getColor();
//            }
//        });
//
//        blackBtn = (ImageButton)findViewById(R.id.color_black);
//        blackBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                quote.setTextColor(((ColorDrawable)blackBtn.getBackground()).getColor());
//                selectedTxtColor = ((ColorDrawable)blackBtn.getBackground()).getColor();
//            }
//        });
//
//        purpleBtn = (ImageButton)findViewById(R.id.color_purple);
//        purpleBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                quote.setTextColor(((ColorDrawable)purpleBtn.getBackground()).getColor());
//                selectedTxtColor = ((ColorDrawable)purpleBtn.getBackground()).getColor();
//            }
//        });
//
//        yellowBtn = (ImageButton)findViewById(R.id.color_yellow);
//        yellowBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                quote.setTextColor(((ColorDrawable)yellowBtn.getBackground()).getColor());
//                selectedTxtColor = ((ColorDrawable)yellowBtn.getBackground()).getColor();
//            }
//        });

        quotePubBtn = (Button)findViewById(R.id.quotePubBtn);
        quotePubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressNotification.startLoading(QuotePublisher.this,"Posting...");
                    QuotePub publisher = new QuotePub();
                    Thread t1 = new Thread(publisher);
                    t1.start();
            }
        });
    }

    private class ScreenAdapter extends PagerAdapter{
        private Activity _activity;
        private LayoutInflater inflater;

        public ScreenAdapter(Activity activity){
            this._activity = activity;
        }

        @Override
        public int getCount() {
            return screens.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View viewLayout = inflater.inflate(R.layout.quote_model, container,false);
            quoteScreen = (ImageView)viewLayout.findViewById(R.id.quote_background);
            quoteScreen.setImageDrawable(getResources().getDrawable(screens[position]));
            ((ViewPager) container).addView(viewLayout);
            return viewLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);
        }
    }

    private class BackgroundAdapter extends PagerAdapter{
        private Activity _activity;
        private LayoutInflater inflater;

        public BackgroundAdapter(Activity activity){
            this._activity = activity;
        }

        @Override
        public int getCount() {
            return 49;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            SimpleDraweeView quoteBackground;
            inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View viewLayout = inflater.inflate(R.layout.quote_background_model, container,false);
            quoteBackground = (SimpleDraweeView)viewLayout.findViewById(R.id.quote_background_color);
            quoteBackground.setImageResource(backgrounds[position]);
            if(position!=0) {
                pager.setBackground(getDrawable(backgrounds[position-1]));
            }
            ((ViewPager) container).addView(viewLayout);
            return viewLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);
        }
    }

    class QuotePub implements Runnable{

        public QuotePub(){

        }

        @Override
        public void run() {
            ProgressNotification pn = new ProgressNotification();
            pn.startProgress(QuotePublisher.this);

            LinkedHashMap parameters = new LinkedHashMap();
            parameters.put("type","QUOTE");
            parameters.put("txt",quote.getText().toString());
            parameters.put("txtColor",selectedTxtColor);
            parameters.put("background",screens[pager.getCurrentItem()]);
            parameters.put("backColor",selectedBackColor);
            SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String userID = saved_values.getString("owner_id","");
            parameters.put("user",userID);

            try {
                String response = new AssetUploader.DBOps(Config.DB_UPDATE_URL, parameters).execute().get();
                Log.e("QUOTE Res",response);
                MDToast.makeText(QuotePublisher.this,"Upload Complete", Toast.LENGTH_LONG,MDToast.TYPE_SUCCESS).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pn.stopProgress();
            ProgressNotification.stopLoading();
        }
    }
}
