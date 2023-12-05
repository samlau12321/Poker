package com.neusoft.controller;

import com.neusoft.entity.LoginDataTrans;
import com.neusoft.entityDAO.impl.AdministratorDAOImpl;
import com.neusoft.entityDAO.impl.DoctorDAOImpl;
import com.neusoft.entityDAO.impl.PatientDAOImpl;
import com.neusoft.main.AdminSelectMain;
import com.neusoft.main.PatientInfoSurfaceMain;
import com.neusoft.main.PatientLoginMain;
import com.neusoft.service.Impl.AdministratorServiceImpl;
import com.neusoft.service.Impl.DoctorServiceImpl;
import com.neusoft.service.Impl.PatientServiceImpl;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController extends Application {
    public ToggleGroup select;
//    public RadioButton Administrator;
//    public RadioButton Doctor;
//    public RadioButton Patient;
    public TextField UserName;
    public PasswordField Password;

    Stage stage = new Stage();

    AdministratorServiceImpl administratorService = AdministratorServiceImpl.getAdministratorService();
    DoctorServiceImpl doctorService = DoctorServiceImpl.getDoctorService();
    PatientServiceImpl patientService = PatientServiceImpl.getPatientService();

    AdminSelectMain adminSelectMain = new AdminSelectMain();
    PatientInfoSurfaceMain patientInfoSurfaceMain = new PatientInfoSurfaceMain();
    PatientLoginMain patientLoginMain = new PatientLoginMain();

    AdministratorDAOImpl administratorDAO = AdministratorDAOImpl.getAdministratorDAO();
    PatientDAOImpl patientDAO = PatientDAOImpl.getPatientDAO();
    DoctorDAOImpl doctorDAO = DoctorDAOImpl.getDoctorDAO();

    LoginDataTrans loginDataTrans = LoginDataTrans.getLoginDataTrans();

    public static void main(String[] args) {
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
    }

    public void showWindow() throws Exception {
        start(stage);
    }

    public void clickExit_Event() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("消息");
        alert.setContentText("程序即将退出！");
        alert.showAndWait();
        System.exit(0);
    }

    public void clickToResign() {
        RegisterController registerController = new RegisterController();
        try {
            registerController.showWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click_Signin() throws IOException {
        boolean Valid = false;
        if (administratorDAO.findAdminByLoginName(UserName.getText()) != null) {
            Valid = administratorService.validate(UserName.getText(), Password.getText(), "Administrators.json");

            if (Valid) {
                Alert alert;
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("登录提示");
                alert.setContentText("登录成功！");
                alert.showAndWait();
                loginDataTrans.setLoginName(UserName.getText());
                adminSelectMain.ShowWindow();
            } else {
                Alert alert;
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("登录错误");
                alert.setContentText("登录失败！请检查输入用户名与密码！");
                alert.show();
            }
        } else if (patientDAO.findPatientByLoginName(UserName.getText()) != null) {
            Valid = patientService.validate(UserName.getText(), Password.getText(), "Patients.json");
            if (Valid) {
                Alert alert;
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("登录提示");
                alert.setContentText("登录成功！");
                alert.showAndWait();
                loginDataTrans.setLoginName(UserName.getText());
                patientLoginMain.ShowWindow();
            } else {
                Alert alert;
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("登录错误");
                alert.setContentText("登录失败！请检查输入用户名与密码！");
                alert.show();
            }
        } else if (doctorDAO.findDoctorByLoginName(UserName.getText()) != null) {
            Valid = doctorService.validate(UserName.getText(), Password.getText(), "Doctors.json");
            if (Valid) {
                Alert alert;
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("登录提示");
                alert.setContentText("登录成功！");
                alert.showAndWait();
                loginDataTrans.setLoginName(UserName.getText());
                patientInfoSurfaceMain.ShowWindow();
            } else {
                Alert alert;
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("登录错误");
                alert.setContentText("登录失败！请检查输入用户名与密码！");
                alert.show();
            }
        } else {
            Alert alert;
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("登录错误");
            alert.setContentText("登录失败！请检查此用户是否存在！");
            alert.show();
        }
        Password.clear();
    }
}
