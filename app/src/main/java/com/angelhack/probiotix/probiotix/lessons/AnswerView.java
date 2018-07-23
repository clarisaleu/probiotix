package com.angelhack.probiotix.probiotix.lessons;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.angelhack.probiotix.probiotix.R;

/**
 * Created by William Zulueta on 7/21/18.
 */

public class AnswerView extends LinearLayout implements View.OnClickListener
{
    private Answer _answer;
    private boolean _isSelected;
    private View _main;
    //    private TextView _text;
    //    private ImageView _image;
    private Button _button;
    private boolean _isEnabled = true;
    private MediaPlayer _mediaPlayer;

    private OnClickListener _onClickListener;

    public AnswerView(Answer answer, Context context)
    {
        super(context);
        _answer = answer;
        inflateView();
    }

    private void inflateView()
    {
        _main = View.inflate(getContext(), R.layout.view_selection_button, null);
        _button = _main.findViewById(R.id.buttonImage);
        _button.setText(_answer.getText());
        _button.setOnClickListener(this);
        //        _text = _main.findViewById(R.id.buttonText);
        //        _text.setText(_answer.getText());
        //        _image = _main.findViewById(R.id.buttonImage);
        addView(_main, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        _main.setOnClickListener(this);
    }

    public void highlight()
    {
        _isSelected = !_isSelected;
        if (_isSelected)
        {
            //TODO: Find the button obje
//            _main.setBackground(getResources().getDrawable(R.drawable.border_highlight));
            _main.setBackground(getResources().getDrawable(R.drawable.pressed_button));
        } else
        {
//            _main.setBackgroundColor(getResources().getColor(R.color.gray));
            _main.setBackground(getResources().getDrawable(R.drawable.rounded_button));
        }

        //play audio?
//        if (_answer.getAudioPath() != null)
//        {
//            _mediaPlayer = Utils.playAudioEffect(_answer.getAudioPath(), getContext());
//            _mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
//            {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer)
//                {
//                    _mediaPlayer.reset();
//                    _mediaPlayer.release();
//                    _mediaPlayer = null;
//                }
//            });
//        }
    }

    public void enable()
    {
//        setEnabled(true);
        _isEnabled = true;
    }

    public void disable()
    {
//        setEnabled(false);
        setVisibility(GONE);
        _isEnabled = false;
    }

    public boolean disabled()
    {
        return !_isEnabled;
    }

    @Override
    public void onClick(View view)
    {
        if (_onClickListener != null)
            _onClickListener.onClick(this);
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener)
    {
        _onClickListener = onClickListener;
    }

    public boolean isHighlighted()
    {
        return _isSelected;
    }

    public boolean isCorrectAnswer()
    {
        return _answer.isCorrect();
    }

    public int getAnswerId()
    {
        return _answer.getMatchId();
    }
}
