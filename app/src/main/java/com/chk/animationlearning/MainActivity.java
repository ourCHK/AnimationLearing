package com.chk.animationlearning;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chk.animationlearning.ui.linearGradient.LinearGradientActivity;
import com.chk.animationlearning.ui.lottie.LottieActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.LinearGradientLearning)
    Button gradientLearning;

    @BindView(R.id.LottieLearning)
    Button lottieLearning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.LinearGradientLearning,R.id.LottieLearning})
    void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.LinearGradientLearning:
                intent = new Intent(this, LinearGradientActivity.class);
                break;
            case R.id.LottieLearning:
                intent = new Intent(this, LottieActivity.class);
                break;
        }
        startActivity(intent);
    }


}
