package com.neusoft.controller;

import com.neusoft.entity.LoginDataTrans;
import com.neusoft.entity.people.Administrator;
import com.neusoft.entity.people.Doctor;
import com.neusoft.entityDAO.impl.DoctorDAOImpl;
import com.neusoft.factory.JavaFxTableCellFactory;
import com.neusoft.main.EvaluateSurfaceMain;
import com.neusoft.main.TemplateArrangeMain;
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

public class DoctorSurfaceController implements Initializable {
    public TableView<Doctor> InfoTable;
    public TableColumn selectColumn;
    public TableColumn adminUserNameColumn;
    public TableColumn adminNameColumn;
    public TableColumn DateColumn;
    public TableColumn telephoneColumn;
    public TableColumn PasswordColumn;
    public TableColumn sexColumn;
    public TableColumn typeOfPeople;
    public TableColumn Dateofborn;
    public TableColumn expertise;

    public TextField NameToSeek;
    public TextField LoginNameAdd;
    public TextField NameAdd;
    public TextField ageAdd;
    public TextField idAdd;
    public TextField PhoneAdd;
    public TextField passwordAdd;
    public TextField sexAdd;
    public TextField expertiseAdd;
    public TextField borndateAdd;
    public Button exitButton;
    public Button evaluateButton;
    public Button TemplateArrange;
    public Label WelcomeLabel;


    DoctorDAOImpl doctorDAO = DoctorDAOImpl.getDoctorDAO();
    EvaluateSurfaceMain evaluateSurfaceMain = new EvaluateSurfaceMain();
    LoginDataTrans loginDataTrans = LoginDataTrans.getLoginDataTrans();

    private ObservableList<Doctor> data = FXCollections.observableArrayList();

    public ObservableList<Doctor> getData()throws IOException{
        ObservableList<Doctor> doctorlist;
        doctorlist = FXCollections.observableArrayList(doctorDAO.findAllDoctor());
        return doctorlist;
    }

    public void ShowTable(ObservableList<Doctor> data){
        InfoTable.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WelcomeLabel.setText("欢迎：" + loginDataTrans.getLoginName());
        try {
            //下面的代码用来设置表格的宽度
            double width = InfoTable.getPrefWidth() / (InfoTable.getColumns().size() - 1) + 50;
            selectColumn.setPrefWidth(30);
            adminUserNameColumn.setPrefWidth(width);
            adminNameColumn.setPrefWidth(width);
            DateColumn.setPrefWidth(width);
            typeOfPeople.setPrefWidth(width);
            telephoneColumn.setPrefWidth(width);
            PasswordColumn.setPrefWidth(width);
            sexColumn.setPrefWidth(width);
            typeOfPeople.setPrefWidth(width);
            Dateofborn.setPrefWidth(width);
            expertise.setPrefWidth(width);
            //读取数据
            data = getData();
            selectColumn.setCellValueFactory(new PropertyValueFactory<>("Select"));
            adminUserNameColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("loginName"));
            adminNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
            DateColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            typeOfPeople.setCellValueFactory(new PropertyValueFactory<>("id"));
            telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
            PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            Dateofborn.setCellValueFactory(new PropertyValueFactory<>("bornDate"));
            expertise.setCellValueFactory(new PropertyValueFactory<>("expertise"));
            //多选框初始化
            selectColumn.setCellFactory(
                    JavaFxTableCellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                        @Override
                        public ObservableValue<Boolean> call(Integer index) {
                            final Doctor doctor = InfoTable.getItems().get(index);
                            ObservableValue<Boolean> ret =
                                    new SimpleBooleanProperty(doctor, "Select", doctor.getSelect());
                            ret.addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    doctor.setSelect(newValue);
                                }
                            });
                            return ret;
                        }
                    })
            );
            ShowTable(data);
        }catch (IOException e){

        }
    }

    public void SearchByName() throws IOException {
        String name = NameToSeek.getText();
        Doctor doctor_find = null;
        doctor_find = doctorDAO.findDoctorByName(name);
        if (doctor_find != null){
            data.clear();
            data.add(doctor_find);
        }
        else if(name.equals("")){
            data.clear();
            data.addAll(doctorDAO.findAllDoctor());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setResizable(false);
            alert.setHeaderText("提示");
            alert.setContentText("没有此名字的医生！");
            alert.show();
        }
    }

    public void click_add(ActionEvent actionEvent) {
        if (idAdd.getText() != null && NameAdd.getText() != null && LoginNameAdd.getText() != null
                && ageAdd.getText() != null && PhoneAdd.getText() != null && passwordAdd != null && sexAdd.getText() != null
                && borndateAdd.getText() != null && expertiseAdd.getText() != null)
        {
            try {
                Doctor doctor = new Doctor(NameAdd.getText(), Integer.valueOf(ageAdd.getText()),
                        sexAdd.getText(), idAdd.getId(), LoginNameAdd.getText(), passwordAdd.getText(), PhoneAdd.getText(),
                        borndateAdd.getText(),expertiseAdd.getText());
                data.add(doctor);
                doctorDAO.addDoctor(doctor);
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
    }

    public void click_delete(ActionEvent actionEvent) throws IOException{
        int size = data.size();
        for (int i = size - 1; i >= 0; i--){
            Doctor doctor =data.get(i);
            if (doctor.getSelect()){
                doctorDAO.deleteDoctor(doctor);
                data.remove(doctor);
            }
        }
    }

    public void click_modify(ActionEvent actionEvent) throws IOException {
        boolean flag = false;
        Doctor doctorUpdate = new Doctor();
        if (idAdd.getText() != null && NameAdd.getText() != null && LoginNameAdd.getText() != null
                && ageAdd.getText() != null && PhoneAdd.getText() != null && passwordAdd != null && sexAdd.getText() != null
                && borndateAdd.getText() != null && expertiseAdd.getText() != null)
        {
            for (Doctor doctor : doctorDAO.findAllDoctor()){
                if (doctor.getId().equals(idAdd.getText())){
                    flag = true;
                    doctorUpdate.setId(idAdd.getText());
                    doctorUpdate.setLoginName(LoginNameAdd.getText());
                    doctorUpdate.setName(NameAdd.getText());
                    doctorUpdate.setPassword(passwordAdd.getText());
                    doctorUpdate.setTelephoneNumber(PhoneAdd.getText());
                    doctorUpdate.setAge(Integer.valueOf(ageAdd.getText()));
                    doctorUpdate.setSex(sexAdd.getText());
                    doctorUpdate.setBornDate(borndateAdd.getText());
                    doctorUpdate.setExpertise(expertiseAdd.getText());

                    doctorDAO.updateDoctor(doctorUpdate);
                }
            }
        }
        if (flag){
            data.clear();
            data.addAll(doctorDAO.findAllDoctor());
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

    public void click_evaluate(ActionEvent actionEvent) throws IOException {//查看评估界面
        evaluateSurfaceMain.ShowWindow();
    }

    public void click_arrangeTemplate(ActionEvent actionEvent) throws IOException {
        TemplateArrangeMain templateArrangeMain = new TemplateArrangeMain();
        templateArrangeMain.ShowWindow();
    }
}
