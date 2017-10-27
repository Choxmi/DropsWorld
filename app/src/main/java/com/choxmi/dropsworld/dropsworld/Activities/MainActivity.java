package com.choxmi.dropsworld.dropsworld.Activities;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.choxmi.dropsworld.dropsworld.Fragments.ChatFragment;
import com.choxmi.dropsworld.dropsworld.Fragments.HomeFragment;
import com.choxmi.dropsworld.dropsworld.Fragments.IconFragment;
import com.choxmi.dropsworld.dropsworld.R;

public class MainActivity extends AppCompatActivity {

    //Init Tab
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private final int HOME = 1;
    private final int ICON = 0;
    private final int CHAT = 2;
    private TabLayout tabLayout;
    private int[] icons = {R.drawable.ic_blur_icon,R.drawable.ic_home,R.drawable.ic_chat,R.drawable.ic_blur_icon_hi,R.drawable.ic_home_hi,R.drawable.ic_chat_hi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(HOME);
        //tab setting up
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(icons[0]);
        tabLayout.getTabAt(1).setIcon(icons[4]);
        tabLayout.getTabAt(2).setIcon(icons[2]);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(icons[tab.getPosition()+3]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(icons[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position==ICON){
                return IconFragment.newInstance();
            } if (position==HOME){
                return HomeFragment.newInstance();
            } if (position==CHAT){
                return ChatFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case ICON:
                    return "";
                case HOME:
                    return "";
                case CHAT:
                    return "";
            }
            return null;
        }
    }
}
