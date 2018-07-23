package com.angelhack.probiotix.probiotix.lessons;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.angelhack.probiotix.probiotix.R;

/**
 * Created by William Zulueta on 7/22/18.
 */

public class RetryFooter extends Footer
{
    private View _mainView;
    public RetryFooter(Context context)
    {
        super(context);
        inflateSelf();
    }

    public RetryFooter(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        inflateSelf();
    }

    private void inflateSelf()
    {
        _mainView = inflate(getContext(), R.layout.view_retry, null);
        addView(_mainView);
    }
}
