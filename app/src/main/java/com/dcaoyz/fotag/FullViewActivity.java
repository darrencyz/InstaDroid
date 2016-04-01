package com.dcaoyz.fotag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class FullViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView image = (ImageView) findViewById(R.id.image);
        Intent intent = getIntent();
        String resourceName = intent.getStringExtra("image");
        boolean isUrl = Boolean.valueOf(intent.getStringExtra("isUrl"));
        if (isUrl) {
            new UrlImageTask(image).execute(resourceName);
        }
        else {
            image.setImageResource(getResources().getIdentifier(resourceName, "drawable", getPackageName()));
        }
    }
}
