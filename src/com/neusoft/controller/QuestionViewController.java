package com.neusoft.controller;

import com.neusoft.entity.TestTemplate.Questions;
import com.neusoft.entityDAO.impl.QuestionDAOImpl;
import com.neusoft.entityDAO.impl.TemplateDAOImpl;
import com.neusoft.factory.JavaFxTableCellFactory;
import com.neusoft.main.NewModelSurfaceMain;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class QuestionViewController implements Initializable {
    public static final String Column1MapKey = "A";
    public static final String Column2MapKey = "B";
    public static final String Column3MapKey = "C";
    public TableView<Questions> InfoTable;
    public TableColumn selectColumn;
    public TableColumn IDColumn;
    public TableColumn TypeColumn;

    public TableColumn<HashMap<String, String>,String >ChoiceAColumn;
    public TableColumn<HashMap<String, String>,String >ChoiceBColumn;
    public TableColumn<HashMap<String, String>,String >ChoiceCColumn;

    public Button exitButton;
    public TextField IDAdd;
    public TextField typeAdd;
    public TextField ChoiceAAdd;
    public TextField ChoiceBAdd;
    public TextField ChoiceCAdd;
    public TableColumn descriptionColumn;
    public TextField descriptionAdd;
    public TextField IDField;

    QuestionDAOImpl questionDAO = QuestionDAOImpl.getQuestionDAO();
    TemplateDAOImpl templateDAO = TemplateDAOImpl.getTemplateDAO();
    private ObservableList<Questions> data = FXCollections.observableArrayList();


    public ObservableList<Questions> getData() throws IOException {
        ObservableList<Questions> list = FXCollections.observableArrayList(questionDAO.findAllQuestions());
        return list;
    }

    public void ShowTable(ObservableList<Questions> data){
        InfoTable.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //下面的代码用来设置表格的宽度
        double width = InfoTable.getPrefWidth() / (InfoTable.getColumns().size() - 1);
        selectColumn.setPrefWidth(30);
        IDColumn.setPrefWidth(width);
        TypeColumn.setPrefWidth(width);
        descriptionColumn.setPrefWidth(width);
        ChoiceAColumn.setPrefWidth(width);
        ChoiceBColumn.setPrefWidth(width);
        ChoiceCColumn.setPrefWidth(width);

        //读取表格数据
        try {
            data = getData();
            selectColumn.setCellValueFactory(new PropertyValueFactory<>("Select"));
            IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            ChoiceAColumn.setCellValueFactory(new PropertyValueFactory<>("ChoiceA"));
            ChoiceBColumn.setCellValueFactory(new PropertyValueFactory<>("ChoiceB"));
            ChoiceCColumn.setCellValueFactory(new PropertyValueFactory<>("ChoiceC"));
            selectColumn.setCellFactory(
                    JavaFxTableCellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                        @Override
                        public ObservableValue<Boolean> call(Integer index) {
                            final Questions questions = InfoTable.getItems().get(index);
                            ObservableValue<Boolean> ret =
                                    new SimpleBooleanProperty(questions, "Select", questions.getSelect());
                            ret.addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    questions.setSelect(newValue);
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

    public void click_add(ActionEvent actionEvent) throws IOException {
        NewModelSurfaceMain newModelSurfaceMain = new NewModelSurfaceMain();
        newModelSurfaceMain.ShowWindow();
    }

    public void click_delete(ActionEvent actionEvent) throws IOException {
        int size = data.size();
        for (int i = size - 1; i >= 0; i--){
            Questions questions =data.get(i);
            if (questions.getSelect()){
                questionDAO.deleteQuestion(questions);
                data.remove(questions);
            }
        }
    }

    public void click_modify(ActionEvent actionEvent) throws IOException {
        boolean flag = false;
        Questions questionUpdate = new Questions();
        if (IDAdd.getText() != null && typeAdd.getText() != null && ChoiceAColumn.getText() != null
                && ChoiceBColumn.getText() != null && ChoiceCColumn.getText() != null){
            for (Questions question : questionDAO.findAllQuestions()){
                if (question.getID().equals(IDAdd.getText())){
                    flag = true;
                    questionUpdate.setID(IDAdd.getText());
                    questionUpdate.setType(typeAdd.getText());
                    questionUpdate.setChoiceA(ChoiceAAdd.getText());
                    questionUpdate.setChoiceB(ChoiceBAdd.getText());
                    questionUpdate.setChoiceC(ChoiceCAdd.getText());
                    questionUpdate.setDescription(descriptionAdd.getText());
                    questionDAO.updateQuestion(questionUpdate);
                }
            }
        }
        if (flag){
            data.clear();
            data.addAll(questionDAO.findAllQuestions());
        }
        IDAdd.clear();
        typeAdd.clear();
        descriptionAdd.clear();
        ChoiceAAdd.clear();
        ChoiceBAdd.clear();
        ChoiceCAdd.clear();
    }

    public void click_exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void click_flush(ActionEvent actionEvent) throws IOException {
        data = getData();
        ShowTable(data);
    }

    public void click_AddToTemplate(ActionEvent actionEvent) throws IOException {
        List<Questions> questionsList = new ArrayList<>();
        int size = data.size();
        for (int i = size - 1; i >= 0; i--){
            Questions questions =data.get(i);
            if (questions.getSelect()){
                questionsList.add(questions);
            }
        }
        boolean flag = templateDAO.addQuestions(questionsList, IDField.getText());
        if (flag){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("提示");
            alert.setContentText("问题添加成功！");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("提示");
            alert.setContentText("问题添加失败！");
            alert.show();
        }
    }
}
