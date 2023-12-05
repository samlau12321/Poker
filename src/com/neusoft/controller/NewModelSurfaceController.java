package com.neusoft.controller;

import com.neusoft.entity.TestTemplate.Questions;
import com.neusoft.entityDAO.impl.QuestionDAOImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NewModelSurfaceController {

    public Button Back;
    public Button Add;
    public TextField description;
    public TextField ChoiceA;
    public TextField ChoiceB;
    public TextField ChoiceC;
    public TextField description0;
    public ComboBox typeOfQuestion;




    public void createQuestion(){
        QuestionDAOImpl questionDAO = QuestionDAOImpl.getQuestionDAO();
        Questions questions = new Questions();
        questions.setID(description0.getText());
        questions.setDescription(description.getText());
        questions.setType(typeOfQuestion.getValue().toString());
        questions.setChoiceA(ChoiceA.getText());
        questions.setChoiceB(ChoiceB.getText());
        questions.setChoiceC(ChoiceC.getText());

        try {
            boolean add = questionDAO.addQuestion(questions);
            if (add){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("提示");
                alert.setContentText("问题添加成功！");
                alert.show();
            }

            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("问题添加失败！请检查！");
                alert.setHeaderText("警告");
                alert.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void click_back() {
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.close();
    }

    public void click_add() {
        createQuestion();
    }
}
