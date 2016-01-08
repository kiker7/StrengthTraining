package com.example.rafal.strengthtraining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Kiker on 08.01.16.
 * Strength Training
 */
public class ViewTraininListAdapter extends ArrayAdapter<String> {

    private String[] array;
    private  int[] positionArray;
    private Context context;

    public ViewTraininListAdapter(Context context, int resource, String[] array, int[] arr) {
        super(context, resource);
        this.array = array;
        this.positionArray = arr;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_training_item,null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.training_item);
        textView.setText(array[position]);

        ImageView img = (ImageView) convertView.findViewById(R.id.badgeImg);
        for(int i =0 ; i < positionArray.length; i++){
            if (positionArray[i] == position)
                img.setImageResource(R.drawable.badge_24);
        }

        return convertView;
    }
}
