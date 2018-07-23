package com.angelhack.probiotix.probiotix.lessons;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.angelhack.probiotix.probiotix.R;

public class CorrectFooter extends Footer implements View.OnClickListener
{
    private View _mainView;
    private ImageView _flag;
    private ImageView _tick;
    private Button _button;

    public CorrectFooter(Context context)
    {
        super(context);
        create();
    }

    public CorrectFooter(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        create();
    }

    private void create()
    {
        _mainView = inflate(getContext(), R.layout.view_correct, null);
        _tick = _mainView.findViewById(R.id.tickmark);
        _tick.setImageResource(R.drawable.baseline_check_circle_black_48dp);
        _button = _mainView.findViewById(R.id.lessonNextButton);
        _button.setOnClickListener(this);
        addView(_mainView);
    }

    public void hideNextButton()
    {
        _button.setVisibility(INVISIBLE);
    }

    @Override
    public void onClick(View view)
    {
        if (_onFooterClicked != null)
            _onFooterClicked.onClicked();
    }
}
