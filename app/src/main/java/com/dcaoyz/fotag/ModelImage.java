package com.dcaoyz.fotag;

import java.util.Observable;

/**
 * Created by dcaoyz on 2016-03-27.
 */

public class ModelImage extends Observable {
    int rating = 0;
    String resourceName;
    boolean isUrl;

    public ModelImage(String resourceName) {
        this.resourceName = resourceName;
        isUrl = false;
    }

    public ModelImage(String resourceName, boolean isUrl) {
        this.resourceName = resourceName;
        this.isUrl = isUrl;
    }

    public void updateRating(int rating) {
        this.rating = rating;
        setChanged();
        notifyObservers();
    }
}
