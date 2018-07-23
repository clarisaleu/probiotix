package com.angelhack.probiotix.probiotix.lessons;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by William Zulueta on 7/21/18.
 */

public class Footer extends LinearLayout
{
    protected onFooterClicked _onFooterClicked;

    public Footer(Context context)
    {
        super(context);
    }

    public Footer(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void setOnFooterClicked(onFooterClicked onFooterClicked)
    {
        _onFooterClicked = onFooterClicked;
    }

    public interface onFooterClicked
    {
        void onClicked();
    }

}
