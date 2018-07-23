package com.angelhack.probiotix.probiotix.lessons;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by William Zulueta on 7/21/18.
 */



public class Question implements Serializable
{
    public enum QuestionType {
        SIMPLE_SELECT,
        SELECT_ALL,
        MATCHING
    }

    public QuestionType _type;
    public String _question;
    public ArrayList<Answer> _answers;
    public String _audioFile;

    public Question(QuestionType type, String question, ArrayList<Answer> answers, String audioFile) {
        _type = type;
        _question = question;
        _answers = answers;
        _audioFile = audioFile;
    };


    public String getQuestionText()
    {
        return _question;
    }

    public QuestionType getType()
    {
        return _type;
    }

    public ArrayList<Answer> getAnswers() // WARNING is mutable!
    {
        return _answers;
    }

    public String getAudioPath()
    {
        return _audioFile;
    }
}

