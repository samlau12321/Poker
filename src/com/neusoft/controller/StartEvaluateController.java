package com.neusoft.controller;

import com.neusoft.entity.DataTrans;
import com.neusoft.entity.TestTemplate.Template;
import com.neusoft.entity.people.Patient;
import com.neusoft.entityDAO.impl.PatientDAOImpl;
import com.neusoft.entityDAO.impl.TemplateDAOImpl;
import com.neusoft.factory.JavaFxTableCellFactory;
import com.neusoft.main.MadeQuestionnaireMain;
import com.neusoft.main.QuestionViewMain;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartEvaluateController implements Initializable {
    public TableView<Template> InfoTable;
    public TableColumn<Template, Boolean> selectColumn;
    public TableColumn IDColumn;
    public TableColumn typeColumn;
//    public TextField nameField;
    public Button exitButton;

    TemplateDAOImpl templateDAO = TemplateDAOImpl.getTemplateDAO();
    PatientDAOImpl patientDAO = PatientDAOImpl.getPatientDAO();

    public static DataTrans dataTrans = DataTrans.getDataTrans();
    MadeQuestionnaireMain madeQuestionnaireMain = new MadeQuestionnaireMain();
    private ObservableList<Template> data = FXCollections.observableArrayList();

    public ObservableList<Template> getData() throws IOException {
        return FXCollections.observableArrayList(templateDAO.findAllTemplate());
    }

    public void ShowTable(ObservableList<Template> data){
        InfoTable.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //设置宽度
        double width = InfoTable.getPrefWidth() / (InfoTable.getColumns().size() - 1);
        selectColumn.setPrefWidth(60);
        IDColumn.setPrefWidth(width);
        typeColumn.setPrefWidth(width);

        try {
            data = getData();
            //初始化数据
            selectColumn.setCellValueFactory(new PropertyValueFactory<>("Select"));
            IDColumn.setCellValueFactory(new PropertyValueFactory<>("TemplateID"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("TempType"));

            selectColumn.setCellFactory(
                    JavaFxTableCellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                        @Override
                        public ObservableValue<Boolean> call(Integer index) {
                            final Template template1 = InfoTable.getItems().get(index);
                            ObservableValue<Boolean> ret =
                                    new SimpleBooleanProperty(template1, "Select", template1.getSelect());
                            ret.addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    template1.setSelect(newValue);
                                }
                            });
                            return ret;
                        }
                    })
            );
            InfoTable.setItems(getData());
            InfoTable.setEditable(true);
            ShowTable(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void click_back(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void click_startEvaluate(ActionEvent actionEvent) throws IOException {
        Patient patient = patientDAO.findPatientByName(dataTrans.getPaientname());
//        dataTrans.setPaientname(patient.getName());
        dataTrans.setDoctorName(patient.getDoctorName());
        int size = data.size();
        for (int i = size - 1; i >= 0; i--){
            Template template =data.get(i);
            if (template.getSelect()){
                dataTrans.setText(template.getTempType());
                dataTrans.setID(template.getTemplateID());
                dataTrans.setSex(patient.getSex());
                madeQuestionnaireMain.ShowWindow();
            }
        }
    }
}
