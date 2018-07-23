package com.angelhack.probiotix.probiotix.lessons;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.angelhack.probiotix.probiotix.R;
import com.angelhack.probiotix.probiotix.ResultActivity;
import com.angelhack.probiotix.probiotix.StartActivity;

import java.util.List;

/**
 * Created by William Zulueta on 7/21/18.
 */

public class LessonActivity extends FragmentActivity implements View.OnClickListener, Footer.onFooterClicked
{
    public static final String KEY_CORRECT = "correct";
    public static final String KEY_COUNT = "count";

    private Lesson _lesson;
    private List<Question> _questions;
    private boolean[] _userResults;
    private int _index;
    private int _correctCount;
    private boolean _userFinished;
    private Button _nextButton;
    private Button _closeButton;
    private ProgressBar _progressBar;
    private QuestionFragment _fragment;

    private CorrectFooter _correctFooter;
    private IncorrectFooter _incorrectFooter;
    private RetryFooter _retryFooter;

    @Override
    public void onCreate(Bundle saved)
    {
        super.onCreate(saved);
        setContentView(R.layout.activity_lesson);

        _lesson = (Lesson) getIntent().getExtras().getSerializable(StartActivity.BUNDLE_KEY);
        _questions = _lesson.getQuestions();

        _nextButton = findViewById(R.id.submitButton);
        _nextButton.setOnClickListener(this);

        _closeButton = findViewById(R.id.closeButton);
        _closeButton.setOnClickListener(this);

        _progressBar = findViewById(R.id.progressBar);
        _progressBar.setProgress(0);

        _correctFooter = findViewById(R.id.correctFooter);
        _correctFooter.setOnFooterClicked(this);
        _incorrectFooter = findViewById(R.id.incorrectFooter);
        _incorrectFooter.setOnFooterClicked(this);
        _retryFooter = findViewById(R.id.retryFooter);

        _progressBar.setMax(_questions.size());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        _fragment = getFragmentForType(_questions.get(0));

        transaction.add(R.id.frameLayout, _fragment, "question");
        transaction.commit();
    }

    public void signalClose()
    {
        onClick(_nextButton);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == _nextButton.getId())
        {
            if (_userFinished)
            {
                closeSelf();
                return;
            }

            if (_fragment.canClose())
            {
                _nextButton.setVisibility(View.GONE);
                boolean userWasRight = _fragment.close();  // close the fragment and get result
                if (userWasRight)
                {
                    _correctFooter.setVisibility(View.VISIBLE);
                    ++_correctCount;
                } else
                {
                    _incorrectFooter.setVisibility(View.VISIBLE);
                }

                ++_index;
                _progressBar.setProgress(_index);

                if (_index == _questions.size())
                {
//                    closeSelf();
//                    _closeButton.setVisibility(View.VISIBLE);
//                    _incorrectFooter.hideNextButton();
//                    _correctFooter.hideNextButton();
                    _userFinished = true;
                    return;
                }
            } else
            {
                _fragment.warn();
            }
        } else if (view.getId() == _closeButton.getId())
        {
            closeSelf();
        }
    }

    private void closeSelf()
    {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra(KEY_CORRECT, _correctCount);
        intent.putExtra(KEY_COUNT, _questions.size());
        startActivity(intent);
        finish();
    }

    private QuestionFragment getFragmentForType(Question question)
    {
        QuestionFragment fragment;
        switch (question.getType())
        {
            case SELECT_ALL:
                fragment = new MultiSelectQuestionView();
                break;
            case MATCHING:
                fragment = new MatchingQuestionView();
                break;
            case SIMPLE_SELECT:
            default:
                fragment = new SimpleSelectQuestionView();
                break;
        }

        fragment.setQuestion(question);
        return fragment;
    }

    private void moveToNextQuestion()
    {
        // todo animations/pause
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // this is were we load a different view based on question type
        // the view will know how to configure itself

        _fragment = getFragmentForType(_questions.get(_index));

        transaction.replace(R.id.frameLayout, _fragment, "question");
        transaction.commit();
    }

    @Override
    public void onClicked()
    {
        if (_userFinished)
        {
            closeSelf();
            return;
        }

        _correctFooter.setVisibility(View.GONE);
        _incorrectFooter.setVisibility(View.GONE);
        _nextButton.setVisibility(View.VISIBLE);

        moveToNextQuestion();
    }
}
