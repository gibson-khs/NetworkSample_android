package kr.pm10.networksample.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyCircleImageView extends CircleImageView {

    Context context;

    public MyCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    //    리사이징 생략
    public void imageLoad(String url) {
        Log.e("url : ", url);
        Glide.with(context)
                .load(url)
                .asBitmap()
                .into(this);
    }
}
