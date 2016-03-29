package com.dcaoyz.fotag;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by dcaoyz on 2016-03-29.
 */
public class ViewImage extends LinearLayout implements Observer {
    private ModelImage model;
    private RatingBar ratingBar;

    public ViewImage(Context context, ModelImage m) {
        super(context);

        View.inflate(context, R.layout.image, this);

        this.model = m;
        m.addObserver(this);

        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(getResources().getIdentifier(model.resourceName, "drawable", context.getPackageName()));

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                model.updateRating((int) rating);
            }
        });
    }

    @Override
    public void update(Observable observable, Object data) {
        ratingBar.setRating(model.rating);
    }
}
