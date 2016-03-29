package com.dcaoyz.fotag;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by dcaoyz on 2016-03-29.
 */
public class View extends LinearLayout implements Observer {
    private Model model;
    // maybe add collection

    public View(Context context, Model m) {
        super(context);

        View.inflate(context, R.layout.content_main, this);

        this.model = m;
        m.addObserver(this);

        for (int i = 0; i < model.collection.size(); i++) {
            ViewImage imageView = new ViewImage(getContext(), model.collection.get(i));
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.contentmain);
            viewGroup.addView(imageView);
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.contentmain);
        viewGroup.removeAllViews();

        for (int i = 0; i < model.collection.size(); i++) {
            ViewImage imageView = new ViewImage(getContext(), model.collection.get(i));
            viewGroup.addView(imageView);
        }
    }
}
