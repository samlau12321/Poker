package com.neusoft.controller;

import com.neusoft.entity.LoginDataTrans;
import com.neusoft.entity.people.Patient;
import com.neusoft.entityDAO.impl.PatientDAOImpl;
import com.neusoft.factory.JavaFxTableCellFactory;
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
import java.util.ResourceBundle;

public class PatientLoginSurfaceController implements Initializable {
    public TableView<Patient> InfoTable;
    public TextField NameToSeek;
    public Button SearchButton;
    public TableColumn selectColumn;
    public TableColumn adminUserNameColumn;
    public TableColumn adminNameColumn;
    public TableColumn DateColumn;
    public TableColumn sexColumn;
    public TableColumn typeOfPeople;
    public TableColumn telephoneColumn;
    public TextField NameAdd;
    public TextField idAdd;
    public TextField sexAdd;
    public TextField LoginNameAdd;
    public TextField ageAdd;
    public TextField passwordAdd;
    public TextField PhoneAdd;
    public Button exitButton;
    public Label WelcomeLabel;

    PatientDAOImpl patientDAO = PatientDAOImpl.getPatientDAO();
    LoginDataTrans loginDataTrans = LoginDataTrans.getLoginDataTrans();

    private ObservableList<Patient> data = FXCollections.observableArrayList();

    public ObservableList<Patient> getData() throws IOException {
        ObservableList<Patient> list = FXCollections.observableArrayList(patientDAO.findAllPatient());
        return list;
    }

    private void ShowTable(ObservableList<Patient> data){
        InfoTable.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WelcomeLabel.setText("欢迎：" + loginDataTrans.getLoginName());
        double width = InfoTable.getPrefWidth() / (InfoTable.getColumns().size() - 1) + 50;
        selectColumn.setPrefWidth(30);
        adminUserNameColumn.setPrefWidth(width);
        adminNameColumn.setPrefWidth(width);
        DateColumn.setPrefWidth(width);
        sexColumn.setPrefWidth(width);
        telephoneColumn.setPrefWidth(width);
        sexColumn.setPrefWidth(width);
        typeOfPeople.setPrefWidth(width);


        try {
            data = getData();
            //初始化值
            selectColumn.setCellValueFactory(new PropertyValueFactory<>("Select"));
            adminUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("LoginName"));
            adminNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            DateColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
            typeOfPeople.setCellValueFactory(new PropertyValueFactory<>("id"));
            telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
            //初始化多选框
            selectColumn.setCellFactory(
                    JavaFxTableCellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                        @Override
                        public ObservableValue<Boolean> call(Integer index) {
                            final Patient patient = InfoTable.getItems().get(index);
                            ObservableValue<Boolean> ret =
                                    new SimpleBooleanProperty(patient, "Select", patient.getSelect());
                            ret.addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    patient.setSelect(newValue);
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

    public void SearchByName(ActionEvent actionEvent) throws IOException {
        String name = NameToSeek.getText();
        Patient patient_find = null;
        patient_find = patientDAO.findPatientByName(name);
        if (patient_find != null){
            data.clear();
            data.add(patient_find);
        }
        else if(name.equals("")){
            data.clear();
            data.addAll(patientDAO.findAllPatient());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setResizable(false);
            alert.setHeaderText("提示");
            alert.setContentText("没有此名字的病人！");
            alert.show();
        }
    }

    public void click_modify(ActionEvent actionEvent) throws IOException {
        boolean flag = false;
        Patient patientUpdate = new Patient();
        if (idAdd.getText() != null && NameAdd.getText() != null && LoginNameAdd.getText() != null
                && ageAdd.getText() != null && PhoneAdd.getText() != null && passwordAdd != null && sexAdd.getText() != null)
        {
            for (Patient patient : patientDAO.findAllPatient()){
                if (patient.getId().equals(idAdd.getText())){
                    flag = true;
                    patientUpdate.setId(idAdd.getText());
                    patientUpdate.setLoginName(LoginNameAdd.getText());
                    patientUpdate.setName(NameAdd.getText());
                    patientUpdate.setPassword(passwordAdd.getText());
                    patientUpdate.setTelephoneNumber(PhoneAdd.getText());
                    patientUpdate.setAge(Integer.valueOf(ageAdd.getText()));
                    patientUpdate.setSex(sexAdd.getText());
                    patientDAO.updatePatient(patientUpdate);
                }
            }
        }

        if (flag){
            data.clear();
            data.addAll(patientDAO.findAllPatient());
        }
        idAdd.clear();
        NameAdd.clear();
        LoginNameAdd.clear();
        ageAdd.clear();
        PhoneAdd.clear();
        passwordAdd.clear();
        sexAdd.clear();
    }

    public void click_exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
