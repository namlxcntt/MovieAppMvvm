package com.example.mvvmarchitectureappmovie.ui.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context)
    { super(context); applyCustomFont(context); }

    public CustomTextView(Context context, AttributeSet set)
    { super(context,set); applyCustomFont(context); }

    public CustomTextView(Context context, AttributeSet set, int defaultStyle)
    { super(context,set,defaultStyle); applyCustomFont(context); }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("robotoblack.ttf", context);
        setTypeface(customFont);
    }
}
