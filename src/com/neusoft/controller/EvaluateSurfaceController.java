package com.neusoft.controller;

import com.neusoft.entity.TestTemplate.EvaluateResult;
import com.neusoft.entityDAO.impl.EvaluateResultDAOImpl;
import com.neusoft.main.TemplateArrangeMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EvaluateSurfaceController implements Initializable {
    public TableView<EvaluateResult> InfoTable;
    public TableColumn nameColumn;
    public TableColumn sexColumn;
    public TableColumn templateNameColumn;
    public TableColumn templateTypeName;
    public TableColumn timeColumn;
    public TableColumn evaluatorColumn;
    public TableColumn judgementColumn;
    public TableColumn scoreColumn;

    public TextField nameAdd;
    public Button exitButton;

    EvaluateResultDAOImpl evaluateResultDAO = EvaluateResultDAOImpl.getEvaluateResultDAO();

    TemplateArrangeMain templateArrangeMain = new TemplateArrangeMain();

    private ObservableList<EvaluateResult> data = FXCollections.observableArrayList();

    public ObservableList<EvaluateResult> getData() throws IOException {
        ObservableList<EvaluateResult> list = FXCollections.observableArrayList(evaluateResultDAO.findAllEvaluateResult());
        return list;
    }

    public void ShowTable(ObservableList<EvaluateResult> data){
        InfoTable.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
        double width = InfoTable.getPrefWidth() / (InfoTable.getColumns().size() - 1) + 50;
        nameColumn.setPrefWidth(width);
        sexColumn.setPrefWidth(width);
        templateNameColumn.setPrefWidth(width);
        templateTypeName.setPrefWidth(width);
        timeColumn.setPrefWidth(width);
        evaluatorColumn.setPrefWidth(width);
        judgementColumn.setPrefWidth(width);
        scoreColumn.setPrefWidth(width);

        try {
            data = getData();
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
            sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
            templateNameColumn.setCellValueFactory(new PropertyValueFactory<>("templateName"));
            templateTypeName.setCellValueFactory(new PropertyValueFactory<>("templateType"));
            evaluatorColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
            judgementColumn.setCellValueFactory(new PropertyValueFactory<>("judgement"));
            scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
            timeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            ShowTable(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click_start(ActionEvent actionEvent) throws IOException {
        templateArrangeMain.ShowWindow();
    }

    public void click_back(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
