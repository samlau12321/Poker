package com.neusoft.entityDAO.impl;

import com.neusoft.entity.TestTemplate.Questions;
import com.neusoft.entityDAO.QuestionDAO;
import com.neusoft.utils.FileUtil;
import com.neusoft.utils.GsonUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl implements QuestionDAO {
    private static QuestionDAOImpl questionDAO = null;
    private QuestionDAOImpl(){
    }

    public static QuestionDAOImpl getQuestionDAO(){
        if (questionDAO == null){
            questionDAO = new QuestionDAOImpl();
        }

        return questionDAO;
    }

    String filename = "Questions.json";

    @Override
    public boolean addQuestion(Questions question) throws IOException {
        boolean add = true;
        List<Object> list = FileUtil.readDataToList(filename, Questions.class);
        if (list != null){
            for(int i = 0 ;i < list.size(); i++)
            {
                Questions question1 =(Questions) list.get(i);
                String id = question.getID();
                if (id.equals(question1.getID())){
                    add = false;
                    break;
                }
            }
        }
        String json_string = GsonUtil.toJson(question);
        if (json_string.length() > 0 && add){
            FileUtil.writeData(filename, json_string, true);
        }
        return add;
    }

    @Override
    public void updateQuestion(Questions question) throws IOException {
        List<Object> list = FileUtil.readDataToList(filename, Questions.class);//传入json数据代表的那个list
        List<Questions> questionsArrayList = new ArrayList<>();
        if (list != null){
            for (Object o : list){
                questionsArrayList.add((Questions) o);
            }
        }

        for (Questions questions : questionsArrayList){
            if (questions.getID().equals(question.getID())){
                questionsArrayList.set(questionsArrayList.indexOf(questions), question);
                break;
            }
        }

        StringBuilder data = new StringBuilder();
        for (Questions question_temp : questionsArrayList){
            if (questionsArrayList.indexOf(question_temp) == questionsArrayList.size() - 1){
                data.append(GsonUtil.toJson(question_temp));
            }
            else
                data.append(GsonUtil.toJson(question_temp)).append("\n");
        }
        FileUtil.writeData(filename, data.toString(), false);
    }

    @Override
    public void deleteQuestion(Questions question) throws IOException {
        List<Object> list = FileUtil.readDataToList(filename, Questions.class);//传入json数据代表的那个list
        List<Questions> questionsArrayList = new ArrayList<>();
        if (list != null){
            for (Object o : list){
                questionsArrayList.add((Questions) o);
            }
        }

        for (Questions question1 : questionsArrayList){
            if (question1.getID().equals(question.getID())){
                questionsArrayList.remove(question1);
                break;
            }
        }

        StringBuilder data = new StringBuilder();
        for (Questions question_temp : questionsArrayList){
            if (questionsArrayList.indexOf(question_temp) == questionsArrayList.size() - 1){
                data.append(GsonUtil.toJson(question_temp));
            }
            else
                data.append(GsonUtil.toJson(question_temp)).append("\n");
        }
        FileUtil.writeData(filename, data.toString(), false);
    }

    @Override
    public List<Questions> findAllQuestions() throws IOException {
        List<Object> list = FileUtil.readDataToList(filename, Questions.class);
        List<Questions> questionsList = new ArrayList<>();
        for (Object o : list){
            questionsList.add((Questions) o);
        }
        return questionsList;
    }

    @Override
    public Questions findQuestionByID(String ID) throws IOException {
        Questions question = null;
        List<Object> list = FileUtil.readDataToList(filename, Questions.class);
        List<Questions> questionsList = new ArrayList<>();
        for (Object o : list){
            questionsList.add((Questions) o);
        }

        for (Questions questions : questionsList){
            if (questions.getID().equals(ID)){
                question = questions;
            }
        }

        return question;
    }
}
