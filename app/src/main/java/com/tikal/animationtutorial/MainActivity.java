package com.tikal.animationtutorial;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = MainActivity.class.getSimpleName();

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    DisplayMetrics displayMetrics;
    AnimatorSet set;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set = new AnimatorSet();

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        button1 = (Button) findViewById(R.id.Button01);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.Button02);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.Button03);
        button3.setOnClickListener(this);

        button4 = (Button) findViewById(R.id.Button04);
        button4.setOnClickListener(this);
    }

    void startAnimation(View view) {
        float dest = 0;

        ImageView imageView = (ImageView) findViewById(R.id.imageView1);

        switch (view.getId()) {
            case R.id.Button01:
                int repeat = 10;
                dest = 360 * repeat;
                if (imageView.getRotation() == dest) {
                    dest *= -1;
                    imageView.setRotation(0);
                }
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, View.ROTATION , dest);
                ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(imageView, View.ROTATION_X, dest);
                rotateAnimation.setDuration(repeat * 1000);
                objectAnimator.setDuration(repeat * 1000);
                set.playTogether(objectAnimator, rotateAnimation);
                set.start();
                break;
            case R.id.Button02:
                Paint paint= new Paint();
                TextView animTextView = (TextView) findViewById(R.id.textView1);
                float textSize = animTextView.getTextSize();
                final float densityMultiplier = getResources().getDisplayMetrics().density;
                //final float scaledPx = textSize * densityMultiplier;
                paint.setTextSize(densityMultiplier);
                float textWidth = animTextView.getPaint().measureText(animTextView.getText().toString());

                Log.i(TAG, "startAnimation: text width(px) = " + textWidth);
                dest = 0;
                if(animTextView.getX() == 0){
                    Log.i(TAG, "startAnimation: x = 0");
                    int displayWidth = displayMetrics.widthPixels;
                    dest = displayWidth - (textWidth);
                    Log.i(TAG, "startAnimation: display width = " + displayWidth + "\nDest = " + dest);
                }
                ObjectAnimator animator = ObjectAnimator.ofFloat(animTextView, View.X, dest);
                animator.setDuration(1000);
                animator.start();
                break;
            case R.id.Button03:
                dest = 1;

                if(imageView.getAlpha() > 0){
                    dest = 0;
                }

                ObjectAnimator alphAnimator = ObjectAnimator.ofFloat(imageView, View.ALPHA, dest);
                alphAnimator.setDuration(3000);
                alphAnimator.start();
                break;
            case R.id.Button04:

                int animDuration = 2000;

                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(imageView, View.ALPHA, 1f, 0f);
                fadeOut.setDuration(animDuration);

                ObjectAnimator mover = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, -500f, 0f);
                mover.setDuration(animDuration);

                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView, View.ALPHA, 0f, 1f);
                fadeIn.setDuration(animDuration);

                AnimatorSet animatiorSet = new AnimatorSet();

                animatiorSet.play(mover).with(fadeOut).with(fadeIn);
                animatiorSet.start();
                break;
        }

    }

    @Override
    public void onClick(View v) {
        startAnimation(v);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.item1){
            startActivity(new Intent(MainActivity.this, HitActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
