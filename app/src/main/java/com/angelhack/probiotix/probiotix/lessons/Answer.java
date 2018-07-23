package com.angelhack.probiotix.probiotix.lessons;

import java.io.Serializable;

/**
 * Created by William Zulueta on 7/21/18.
 */

public class Answer implements Serializable
{
    private String _text;
    private boolean _isCorrect;
    private String _imagePath;
    private int _match_id;
    private String _audioPath;
    // image ?

    public Answer(String text, boolean isCorrect, String imagePath, int match_id, String audioPath)
    {
        _text = text;
        _isCorrect = isCorrect;
        _imagePath = imagePath;
        _match_id = match_id;
        _audioPath = audioPath;
    }

    public String getText()
    {
        return _text;
    }

    public String getAudioPath()
    {
        return _audioPath;
    }

    public String getImagePath()
    {
        return _imagePath;
    }

    public boolean isCorrect()
    {
        return _isCorrect;
    }

    public int getMatchId()
    {
        return _match_id;
    }
}
