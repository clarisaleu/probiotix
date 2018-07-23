package com.angelhack.probiotix.probiotix.lessons;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.angelhack.probiotix.probiotix.R;

import java.util.ArrayList;

/**
 * Created by William Zulueta on 7/21/18.
 */

public class MatchingQuestionView extends QuestionFragment implements View.OnClickListener
{
    private ArrayList<AnswerView> _answerViews;
    private ArrayList<AnswerView> _selectedViews = new ArrayList<>();

    @Override
    View createView(Question question)
    {
        _answerViews = new ArrayList<>();

        View main = View.inflate(getActivity(), R.layout.view_selection, null);

        TextView title = main.findViewById(R.id.selectionTitle);
        title.setText(question.getQuestionText());

        TextView subtitle = main.findViewById(R.id.choiceTitle);
        subtitle.setText("(match two at a time)");

        LinearLayout container = main.findViewById(R.id.buttonContainer);
        container.setWeightSum(_question.getAnswers().size());
        for (Answer answer : question.getAnswers())
        {
            AnswerView av = new AnswerView(answer, getContext());
            av.setOnClickListener(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            params.setMargins(4, 4, 4, 4);
            container.addView(av, params);
            _answerViews.add(av);
        }
        return main;
    }

    @Override
    public boolean canClose()
    {
        for (AnswerView answerView : _answerViews)
            if (!answerView.disabled())
                return false;
        return true;
    }

    @Override
    void warn()
    {
        if (_selectedViews.size() != 2) {
            Toast.makeText(getActivity(), "You have to select two to continue!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (_selectedViews.get(0).getAnswerId() != _selectedViews.get(1).getAnswerId())
        {
            Toast.makeText(getContext(), "Try again!", Toast.LENGTH_SHORT).show();
            for (AnswerView answerView : _selectedViews)
            {
                answerView.highlight();
            }
            _selectedViews.clear();
        }
        else if (_selectedViews.get(0).getAnswerId() == _selectedViews.get(1).getAnswerId())
        {

            for (AnswerView answerView : _selectedViews)
            {
                answerView.highlight();
                answerView.disable();
            }
            _selectedViews.clear();

            if (canClose())
            {
                LessonActivity lessonActivity = (LessonActivity) getActivity();
                lessonActivity.signalClose();
                return;
            }

            Toast.makeText(getContext(), "Nice job!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    boolean close()
    {
//        for (AnswerView view : _answerViews)
//            if ((view.isHighlighted() && !view.isCorrectAnswer()) || (!view.isHighlighted() && view.isCorrectAnswer()))
//                return false;

        return true;
    }

    @Override
    public void onClick(View view)
    {
        if (view instanceof AnswerView)
        {
            AnswerView answerView = (AnswerView) view;

            if (answerView.isHighlighted())
            {
                answerView.highlight();
                _selectedViews.remove(answerView);
                return;
            }

            if (_selectedViews.size() == 2)
            {
                Toast.makeText(getContext(), "You can't select more than 2!", Toast.LENGTH_SHORT).show();
                return;
            }

            answerView.highlight();

            if (_selectedViews.contains(answerView))
                _selectedViews.remove(answerView);
            else
                _selectedViews.add(answerView);

        }
    }
}
