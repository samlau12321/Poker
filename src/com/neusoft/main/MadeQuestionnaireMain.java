package com.neusoft.main;

import com.neusoft.entity.DataTrans;
import com.neusoft.entity.TestTemplate.EvaluateResult;
import com.neusoft.entity.TestTemplate.Questions;
import com.neusoft.entity.TestTemplate.Template;
import com.neusoft.entityDAO.impl.EvaluateResultDAOImpl;
import com.neusoft.entityDAO.impl.TemplateDAOImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MadeQuestionnaireMain extends Application {
    public MadeQuestionnaireMain(){

    }
    Date date = new Date();
    LocalDate localDate = LocalDate.now();

    int chooseA = 0;
    int chooseB = 0;
    int chooseC = 0;

    public static DataTrans dataTrans = DataTrans.getDataTrans();
    Stage stage = new Stage();
    Button confirm = new Button("结束答题");
    HBox buttonBox = new HBox();
    TemplateDAOImpl templateDAO = TemplateDAOImpl.getTemplateDAO();
    List<Questions> questionsList = new ArrayList<>();
    VBox totalVbox = new VBox();
    EvaluateResultDAOImpl evaluateResultDAO = EvaluateResultDAOImpl.getEvaluateResultDAO();

    List<RadioButton> radioButtonA = new ArrayList<>();
    List<RadioButton> radioButtonB = new ArrayList<>();
    List<RadioButton> radioButtonC = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Template template = templateDAO.findTemplateByID(dataTrans.getID());
        if (template != null){
            questionsList = template.getQuestionList();
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color:#72777b");


        int spacing = 50;
        //创建问题列表
        if (questionsList != null){
            for (Questions question : questionsList){
                ToggleGroup tg = new ToggleGroup();
                RadioButton choiceA = new RadioButton();
                RadioButton choiceB = new RadioButton();
                RadioButton choiceC = new RadioButton();

                choiceA.setToggleGroup(tg);
                choiceB.setToggleGroup(tg);
                choiceC.setToggleGroup(tg);

                Label description = new Label();

                VBox vBox = new VBox();
                vBox.setPrefHeight(200);
                vBox.setPrefWidth(200*1.68);
                vBox.getChildren().addAll(description, choiceA, choiceB, choiceC);

                vBox.setSpacing(20);
                vBox.setLayoutX(50);
                vBox.setLayoutY(spacing*4);

                description.setText(question.getDescription());
                choiceA.setText(question.getChoiceA());
                choiceB.setText(question.getChoiceB());
                choiceC.setText(question.getChoiceC());

                radioButtonA.add(choiceA);
                radioButtonB.add(choiceB);
                radioButtonC.add(choiceC);

                totalVbox.getChildren().add(vBox);
                spacing += 50;
            }
        }

        confirm.setPrefWidth(100);
        confirm.setPrefHeight(30);

        confirm.setOnAction(actionEvent -> {
            try {
                confirmButtonEvent(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttonBox.getChildren().add(confirm);
        totalVbox.getChildren().add(buttonBox);
        confirm.setAlignment(Pos.BASELINE_CENTER);
        scrollPane.setContent(totalVbox);
        scrollPane.setPrefWidth(800);
        scrollPane.setPrefHeight(800);
        Scene scene = new Scene(scrollPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("评估问卷");
        primaryStage.getIcons().add(new Image("file:src/picture/company1.jpg"));
        primaryStage.show();
    }

    private void confirmButtonEvent(ActionEvent actionEvent) throws IOException {
        String good = "非常好";
        String average = "一般";
        String bad = "差";

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("确认");
        alert.setContentText("确定结束答题吗？");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)){
            int score = 0;
            for (RadioButton radioButton : radioButtonA){
                if (radioButton.isSelected()){
                    chooseA ++;
                }
            }

            for (RadioButton radioButton : radioButtonB){
                if (radioButton.isSelected()){
                    chooseB++;
                }
            }

            for (RadioButton radioButton : radioButtonC){
                if (radioButton.isSelected()){
                    chooseC++;
                }
            }

            score = chooseA * 5 + chooseB * 3 + chooseC * 1;
            dataTrans.setScore(score);
            dataTrans.setDate(localDate.toString());
            EvaluateResult evaluateResult = new EvaluateResult();
            evaluateResult.setPatientName(dataTrans.getPaientname());
            evaluateResult.setDoctorName(dataTrans.getDoctorName());
            evaluateResult.setDate(dataTrans.getDate());
            evaluateResult.setScore(dataTrans.getScore());
            evaluateResult.setTemplateName(dataTrans.getID());
            evaluateResult.setTemplateType(dataTrans.getText());
            evaluateResult.setSex(dataTrans.getSex());

            if (score <= radioButtonA.size() * 5 && score > radioButtonA.size() *5 *0.8){
                evaluateResult.setJudgement(good);
            }
            else if (score <= radioButtonA.size() *5 *0.8 && score >= radioButtonA.size() * 5* 0.6){
                evaluateResult.setJudgement(average);
            }

            else {
                evaluateResult.setJudgement(bad);
            }

            evaluateResultDAO.addEvaluateResult(evaluateResult);
            stage = (Stage) confirm.getScene().getWindow();
            stage.close();
        }
    }

    public void ShowWindow() throws IOException {
        start(stage);
    }


}
