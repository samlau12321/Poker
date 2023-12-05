package com.neusoft.controller;

import com.neusoft.main.AdministratorSurfaceMain;
import com.neusoft.main.DoctorSurfaceMain;
import com.neusoft.main.PatientInfoSurfaceMain;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminSelectController implements Initializable {
    public RadioButton adminselect;
    public RadioButton doctorselect;
    public RadioButton patientselect;

    Stage stage = new Stage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void click_confirm() {
        if (adminselect.isSelected()){
            AdministratorSurfaceMain administratorSurfaceMain = new AdministratorSurfaceMain();
            administratorSurfaceMain.start(stage);
        }

        else if(doctorselect.isSelected()){
            DoctorSurfaceMain doctorSurfaceMain = new DoctorSurfaceMain();
            doctorSurfaceMain.ShowWindow();
        }

        else if (patientselect.isSelected()){
            PatientInfoSurfaceMain patientInfoSurfaceMain = new PatientInfoSurfaceMain();
            patientInfoSurfaceMain.ShowWindow();
        }

        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("提示");
            alert.setContentText("请选择要进入的页面！");
            alert.show();
        }
    }
}
