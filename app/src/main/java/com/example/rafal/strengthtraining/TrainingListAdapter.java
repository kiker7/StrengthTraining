package com.example.rafal.strengthtraining;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Kiker on 02.01.16.
 * Strength Training
 */
public class TrainingListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> headerList;
    private HashMap<String, List<String>> childList;

    public TrainingListAdapter(Context context, List<String> headerList, HashMap<String, List<String>> childList) {
        this.context = context;
        this.headerList = headerList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return this.headerList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childList.get(headerList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.childList.get(this.headerList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.training_detail_header,null);
        }

        TextView labelListHeader = (TextView) convertView.findViewById(R.id.training_header);
        labelListHeader.setTypeface(null, Typeface.BOLD);
        labelListHeader.setText(headerTitle);


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.training_detail_subitem,null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.training_list_subitem);
        txtListChild.setText(childText);
        ImageView img = (ImageView) convertView.findViewById(R.id.training_img);

        switch (childPosition){
            case 0:
                img.setImageResource(R.drawable.biceps_48);
            case 4:
                img.setImageResource(R.drawable.triceps_48);
            case 8:
                img.setImageResource(R.drawable.barki_48);
            case 12:
                img.setImageResource(R.drawable.klatka_48);
            case 16:
                img.setImageResource(R.drawable.plecy_48);
            case 20:
                img.setImageResource(R.drawable.nogi_48);
            case 24:
                img.setImageResource(R.drawable.brzuch_48);
        }

        // zrobic jeszcze dla objetosciowego treningu gdzie od 4 tyg po 4 cwiczenia sa

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

