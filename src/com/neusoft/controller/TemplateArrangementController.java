package com.neusoft.controller;

import com.neusoft.entity.TestTemplate.Questions;
import com.neusoft.entity.TestTemplate.Template;
import com.neusoft.entity.people.Doctor;
import com.neusoft.entityDAO.impl.TemplateDAOImpl;
import com.neusoft.factory.JavaFxTableCellFactory;
import com.neusoft.main.QuestionViewMain;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TemplateArrangementController implements Initializable {


    public TableView<Template> InfoTable;
    public TableColumn selectColumn;
    public TableColumn TemplateIDColumn;
    public TableColumn TemplateTypeColumn;
    public TextField IDAdd;
    public TextField typeAdd;
    public Button exitButton;

    TemplateDAOImpl templateDAO = TemplateDAOImpl.getTemplateDAO();
    private ObservableList<Template> data = FXCollections.observableArrayList();

    public ObservableList<Template> getData() throws IOException {
        ObservableList<Template> list = FXCollections.observableArrayList(templateDAO.findAllTemplate());
        return list;
    }

    public void ShowTable(ObservableList<Template> data){
        InfoTable.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载数据
        double width = InfoTable.getPrefWidth() / (InfoTable.getColumns().size() - 1);
        selectColumn.setPrefWidth(30);
        TemplateIDColumn.setPrefWidth(width);
        TemplateTypeColumn.setPrefWidth(width);

        //
        try {
            data = getData();
            selectColumn.setCellValueFactory(new PropertyValueFactory<>("Select"));
            TemplateIDColumn.setCellValueFactory(new PropertyValueFactory<>("TemplateID"));
            TemplateTypeColumn.setCellValueFactory(new PropertyValueFactory<>("TempType"));

            selectColumn.setCellFactory(
                    JavaFxTableCellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                        @Override
                        public ObservableValue<Boolean> call(Integer index) {
                            final Template template = InfoTable.getItems().get(index);
                            ObservableValue<Boolean> ret =
                                    new SimpleBooleanProperty(template, "Select", template.getSelect());
                            ret.addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    template.setSelect(newValue);
                                }
                            });
                            return ret;
                        }
                    })
            );
            ShowTable(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void click_add(ActionEvent actionEvent) {
        if (IDAdd.getText() != null && typeAdd.getText() != null){
            try {
                Template template = new Template(IDAdd.getText(), typeAdd.getText());
                data.add(template);
                templateDAO.addTemplate(template);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("警告");
                alert.setContentText("请输入内容！");
                alert.show();
            }
        }

        IDAdd.clear();;
        typeAdd.clear();
    }

    public void click_delete(ActionEvent actionEvent) throws IOException {
        int size = data.size();
        for (int i = size - 1; i >= 0; i--){
            Template template =data.get(i);
            if (template.getSelect()){
                templateDAO.deleteTemplate(template);
                data.remove(template);
            }
        }
    }

    public void click_modify(ActionEvent actionEvent) throws IOException {
        boolean flag = false;
        Template templateUpdate = new Template();
        if (IDAdd.getText() != null && typeAdd.getText() != null){
            for (Template template : templateDAO.findAllTemplate()){
                if (template.getTemplateID().equals(IDAdd.getText())){
                    flag = true;
                    templateUpdate.setTemplateID(IDAdd.getText());
                    templateUpdate.setTemplateID(typeAdd.getText());

                    templateDAO.updateTemplate(template);
                }
            }
        }

        if (flag){
            data.clear();
            data.addAll(templateDAO.findAllTemplate());
        }

        IDAdd.clear();
        typeAdd.clear();
    }

    public void click_view(ActionEvent actionEvent) {
        int size = data.size();
        for (int i = size - 1; i >= 0; i--){
            Template template =data.get(i);
            if (template.getSelect()){
                ScrollPane anchorPane = new ScrollPane();
                anchorPane.setStyle("-fx-background-color:#72777b");
                List<Questions> questionsList = new ArrayList<>();
                questionsList = template.getQuestionList();
                Stage primaryStage = new Stage();
                VBox totalVbox = new VBox();
                int spacing = 50;
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
                    totalVbox.getChildren().add(vBox);
                    spacing += 50;
                }
//                Button confirm = new Button("确认");
//                confirm.setAlignment(Pos.BASELINE_CENTER);
//                totalVbox.getChildren().add(confirm);
                anchorPane.setContent(totalVbox);
                Scene scene = new Scene(anchorPane);
                primaryStage.setScene(scene);
                primaryStage.setTitle("评估问卷");
                primaryStage.setWidth(800);
                primaryStage.setHeight(800);
                primaryStage.show();
            }
        }
    }

    public void click_exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void click_arrangeQuestion(ActionEvent actionEvent) throws IOException {
        QuestionViewMain questionViewMain = new QuestionViewMain();
        Stage stage = new Stage();
        questionViewMain.start(stage);
    }

}
