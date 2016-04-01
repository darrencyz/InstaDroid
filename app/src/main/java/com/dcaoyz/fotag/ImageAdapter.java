//package com.dcaoyz.fotag;
//
//import java.util.ArrayList;
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Filter;
//import android.widget.Filterable;
//
///**
// * Created by dcaoyz on 2016-04-01.
// */
//public class ImageAdapter extends ArrayAdapter<ModelImage> implements Filterable {
//
//    private Filter filter;
//    private ArrayList<ModelImage> original = new ArrayList();
//    private ArrayList<ModelImage> filtered = new ArrayList();
//
//    public ImageAdapter(Context c, ArrayList<ModelImage> arrayList) {
//        super(c, 0, arrayList);
//        if(!arrayList.isEmpty()){
//            this.original = new ArrayList(arrayList);
//        }
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewImage itemView = (ViewImage)convertView;
//        if (null == itemView)
//            itemView = ItemView.inflate(parent);
//        itemView.setItem(getItem(position));
//
//        return itemView;
//
//    }
//
//    @Override
//    public Filter getFilter()
//    {
//        if (filter == null)
//            filter = new RatingFilter();
//
//        return filter;
//    }
//
//    private class RatingFilter extends Filter
//    {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint)
//        {
//            FilterResults results = new FilterResults();
//            int filter = Integer.valueOf(constraint.toString());
//
//            final ArrayList<ModelImage> list = new ArrayList<ModelImage>(original);
//            final ArrayList<ModelImage> filtered = new ArrayList<ModelImage>();
//            int count = list.size();
//
//            for (int i=0; i < count; i++)
//            {
//                final ModelImage modelImage = list.get(i);
//
//                if (modelImage.rating >= filter)
//                {
//                    filtered.add(modelImage);
//                }
//            }
//
//            results.values = filtered;
//            results.count = filtered.size();
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            filtered = (ArrayList<ModelImage>) results.values;
//            if(null == filtered){
//                return;
//            }
//
//            clear();
//            int count = filtered.size();
//            for (int i=0; i<count; i++)
//            {
//                ModelImage item = (ModelImage) filtered.get(i);
//                filtered.add(item);
//            }
//        }
//
//    }
//}
//
