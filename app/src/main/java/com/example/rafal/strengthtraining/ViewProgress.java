package com.example.rafal.strengthtraining;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;


/**
 * Created by Kiker on 19.12.15.
 * Strength Training
 */
public class ViewProgress extends Activity {

    private PieChart chart;
    private float[] yData;
    private String[] xData = {"Wykonane","Do zrobienia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        yData = setChartYVal();

        chart = new PieChart(this);
        setContentView(chart);

        // konfiguracja wykresu
        chart.setUsePercentValues(true);
        chart.setDescription("Status ukończenia makrocyklu");
        chart.setDescriptionColor(Color.WHITE);
        chart.setDescriptionTextSize(20);
        chart.setDescriptionPosition(330,50);
        chart.setBackgroundColor(Color.DKGRAY);

        // koło wewnatrz diagramu
        chart.setDrawHoleEnabled(true);
        chart.setHoleColorTransparent(true);
        chart.setHoleRadius(7);
        chart.setTransparentCircleRadius(10);

        // rotacja wykresu
        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);

        // listener dla wartosci wykresu
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if(e == null)
                    return;
//                Toast.makeText(getApplicationContext(),xData[e.getXIndex()] + " = " + e.getVal() +"%" ,Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        addData();

        // dopasowanie opisu
        Legend l =chart.getLegend();
        l.setTextColor(Color.WHITE);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    private void addData() {
        ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        for(int i = 0 ; i < yData.length; i++)
            yVals.add(new Entry(yData[i],i));

        Collections.addAll(xVals, xData);

        PieDataSet dataSet = new PieDataSet(yVals,"");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        for(int c: ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(xVals,dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);

        chart.setData(data);
        chart.highlightValues(null);
        chart.invalidate();

    }

    private float[] setChartYVal() {
        SharedPreferences userPrefs = getApplicationContext().getSharedPreferences("Session", MODE_PRIVATE);
        String name = userPrefs.getString("userName",null);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(name, MODE_PRIVATE);
        Map<String, ?> allPreffs = preferences.getAll();
        Set<String> set = allPreffs.keySet();
        int truecount = 0;
        for(String e: set){
            if(allPreffs.get(e).equals(true)) {
                truecount++;
            }
        }
        return new float[]{truecount ,(8 - truecount)};
    }
}