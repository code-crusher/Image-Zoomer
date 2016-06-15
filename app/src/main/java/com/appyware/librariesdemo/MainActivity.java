package com.appyware.librariesdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.vatsal.imagezoomer.ZoomAnimation;

public class MainActivity extends AppCompatActivity {

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ImageButton imageButton = (ImageButton) findViewById(R.id.image_button);

        activity = this;

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZoomAnimation zoomAnimation = new ZoomAnimation(activity);
                zoomAnimation.zoom(v, 600);
            }
        });
    }
}
