package com.choxmi.dropsworld.dropsworld.Publisher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 10/27/2017.
 */

public class QuotePublisher extends AppCompatActivity {
    int screens[] = {R.drawable.xbox,R.drawable.android};
    ViewPager pager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publisher_quote);
        pager = (ViewPager)findViewById(R.id.quote_pager);
        pager.setAdapter(new ScreenAdapter(this));
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
            ImageView quoteScreen;

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
}
