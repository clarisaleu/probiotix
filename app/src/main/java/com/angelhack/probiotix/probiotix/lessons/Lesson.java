package com.angelhack.probiotix.probiotix.lessons;

import java.io.Serializable;
import java.util.List;

public class Lesson implements Serializable {
    public String _name;
    public List<Question> _questions;

    public Lesson(String name, List<Question> questions) {
        _name = name;
        _questions = questions;
    }

    public List<Question> getQuestions()
    {
        return _questions;
    }
}

