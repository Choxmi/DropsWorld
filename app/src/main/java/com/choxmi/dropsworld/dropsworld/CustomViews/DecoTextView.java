package com.choxmi.dropsworld.dropsworld.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.choxmi.dropsworld.dropsworld.R;

/**
 * Created by Choxmi on 2/21/2018.
 */

public class DecoTextView extends android.support.v7.widget.AppCompatTextView {
    Typeface segoe;
    public DecoTextView(Context context) {
        super(context);
    }

    public DecoTextView(Context context, AttributeSet attrs) {
        super(context);
        setCustomFont(context,attrs);
    }

    public DecoTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context);
        setCustomFont(context,attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs,
                R.styleable.TextViewPlus);
        String customFont = a.getString(0);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx,String s) {
        try {
            segoe = Typeface.createFromAsset(ctx.getAssets(), "fonts/segoeuil.ttf");
        } catch (Exception e) {
            return false;
        }

        setTypeface(segoe);
        return true;
    }
}
