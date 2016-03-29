package com.dcaoyz.fotag;

import java.util.Observable;
import java.util.ArrayList;

/**
 * Created by dcaoyz on 2016-03-27.
 */
public class Model extends Observable {
    ArrayList<ModelImage> collection = new ArrayList<ModelImage>();
    int filter = 0;

    Model() {
        setChanged();
    }

    public void addImage(ModelImage image) {
        collection.add(image);
        setChanged();
        notifyObservers();
    }

    public void clear() {
        collection = new ArrayList<ModelImage>();
        setChanged();
        notifyObservers();
    }

    public void updateRating(int rating) {
        filter = rating;
        setChanged();
        notifyObservers();
    }
}
