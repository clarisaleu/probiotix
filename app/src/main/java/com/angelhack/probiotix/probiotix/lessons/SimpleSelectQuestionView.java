package com.angelhack.probiotix.probiotix.lessons;

import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.angelhack.probiotix.probiotix.R;
import com.angelhack.probiotix.probiotix.Utils;

import java.util.ArrayList;

/**
 * Created by William Zulueta on 7/21/18.
 */

public class SimpleSelectQuestionView extends QuestionFragment implements View.OnClickListener
{
    private ArrayList<AnswerView> _answerViews;
    private AnswerView _selectedView;

    @Override
    View createView(Question question)
    {
        _answerViews = new ArrayList<>();

        View main = View.inflate(getActivity(), R.layout.view_selection, null);

        TextView title = main.findViewById(R.id.selectionTitle);
        title.setText(question.getQuestionText());

        TextView subtitle = main.findViewById(R.id.choiceTitle);
        subtitle.setText("(select one)");

        LinearLayout container = main.findViewById(R.id.buttonContainer);
        container.setWeightSum(_question.getAnswers().size());

        ImageView imageView = main.findViewById(R.id.volumeImage);

        if (question.getAudioPath() != null)
        {
            imageView.setVisibility(View.VISIBLE);
            imageView.setOnClickListener(this);
        } else
        {
            imageView.setVisibility(View.GONE);
        }

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
        return _selectedView != null;
    }

    @Override
    void warn()
    {
        Toast.makeText(getActivity(), "You have to select a question to continue!", Toast.LENGTH_SHORT).show();
    }

    @Override
    boolean close()
    {
        return _selectedView.isCorrectAnswer() && _selectedView.isHighlighted();
    }

    @Override
    public void onClick(View view)
    {
        if (view instanceof AnswerView)
        {
            if (_selectedView != null)
                _selectedView.highlight();

            _selectedView = (AnswerView) view;
            _selectedView.highlight();

        }

        if (view.getId() == R.id.volumeImage)
        {
            MediaPlayer mediaPlayer = Utils.playAudioEffect(_question.getAudioPath(), getContext());
            // we can hold onto this media player instance if we want to stop the audio effect later
        }
    }
}
