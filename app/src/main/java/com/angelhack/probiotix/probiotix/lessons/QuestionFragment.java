package com.angelhack.probiotix.probiotix.lessons;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by William Zulueta on 7/21/18.
 */

public abstract class QuestionFragment extends Fragment
{
    protected Question _question;

    public void setQuestion(Question question)
    {
        _question = question;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return createView(_question);
    }

    abstract View createView(Question question);

    abstract boolean close();

    abstract boolean canClose();

    abstract void warn();
}
