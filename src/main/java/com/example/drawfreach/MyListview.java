package com.example.headerfreach;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by muzhi on 2017/4/4.
 */

public class MyListview extends ListView {

    private int maxHeight;
    private int initialHeight;

    public MyListview(Context context) {
        this(context, null);
    }

    public MyListview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    ImageView imageView;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
        initialHeight = getResources().getDimensionPixelOffset(R.dimen.dp120);

        maxHeight = imageView.getDrawable().getIntrinsicHeight();
    }

    /**
     * 该方法就是listview执行到首位的时候执行,可以获得滑行的距离
     *
     * @param deltaY         继续滑行的距离
     * @param scrollY        scrollto 方法产生的距离
     * @param scrollRangeY
     * @param maxOverScrollY 到头后继续滑行的距离
     * @param isTouchEvent   是按着滑动(true)的还是惯性滑动的(false)
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (deltaY < 0) {
            int newheight =  imageView.getHeight() - deltaY/3;//获取新的图片的高度
            Log.e("setImageView",initialHeight + "");
            Log.e("overScrollBy",imageView.getHeight() + "");
            if (newheight > maxHeight) {
                newheight = maxHeight;
            }
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = newheight;
            imageView.setLayoutParams(layoutParams);
        }

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                ValueAnimator animator = ValueAnimator.ofInt(imageView.getHeight(),initialHeight);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                        layoutParams.height = animatedValue;
                        imageView.setLayoutParams(layoutParams);
                    }
                });
                animator.setInterpolator(new OvershootInterpolator());
                animator.setDuration(500);
                animator.start();
                break;

        }
        return super.onTouchEvent(ev);
    }
}
