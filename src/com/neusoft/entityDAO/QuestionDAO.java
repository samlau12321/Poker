package com.neusoft.entityDAO;

import com.neusoft.entity.TestTemplate.Questions;

import java.io.IOException;
import java.util.List;

public interface QuestionDAO {
    public boolean addQuestion(Questions question) throws IOException;

    public void updateQuestion(Questions question) throws IOException;

    public void deleteQuestion(Questions question) throws IOException;

    public List<Questions> findAllQuestions() throws IOException;

    public Questions findQuestionByID(String ID) throws IOException;
}
