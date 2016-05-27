package com.sourcey.materiallogindemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
/**
 * Created by apple on 16/5/19.
 */
public class done extends AppCompatActivity {

    private MyCustomView mView;
    private Movie mMovie;
    private long mMovieStart;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new MyCustomView(this);
        setContentView(mView);
    }


    class MyCustomView extends View {
        public MyCustomView(Context context) {
            super(context);
            mMovie = Movie.decodeStream(getResources().openRawResource(
                    + R.drawable.gif_load));
        }

        @Override
        protected void onDraw(Canvas canvas) {
            long curTime = android.os.SystemClock.uptimeMillis();

            if (mMovieStart == 0) {
                mMovieStart = curTime;
            }

            if (mMovie != null) {
                int duration = mMovie.duration();

                int relTime = (int) ((curTime - mMovieStart) % duration);
                mMovie.setTime(relTime);
                mMovie.draw(canvas, 0, 0);


                invalidate();
            }
            super.onDraw(canvas);
        }
    }
}
