package com.example.rafal.strengthtraining;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Kiker on 06.01.16.
 * Strength Training
 */
public class Guidebook extends Activity {

    private TextView description, howTo, advises;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guidebook);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        description = (TextView) findViewById(R.id.TCdescription);

        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo(0, (int) ((TextView) findViewById(R.id.guide_description)).getY());
            }
        });

        howTo = (TextView) findViewById(R.id.TChowTo);

        howTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo(0, (int) ((TextView) findViewById(R.id.guide_howTo)).getY());
            }
        });

        advises = (TextView) findViewById(R.id.TCadvise);

        advises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo(0, (int) ((TextView) findViewById(R.id.guide_advise)).getY());
            }
        });
    }
}
