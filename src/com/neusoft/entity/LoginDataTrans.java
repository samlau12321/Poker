package com.neusoft.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginDataTrans {
    public static LoginDataTrans loginDataTrans = null;

    public LoginDataTrans(){

    }

    public LoginDataTrans(StringProperty loginName) {
        LoginName = loginName;
    }

    public static LoginDataTrans getLoginDataTrans(){
        if (loginDataTrans == null){
            loginDataTrans = new LoginDataTrans();
        }

        return loginDataTrans;
    }

    private StringProperty LoginName = new SimpleStringProperty();

    public String getLoginName() {
        return LoginName.get();
    }

    public StringProperty loginNameProperty() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        this.LoginName.set(loginName);
    }


}
