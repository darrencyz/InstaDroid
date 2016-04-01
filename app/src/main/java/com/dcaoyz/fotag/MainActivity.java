package com.dcaoyz.fotag;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RetainedFragment dataFragment;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getFragmentManager();
        dataFragment = (RetainedFragment) fm.findFragmentByTag("dataFragment");

        // create the fragment and data the first time
        if (dataFragment == null) {
            // add the fragment
            dataFragment = new RetainedFragment();
            fm.beginTransaction().add(dataFragment, "dataFragment").commit();
            dataFragment.setModel(new Model());
        }

        model = dataFragment.getModel();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //model = new Model();

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                model.updateRating((int) rating);
            }
        });
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
            for (int i = 0; i < 1; i++) {
                model.addImage(new ModelImage("image" + i));
            }
            return true;
        }

        if (id == R.id.action_clear) {
            model.clear();
            return true;
        }

        if (id == R.id.action_search) {
            final android.view.View searchDialog = LayoutInflater.from(this).inflate(R.layout.search_dialog, null);
            final EditText userInput = (EditText) searchDialog.findViewById(R.id.urlField);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Invalid Image URL!");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Dismiss",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

//            builder1.setNegativeButton(
//                    "No",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.cancel();
//                        }
//                    });

            final AlertDialog alert11 = builder1.create();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setView(searchDialog);
            alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String url = userInput.getText().toString();
                        boolean isImage;

                        try {
                            URLConnection connection = new URL(url).openConnection();
                            String contentType = connection.getHeaderField("Content-Type");
                            isImage = contentType.startsWith("image/");
                        }
                        catch (IOException e) {
                            isImage = false;
                        }

                        if (isImage) {
                            ModelImage image = new ModelImage(url, true);
                            model.addImage(image);
                        }
                        else {
                            alert11.show();
                        }
                    }
                });
            alertDialogBuilder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            alertDialogBuilder.setCancelable(false);

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        // store the data in the fragment
//        dataFragment.setModel(model);
//    }
}
