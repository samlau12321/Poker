package com.neusoft.controller;

import com.neusoft.entity.people.Doctor;
import com.neusoft.entity.people.Patient;
import com.neusoft.service.Impl.AdministratorServiceImpl;
import com.neusoft.service.Impl.DoctorServiceImpl;
import com.neusoft.service.Impl.PatientServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class RegisterController extends Application {
    AdministratorServiceImpl administratorService = AdministratorServiceImpl.getAdministratorService();
    DoctorServiceImpl doctorService = DoctorServiceImpl.getDoctorService();
    PatientServiceImpl patientService = PatientServiceImpl.getPatientService();

    public ToggleGroup typeOfPeople;
    public RadioButton isDoctor;
    public RadioButton isPatient;
    public RadioButton isAdministrator;
    public RadioButton Male;
    public RadioButton Female;
    public PasswordField password;
    public TextField Name;
    public TextField telephoneNumber;
    public TextField LoginName;
    public TextField ID;
    public TextField EmergencyName;
    public TextField EmergencyPhone;
    public DatePicker BornDate;
    public CheckBox read;
    public ComboBox expertise;
    public Button resign;

    Stage stage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/neusoft/frames/Register.fxml"));
        primaryStage.setTitle("东软颐养中心管理系统：注册界面");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("file:src/picture/company1.jpg"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void showWindow() throws Exception{
        start(stage);
    }

    public boolean click_isDoctor() {
        return isDoctor.isSelected();
    }

    public boolean click_isPatient() {
        return isPatient.isSelected();
    }

    public boolean click_isAdministrator() {
        return isAdministrator.isSelected();
    }

    public String input_Name() {
        String name;
        name = Name.getText();
        return name;
    }

    public boolean isMale() {
        return Male.isSelected();
    }

    public boolean isFemale() {
        return Female.isSelected();
    }

    public String create_Borndate() {
//        System.out.println(BornDate.getChronology().dateNow());//展示现在日期
//        System.out.println(BornDate.getValue());
        String bornDate;
        bornDate = String.valueOf(BornDate.getValue());
        return bornDate;
    }

    public String create_LoginName() {
        String loginName;
        loginName = LoginName.getText();
        return loginName;
    }

    public String create_password() {
        String Password;
        Password = password.getText();
        return Password;
    }

    public String create_telephoneNumber() {
        String personalPhoneNumber;
        personalPhoneNumber = telephoneNumber.getText();
        return personalPhoneNumber;
    }

    public String create_ID() {
        return ID.getText();
    }

    public boolean isRead() {
        return read.isSelected();
    }

    public String create_EmergencyName() {
        return EmergencyName.getText();
    }

    public String create_EmergencyPhone() {
        return EmergencyPhone.getText();
    }

    public String create_expertise() {
        return expertise.getValue().toString();
    }

    public void click_To_resign() throws IOException
    {
        if (isRead()){
            Stage stage1 = (Stage) resign.getScene().getWindow();
            int year = LocalDate.now().getYear();
            int age = 0;
            try {
                age = year - BornDate.getValue().getYear();
            }catch (NullPointerException e){

            }

            if (click_isAdministrator()){
                String sex = "";

                if (isMale()){
                    sex = "Male";
                }
                else if(isFemale()){
                    sex = "Female";
                }

                boolean isResign = false;
                isResign = administratorService.resignUser(input_Name(), age, sex, create_ID(), create_LoginName(),
                        create_password(), create_telephoneNumber());
                Alert alert;
                if (isResign){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setResizable(false);
                    alert.setHeaderText("注册提示");
                    alert.setContentText("注册成功！");
                    alert.showAndWait();
                    stage1.close();
                }

                else {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setResizable(false);
                    alert.setHeaderText("注册提示");
                    alert.setContentText("注册失败！");
                    alert.show();
                }

            }

            else if (click_isDoctor())
            {
                String sex = "";
                if (isMale()){
                    sex = "Male";
                }
                else if(isFemale()){
                    sex = "Female";
                }

                boolean isResign = false;

                Doctor doctor = new Doctor(input_Name(), age, sex,create_ID(),create_LoginName(),create_password(),create_telephoneNumber()
                ,create_Borndate(),create_expertise());

                isResign = doctorService.resignDoctor(doctor);

                Alert alert;
                if (isResign){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setResizable(false);
                    alert.setHeaderText("注册提示");
                    alert.setContentText("注册成功！");
                    alert.showAndWait();
                    stage1.close();
                }

                else {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setResizable(false);
                    alert.setHeaderText("注册提示");
                    alert.setContentText("注册失败！");
                    alert.show();
                }

            }

            else if ((click_isPatient())){
                String sex = "";
                if (isMale()){
                    sex = "Male";
                }
                else if (isFemale()){
                    sex = "Female";
                }
                boolean isResign = false;
                Patient patient = new Patient(input_Name(), age, sex, create_ID(), create_LoginName(),create_password(), create_telephoneNumber()
                , create_EmergencyName(), create_telephoneNumber());


                isResign = patientService.resignPatient(patient);

                Alert alert;
                if (isResign){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setResizable(false);
                    alert.setHeaderText("注册提示");
                    alert.setContentText("注册成功！");
                    alert.showAndWait();
                    stage1.close();
                }

                else {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setResizable(false);
                    alert.setHeaderText("注册提示");
                    alert.setContentText("注册失败！");
                    alert.show();
                }
            }

            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setResizable(false);
                alert.setHeaderText("注册人员类别警告");
                alert.setContentText("请选择注册人员类别！");
                alert.show();
            }
        }

        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("警告信息");
            alert.setContentText("请阅读详细信息！");
            alert.show();
        }
    }


}
