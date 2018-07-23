package com.angelhack.probiotix.probiotix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.angelhack.probiotix.probiotix.lessons.Answer;
import com.angelhack.probiotix.probiotix.lessons.Lesson;
import com.angelhack.probiotix.probiotix.lessons.LessonActivity;
import com.angelhack.probiotix.probiotix.lessons.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//import com.angelhack.probiotix.probiotix.lessons.Answer;
//import com.angelhack.probiotix.probiotix.lessons.Question;
//import com.angelhack.probiotix.probiotix.lessons.SimpleSelectQuestion;
//import org.yaml.snakeyaml.Constructor;

/**
 * Created by William Zulueta on 7/21/18.
 */

public class StartActivity extends AppCompatActivity implements View.OnClickListener
{
    public static final String BUNDLE_KEY = "bundleKey";
    private Button _startButton;
    private ArrayList<Lesson> _lessons = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        try
        {
            _lessons = loadLessons(getAssets().open("lessons.json"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        assert _lessons != null : "Lessons is null! Quitting!";

        for (Lesson lesson : _lessons)
        {
            Log.i("Lesson", lesson._name);
            for (Question question : lesson._questions)
            {
                Log.i("Question", question._question);
            }
        }

        _startButton = findViewById(R.id.startButton);
        _startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == _startButton.getId())
        {
            // We know that when we click the Start Learning button that we are going to launch lesson 1
            launchLesson(_lessons.get(0));
        }
    }

    private void launchLesson(Lesson lesson)
    {
        Intent intent = new Intent(getApplicationContext(), LessonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_KEY, lesson);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        assert false : "This should fail";
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.lesson1:
                launchLesson(_lessons.get(0));
                return true;
            default:
                launchLesson(_lessons.get(1));
                return true;
        }
    }

    //////////////
    //// JSON extraction functions, these really should be moved to a seperate file.
    //////////////

    private ArrayList<Lesson> loadLessons(InputStream in) throws IOException
    {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try
        {
            return readLessonsArray(reader);
        } finally
        {
            reader.close();
        }
    }

    private ArrayList<Lesson> readLessonsArray(JsonReader reader) throws IOException
    {
        ArrayList<Lesson> lessons = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext())
        {
            lessons.add(readLesson(reader));
        }
        reader.endArray();
        return lessons;
    }

    private Lesson readLesson(JsonReader reader) throws IOException
    {
        String name = null;
        List<Question> questions = null;

        reader.beginObject();
        while (reader.hasNext())
        {
            name = reader.nextName();
            if (name.equals("name"))
            {
                name = reader.nextString();
            } else if (name.equals("questions"))
            {
                questions = readQuestionsArray(reader);
            } else
            {
                reader.skipValue();
            }
        }
        reader.endObject();

        return new Lesson(name, questions);

    }

    private List<Question> readQuestionsArray(JsonReader reader) throws IOException
    {
        List<Question> questions = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext())
        {
            questions.add(readQuestion(reader));
        }
        reader.endArray();
        return questions;
    }

    private Question readQuestion(JsonReader reader) throws IOException
    {
        String name = null;

        Question.QuestionType type = null;
        String question = null;
        ArrayList<Answer> answers = null;
        String audioFile = null;

        reader.beginObject();
        while (reader.hasNext())
        {
            name = reader.nextName();
            if (name.equals("type"))
            {
                switch (reader.nextString())
                {
                    case "SimpleSelect":
                        type = Question.QuestionType.SIMPLE_SELECT;
                        break;
                    case "SelectAll":
                        type = Question.QuestionType.SELECT_ALL;
                        break;
                    case "Matching":
                        type = Question.QuestionType.MATCHING;
                        break;
                    default:
                        assert false : "Unknown type " + name;
                }
            } else if (name.equals("text"))
            {
                question = reader.nextString();
            } else if (name.equals("answers"))
            {
                answers = readAnswersArray(reader);
            } else if (name.equals("audioFile"))
            {
                audioFile = reader.nextString();
            } else
            {
                reader.skipValue();
            }
        }
        reader.endObject();

        return new Question(type, question, answers, audioFile);

    }

    private ArrayList<Answer> readAnswersArray(JsonReader reader) throws IOException
    {
        ArrayList<Answer> answers = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext())
        {
            answers.add(readAnswer(reader));
        }
        reader.endArray();
        return answers;
    }

    private Answer readAnswer(JsonReader reader) throws IOException
    {
        String name = null;

        String text = null;
        boolean correct = false;
        String imagePath = null;
        int match_id = -1;
        String audioPath = null;

        reader.beginObject();
        while (reader.hasNext())
        {
            name = reader.nextName();
            if (name.equals("text"))
            {
                text = reader.nextString();
            } else if (name.equals("correct"))
            {
                correct = reader.nextBoolean();
            } else if (name.equals("imagePath"))
            {
                imagePath = reader.nextString();
            } else if (name.equals("matchID"))
            {
                match_id = reader.nextInt();
            } else if (name.equals("audioPath"))
            {
                audioPath = reader.nextString();
            } else
            {
                reader.skipValue();
            }
        }
        reader.endObject();

        return new Answer(text, correct, imagePath, match_id, audioPath);
    }
}
