package com.chk.animationlearning.customView;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.airbnb.lottie.model.layer.Layer;

import androidx.annotation.Nullable;

/**
 * Created by CHK on 20-5-26.
 */
public class LinearGradientView extends View {

    Paint mPaint;
    LinearGradient mLinearGradient;
    ValueAnimator valueAnimator;
    int mDx;

    public LinearGradientView(Context context) {
        super(context);
        init();
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        setLayerType(LAYER_TYPE_SOFTWARE,null);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initShader();
    }

    void initShader() {
        mLinearGradient = new LinearGradient(-getMeasuredWidth(),0,0,0,0xffff0000,0xff00ff00, Shader.TileMode.CLAMP);

        mPaint.setShader(mLinearGradient);
        mPaint.setTextSize(100);
        mPaint.setStrokeWidth(5);
        valueAnimator = ValueAnimator.ofInt(0,2 * getMeasuredWidth());
//        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        Matrix matrix = new Matrix();
        matrix.setTranslate(mDx,0);
        mLinearGradient.setLocalMatrix(matrix);
        canvas.drawText("你好啊",0,100,mPaint);
        canvas.drawRect(0,200,getWidth(),getHeight(),mPaint);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }
}
