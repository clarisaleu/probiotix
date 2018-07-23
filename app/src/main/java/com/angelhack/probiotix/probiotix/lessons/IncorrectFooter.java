package com.angelhack.probiotix.probiotix.lessons;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.angelhack.probiotix.probiotix.R;

/**
 * Created by William Zulueta on 7/21/18.
 */

public class IncorrectFooter extends Footer implements View.OnClickListener
{
    private View _mainView;
    private Button _button;

    public IncorrectFooter(Context context)
    {
        super(context);
        inflateSelf();
    }

    public IncorrectFooter(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        inflateSelf();
    }

    private void inflateSelf()
    {
        _mainView = inflate(getContext(), R.layout.view_incorrect, null);
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
