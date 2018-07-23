package com.angelhack.probiotix.probiotix;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.angelhack.probiotix.probiotix.lessons.LessonActivity;

/**
 * Created by William Zulueta on 7/22/18.
 */

public class ResultActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        int correctQuestionCount = getIntent().getIntExtra(LessonActivity.KEY_CORRECT, 0);
        int questionCount = getIntent().getIntExtra(LessonActivity.KEY_COUNT, 0);
        setContentView(R.layout.view_congrats);
        Button button = findViewById(R.id.submitButton4);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

}
