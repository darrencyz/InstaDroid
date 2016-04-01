package com.dcaoyz.fotag;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;

/**
 * Created by dcaoyz on 2016-04-01.
 */
public class RetainedFragment extends Fragment {
    private Model model;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setModel(Model m) {
        model = m;
    }

    public Model getModel() {
        return model;
    }
}
