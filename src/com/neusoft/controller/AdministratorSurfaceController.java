package com.neusoft.controller;

import com.neusoft.entity.LoginDataTrans;
import com.neusoft.entity.people.Administrator;
import com.neusoft.entity.people.Doctor;
import com.neusoft.entity.people.Patient;
import com.neusoft.entityDAO.impl.AdministratorDAOImpl;
import com.neusoft.factory.JavaFxTableCellFactory;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import javax.print.Doc;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdministratorSurfaceController implements Initializable {
    public TextField LoginNameAdd;
    public TextField NameAdd;
    public TextField ageAdd;
    public TextField idAdd;
    public TextField PhoneAdd;
    public TextField passwordAdd;
    public TextField sexAdd;
    public TextField NameToSeek;
    public TableColumn sexColumn;
    public Label WelcomeLabel;

    AdministratorDAOImpl administratorDAO = AdministratorDAOImpl.getAdministratorDAO();
    LoginDataTrans loginDataTrans = LoginDataTrans.getLoginDataTrans();

    @FXML
    public TableView<Administrator> InfoTable;
    @FXML
    public TableColumn<Administrator, Boolean> selectColumn;
    @FXML
    public TableColumn adminUserNameColumn;
    @FXML
    public TableColumn adminNameColumn;
    @FXML
    public TableColumn DateColumn;
    @FXML
    public TableColumn typeOfPeople;
    @FXML
    public TableColumn telephoneColumn;
    @FXML
    public TableColumn PasswordColumn;
    @FXML
    public Button exitButton;
    private ObservableList<Administrator> data = FXCollections.observableArrayList();
    //创建一个ObservableList用来准备读取数据
    public ObservableList<Administrator> getData() throws IOException {
        ObservableList<Administrator> adminList;
        adminList = FXCollections.observableArrayList(administratorDAO.findAllAdministrator());
        return adminList;
    }

    public void ShowTable(ObservableList<Administrator> adminList){
        InfoTable.setItems(adminList);
    }

    public void SearchByName() throws IOException {
        String name = NameToSeek.getText();
        Administrator admin_find = null;
            admin_find = administratorDAO.findAdminByName(name);
            if (admin_find != null){
                data.clear();
                data.add(admin_find);
            }
            else if(name.equals("")){
                data.clear();
                data.addAll(administratorDAO.findAllAdministrator());
        }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setResizable(false);
                alert.setHeaderText("提示");
                alert.setContentText("没有此名字的员工！");
                alert.show();
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WelcomeLabel.setText("欢迎：" + loginDataTrans.getLoginName());

        try {
            //下面的代码用来设置表格的宽度
            double width = InfoTable.getPrefWidth() / (InfoTable.getColumns().size()-1) + 50;
            selectColumn.setPrefWidth(30);
            adminUserNameColumn.setPrefWidth(width);
            adminNameColumn.setPrefWidth(width);
            DateColumn.setPrefWidth(width);
            typeOfPeople.setPrefWidth(width);
            telephoneColumn.setPrefWidth(width);
            PasswordColumn.setPrefWidth(width);
            sexColumn.setPrefWidth(width);

            data = getData();
            //下面是将数据读入表格中
            selectColumn.setCellValueFactory(new PropertyValueFactory<>("Select"));
            adminUserNameColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("loginName"));
            adminNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
            DateColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            typeOfPeople.setCellValueFactory(new PropertyValueFactory<>("id"));
            telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
            PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            //多选框初始化
            selectColumn.setCellFactory(
                    JavaFxTableCellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                        @Override
                        public ObservableValue<Boolean> call(Integer index) {
                            final Administrator administrator = InfoTable.getItems().get(index);
                            ObservableValue<Boolean> ret =
                                    new SimpleBooleanProperty(administrator, "Select", administrator.getSelect());
                                ret.addListener(new ChangeListener<Boolean>() {
                                    @Override
                                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                        administrator.setSelect(newValue);
                                    }
                                });
                                return ret;
                        }
                    })
                );
                //设置可编辑数据
                InfoTable.setItems(getData());
                InfoTable.setEditable(true);
                adminUserNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                adminNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                PasswordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                sexColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                DateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Number>() {
                    @Override
                    public String toString(Number object) {
                        return String.valueOf(object.intValue());
                    }

                    @Override
                    public Number fromString(String string) {
                        return Integer.valueOf(string);
                    }
                }));
                typeOfPeople.setCellFactory(TextFieldTableCell.forTableColumn());
                telephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            //展示数据
                ShowTable(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void click_exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void click_add(ActionEvent actionEvent) throws Exception {
        if (idAdd.getText() != null && NameAdd.getText() != null && LoginNameAdd.getText() != null
            && ageAdd.getText() != null && PhoneAdd.getText() != null && passwordAdd != null && sexAdd.getText() != null)
        {
            try {
                Administrator administrator = new Administrator(NameAdd.getText(), Integer.valueOf(ageAdd.getText()),
                        sexAdd.getText(), idAdd.getText(), LoginNameAdd.getText(), passwordAdd.getText(), PhoneAdd.getText());
                data.add(administrator);
                administratorDAO.addAdministrator(administrator);
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

    public void click_delete(ActionEvent actionEvent) throws IOException {
        int size = data.size();
        for (int i = size - 1; i >= 0; i--){
            Administrator admin =data.get(i);
            if (admin.getSelect()){
                administratorDAO.deleteAdministrator(admin);
                data.remove(admin);
            }
        }
    }

    public void click_modify(ActionEvent actionEvent) throws IOException {
        boolean flag = false;
        Administrator administratorUpdate = new Administrator();
        if (idAdd.getText() != null && NameAdd.getText() != null && LoginNameAdd.getText() != null
                && ageAdd.getText() != null && PhoneAdd.getText() != null && passwordAdd != null && sexAdd.getText() != null){
            for (Administrator admin : administratorDAO.findAllAdministrator()){
                if (admin.getId().equals(idAdd.getText())){
                    flag = true;
                    administratorUpdate.setId(idAdd.getText());
                    administratorUpdate.setLoginName(LoginNameAdd.getText());
                    administratorUpdate.setName(NameAdd.getText());
                    administratorUpdate.setPassword(passwordAdd.getText());
                    administratorUpdate.setTelephoneNumber(PhoneAdd.getText());
                    administratorUpdate.setAge(Integer.valueOf(ageAdd.getText()));
                    administratorUpdate.setSex(sexAdd.getText());

                    administratorDAO.updateAdministrator(administratorUpdate);
                }
            }
        }
        if (flag){
            data.clear();
            data.addAll(administratorDAO.findAllAdministrator());
        }
        idAdd.clear();
        NameAdd.clear();
        LoginNameAdd.clear();
        ageAdd.clear();
        PhoneAdd.clear();
        passwordAdd.clear();
        sexAdd.clear();
    }

}
