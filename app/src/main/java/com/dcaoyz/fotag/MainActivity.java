package com.dcaoyz.fotag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RatingBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        model = new Model();

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                model.updateRating((int) rating);
            }
        });
//        Log.e("MVC", "create");
//        if (savedInstanceState != null) {
//            Log.e("MVC", "createsave");
//            ratingBar.setRating(savedInstanceState.getInt("Filter"));
//        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // add views
        View view = new View(this, model);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.mainactivity);
        viewGroup.addView(view);

        model.notifyObservers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_photos) {
            for (int i = 0; i < 2; i++) {
                model.addImage(new ModelImage("image" + i));
            }
            return true;
        }

        if (id == R.id.action_clear) {
            model.clear();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putInt("Filter", model.filter);
//        Log.e("MVC", "save");
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.e("MVC", "onresume");
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        model.updateRating(savedInstanceState.getInt("Filter"));
//        Log.e("MVC", "restore");
//        Log.e("MVC", String.valueOf(model.filter));
//        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
//        ratingBar.setRating(savedInstanceState.getInt("Filter"));
//        super.onRestoreInstanceState(savedInstanceState);
//    }
}
