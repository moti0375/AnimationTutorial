package com.tikal.animationtutorial;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;

import java.util.Random;

import static android.animation.ValueAnimator.ofFloat;

public class HitActivity extends AppCompatActivity {

    public static final int ANIMATION_DURATION = 1400;
    private static final String TAG = HitActivity.class.getSimpleName();
    ObjectAnimator animator1;
    ObjectAnimator animator2;

    Button button;
    Random random;

    int width;
    int height;

    AnimatorSet animatorSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hit);

        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();
        random = new Random();

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "button clicked");
                if (animatorSet != null){
                    if(animatorSet.isStarted()){
                        animatorSet.cancel();
                    }else{
                        animatorSet.start();
                    }
                }
            }
        });

        animatorSet = createAnimation();

        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(TAG, "onAnimationEnd");
                int nextX = random.nextInt(width);
                int nextY = random.nextInt(height);

                animator1 = ObjectAnimator.ofFloat(button, View.X, button.getX(), nextX);
                animator1.setDuration(ANIMATION_DURATION);

                animator2 = ObjectAnimator.ofFloat(button, View.Y, button.getY(), nextY);
                animator2.setDuration(ANIMATION_DURATION);

                animatorSet.playTogether(animator1, animator2);
                animatorSet.start();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private AnimatorSet createAnimation() {

        int nextX = random.nextInt(width);
        int nextY = random.nextInt(height);


        animator1 = ObjectAnimator.ofFloat(button, View.X, nextX);
        animator1.setDuration(ANIMATION_DURATION);

        animator2 = ObjectAnimator.ofFloat(button, View.Y, nextY);
        animator2.setDuration(ANIMATION_DURATION);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator1, animator2);

        return set;

    }
}
