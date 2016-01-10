package com.example.rafal.strengthtraining;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Kiker on 06.01.16.
 * Strength Training
 */
public class Guidebook extends Activity {

    private TextView description, howTo, advises,desc, how, adv,header;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guidebook);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Dustismo_Roman.ttf");
        header = (TextView) findViewById(R.id.guideHeader);
        header.setTypeface(customFont);
        header.setTextSize(70);
        header.setTextColor(Color.WHITE);
        desc = (TextView) findViewById(R.id.guide_description);
        desc.setTypeface(customFont);
        desc.setTextSize(20);
        how = (TextView) findViewById(R.id.guide_howTo);
        how.setTypeface(customFont);
        how.setTextSize(20);
        adv = (TextView) findViewById(R.id.guide_advise);
        adv.setTypeface(customFont);
        adv.setTextSize(20);
        desc.setTextColor(Color.WHITE);
        how.setTextColor(Color.WHITE);
        adv.setTextColor(Color.WHITE);
        adv.setMovementMethod(LinkMovementMethod.getInstance());
        description = (TextView) findViewById(R.id.TCdescription);
        description.setTextColor(Color.WHITE);
        description.setTypeface(customFont);
        description.setTextSize(24);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo(0, (int) ((TextView) findViewById(R.id.guide_description)).getY());
            }
        });

        howTo = (TextView) findViewById(R.id.TChowTo);
        howTo.setTextColor(Color.WHITE);
        howTo.setTypeface(customFont);
        howTo.setTextSize(24);
        howTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo(0, (int) ((TextView) findViewById(R.id.guide_howTo)).getY());
            }
        });

        advises = (TextView) findViewById(R.id.TCadvise);
        advises.setTextColor(Color.WHITE);
        advises.setTypeface(customFont);
        advises.setTextSize(24);
        advises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo(0, (int) ((TextView) findViewById(R.id.guide_advise)).getY());
            }
        });
    }
}
