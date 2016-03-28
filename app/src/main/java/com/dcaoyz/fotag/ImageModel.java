package com.dcaoyz.fotag;

import java.util.Observable;

/**
 * Created by dcaoyz on 2016-03-27.
 */

interface IRating {
    public int getRating();
    public void updateRating(int rating);
}

public class ImageModel extends Observable implements IRating {
    int rating = 0;

    public ImageModel() {
    }

    @Override
    public void updateRating(int rating) {
        this.rating = rating;
        setChanged();
        notifyObservers();
    }

    @Override
    public int getRating() {
        return rating;
    }
}
