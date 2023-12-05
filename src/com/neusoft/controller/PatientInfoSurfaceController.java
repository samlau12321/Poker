package com.neusoft.controller;

import com.neusoft.entity.DataTrans;
import com.neusoft.entity.LoginDataTrans;
import com.neusoft.entity.people.Patient;
import com.neusoft.entityDAO.impl.DoctorDAOImpl;
import com.neusoft.entityDAO.impl.EvaluateResultDAOImpl;
import com.neusoft.entityDAO.impl.PatientDAOImpl;
import com.neusoft.factory.JavaFxTableCellFactory;
import com.neusoft.main.EvaluateSurfaceMain;
import com.neusoft.main.StartEvaluateMain;
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
import java.util.Date;
import java.util.ResourceBundle;

public class PatientInfoSurfaceController implements Initializable {
    LoginDataTrans loginDataTrans = LoginDataTrans.getLoginDataTrans();
    public TableView<Patient> InfoTable;
    public TableColumn selectColumn;
    public TableColumn adminNameColumn;
    public TableColumn DateColumn;
    public TableColumn telephoneColumn;
    public TableColumn sexColumn;
    public TableColumn typeOfPeople;
    public TableColumn EmergencyColumn;
    public TableColumn EmergencyPhoneColumn;

    public TextField LoginNameAdd;
    public TextField NameAdd;
    public TextField ageAdd;
    public TextField idAdd;
    public TextField PhoneAdd;
    public TextField passwordAdd;
    public TextField sexAdd;
    public TextField emergencyAdd;
    public TextField emergencyPhoneAdd;
    public TextField DoctorToGive;
    public TextField NameToSeek;
    public Button exitButton;
    public TableColumn DoctorNameColumn;
    public Label WelcomeLabel;

    Date date = new Date();
    public static DataTrans dataTrans = DataTrans.getDataTrans();
    public StartEvaluateMain startEvaluateMain = new StartEvaluateMain();

    PatientDAOImpl patientDAO = PatientDAOImpl.getPatientDAO();
    DoctorDAOImpl doctorDAO = DoctorDAOImpl.getDoctorDAO();
    EvaluateResultDAOImpl evaluateResultDAO = EvaluateResultDAOImpl.getEvaluateResultDAO();

    EvaluateSurfaceMain evaluateSurfaceMain = new EvaluateSurfaceMain();

    private ObservableList<Patient> data = FXCollections.observableArrayList();

    public ObservableList<Patient> getData() throws IOException {
        ObservableList<Patient> list = FXCollections.observableArrayList(patientDAO.findAllPatient());
        return list;
    }

    public void ShowTable(ObservableList<Patient> data){
        InfoTable.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WelcomeLabel.setText("欢迎：" + loginDataTrans.getLoginName());

        //下面的代码用来设置表格的宽度
        double width = InfoTable.getPrefWidth() / (InfoTable.getColumns().size() - 1) + 50;
        selectColumn.setPrefWidth(30);
        adminNameColumn.setPrefWidth(width);
        DateColumn.setPrefWidth(width);
        typeOfPeople.setPrefWidth(width);
        telephoneColumn.setPrefWidth(width);
        sexColumn.setPrefWidth(width);
        typeOfPeople.setPrefWidth(width);
        EmergencyColumn.setPrefWidth(width);
        EmergencyPhoneColumn.setPrefWidth(width);
        DoctorNameColumn.setPrefWidth(width);
        try {
            data = getData();
            selectColumn.setCellValueFactory(new PropertyValueFactory<>("Select"));
            adminNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
            DateColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            typeOfPeople.setCellValueFactory(new PropertyValueFactory<>("id"));
            telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
            EmergencyPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("emergencyPhoneNumber"));
            EmergencyColumn.setCellValueFactory(new PropertyValueFactory<>("emergencyContact"));
            DoctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
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

    public void click_add(ActionEvent actionEvent) {
        if (idAdd.getText() != null && NameAdd.getText() != null && LoginNameAdd.getText() != null
                && ageAdd.getText() != null && PhoneAdd.getText() != null && passwordAdd != null && sexAdd.getText() != null
                && emergencyAdd.getText() != null && emergencyPhoneAdd.getText() != null)
        {
            try {
                Patient patient = new Patient(NameAdd.getText(), Integer.valueOf(ageAdd.getText()),
                        sexAdd.getText(), idAdd.getId(), LoginNameAdd.getText(), passwordAdd.getText(), PhoneAdd.getText(),
                        emergencyAdd.getText(),emergencyPhoneAdd.getText());
                data.add(patient);
                patientDAO.addPatient(patient);
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("警告");
                alert.setContentText("请输入内容！");
                alert.show();
            }
        }
        idAdd.clear();
        NameAdd.clear();
        LoginNameAdd.clear();
        ageAdd.clear();
        PhoneAdd.clear();
        passwordAdd.clear();
        sexAdd.clear();
        emergencyAdd.clear();
        emergencyPhoneAdd.clear();
    }

    public void click_delete(ActionEvent actionEvent) throws IOException {
        int size = data.size();
        for (int i = size - 1; i >= 0; i--){
            Patient patient =data.get(i);
            if (patient.getSelect()){
                patientDAO.deletePatient(patient);
                data.remove(patient);
            }
        }
    }

    public void click_modify(ActionEvent actionEvent) throws IOException {
        boolean flag = false;
        Patient patientUpdate = new Patient();
        if (idAdd.getText() != null && NameAdd.getText() != null && LoginNameAdd.getText() != null
                && ageAdd.getText() != null && PhoneAdd.getText() != null && passwordAdd != null && sexAdd.getText() != null
                && emergencyAdd.getText() != null && emergencyPhoneAdd.getText() != null)
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
                    patientUpdate.setEmergencyContact(emergencyAdd.getText());
                    patientUpdate.setEmergencyPhoneNumber(emergencyPhoneAdd.getText());
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
        emergencyPhoneAdd.clear();
        emergencyAdd.clear();
    }

    public void click_exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void click_evaluate(ActionEvent actionEvent) throws IOException {
        int size = data.size();
        for (int i = size - 1; i >= 0; i--){
            Patient patient =data.get(i);
            if (patient.getSelect()){
//                EvaluateResult evaluateResult = new EvaluateResult();
//                evaluateResult.setPatientName(patient.getName());
//                evaluateResult.setDate(String.valueOf(date));
//                evaluateResult.setDoctorName(patient.getDoctorName());
//                evaluateResultDAO.addEvaluateResult(evaluateResult);
//                dataTrans.setText(data.toString());
                dataTrans.setPaientname(patient.getName());
                startEvaluateMain.ShowWindow();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("警告");
                alert.setContentText("请选择病人！");
            }
        }

    }

    public void click_arrangeDoctor() throws IOException {
        int size = data.size();
        for (int i = size - 1; i >= 0; i--){
            Patient patient =data.get(i);
            if (patient.getSelect()){
                if (doctorDAO.findDoctorByName(DoctorToGive.getText()) == null){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("警告");
                    alert.setContentText("没有此医生！");
                    alert.show();
                }
                else{
                    patient.setDoctor(doctorDAO.findDoctorByName(DoctorToGive.getText()));
                    patient.setDoctorName(doctorDAO.findDoctorByName(DoctorToGive.getText()).getName());
                    patientDAO.updatePatient(patient);
                }
            }
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

    public void click_viewEvaluate(ActionEvent actionEvent) throws IOException {
        evaluateSurfaceMain.ShowWindow();
    }
}
