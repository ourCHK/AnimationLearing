package com.chk.animationlearning.customView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by CHK on 20-5-26.
 */
public class LinearGradientTextView extends AppCompatTextView {

    Matrix matrix;
    Paint mPaint;
    LinearGradient mLinearGradient;
    ValueAnimator valueAnimator;

    int mDx;

    public LinearGradientTextView(Context context) {
        super(context);
    }

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mLinearGradient = new LinearGradient(-getMeasuredWidth(),0,0,0,new int[] {
                getCurrentTextColor(),0xff00ff00,getCurrentTextColor()
        },new float[] {
                0,0.5f,1f
        }, Shader.TileMode.CLAMP);

        mPaint = getPaint();
        valueAnimator = ValueAnimator.ofInt(0,2 * getMeasuredWidth());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (int) animation.getAnimatedValue();
                Log.i("TAG","mdx:"+mDx);
                postInvalidate();
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        matrix = new Matrix();
        matrix.setTranslate(mDx,0);
        mLinearGradient.setLocalMatrix(matrix);
        mPaint.setShader(mLinearGradient);

        super.onDraw(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }
}
