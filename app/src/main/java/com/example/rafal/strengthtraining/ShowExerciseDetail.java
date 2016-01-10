package com.example.rafal.strengthtraining;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafal.strengthtraining.data.Exercise;


/**
 * Created by Kiker on 19.12.15.
 * Strength Training
 */
public class ShowExerciseDetail extends Activity {

    TextView name;
    TextView description;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_details);

        name = (TextView) findViewById(R.id.exeNameDetail);
        description = (TextView) findViewById(R.id.exeDescriptionDetail);
        image = (ImageView) findViewById(R.id.exeImage);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/tertre-xbol.otf");

        Exercise exercise = (Exercise) getIntent().getSerializableExtra("Exercise");
        name.setText(exercise.getName());
        name.setTypeface(customFont);
        name.setTextSize(30);
        description.setText(exercise.getDescription());
        image.setImageResource(exercise.getImageResourceIdForExercise(this));
    }

}
