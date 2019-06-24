package com.berkanaslan.yakalamaoyunu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* Global Variables */
    TextView timeText;
    TextView scoreText;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Button button;
    int score;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize */
        timeText = (TextView)findViewById(R.id.timeText);
        scoreText =(TextView)findViewById(R.id.scoreText);
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
        imageView5 = (ImageView)findViewById(R.id.imageView5);
        imageView6 = (ImageView)findViewById(R.id.imageView6);
        imageView7 = (ImageView)findViewById(R.id.imageView7);
        imageView8 = (ImageView)findViewById(R.id.imageView8);
        imageView9 = (ImageView)findViewById(R.id.imageView9);
        button = (Button)findViewById(R.id.button);
        /* imageView ID Array */
        imageArray = new ImageView[] {imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        /* Hide Restart Button */
        button.setVisibility(View.INVISIBLE);

        /* Hide Images */
        hideImages();

        /* Score */
        score = 0;

        /* Time */
        CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {

                timeText.setText("Time: "+l/1000);
            }

            @Override
            public void onFinish() {

                /* TimeText */
                timeText.setText("Time is off.");

                /* Move stop */
                handler.removeCallbacks(runnable);

                /* Hide Images */
                for(ImageView image: imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                /* Alert Dialog */

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Game Over!");
                alertDialog.setMessage("Your score: "+score+"\nAre you sure to restart game?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        /* Restart game */
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        timeText.setText("Kenny is everywhere. :)");
                        /* Hide Images */
                        for(ImageView image: imageArray) {
                            image.setVisibility(View.VISIBLE);
                        }

                        /* unHide Restart Button */
                        button.setVisibility(View.VISIBLE);

                        /* Stop the score counter */
                        scoreText.setVisibility(View.INVISIBLE);

                    }
                });

                alertDialog.show();


            }
        }.start();

    }

    public void increaseScore(View view) {
        /* Score counter */
        score++;
        scoreText.setText("Score: "+score);
    }

    public void hideImages() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                /* Hide Images */
                for(ImageView image: imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                /* Random Location */
                Random random = new Random();
                int i = random.nextInt(8);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 500);
                }
            };

        /* Move start */
        handler.post(runnable);
    }

    public void restartButton(View view) {
        /* Restart game */
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
