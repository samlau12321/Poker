package com.neusoft.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DataTrans {
    private StringProperty text = new SimpleStringProperty();
    private StringProperty paientname = new SimpleStringProperty();
    private StringProperty ID = new SimpleStringProperty();
    private IntegerProperty score = new SimpleIntegerProperty();
    private StringProperty date = new SimpleStringProperty();
    private StringProperty DoctorName = new SimpleStringProperty();
    private StringProperty sex = new SimpleStringProperty();

    public String getSex() {
        return sex.get();
    }

    public StringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getDoctorName() {
        return DoctorName.get();
    }

    public StringProperty doctorNameProperty() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        this.DoctorName.set(doctorName);
    }

    private static DataTrans dataTrans = null;

    public static DataTrans getDataTrans(){
        if (dataTrans == null){
            dataTrans = new DataTrans();
        }
        return dataTrans;
    }

    public DataTrans(){

    }

    public String getPaientname() {
        return paientname.get();
    }

    public StringProperty paientnameProperty() {
        return paientname;
    }

    public void setPaientname(String paientname) {
        this.paientname.set(paientname);
    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public int getScore() {
        return score.get();
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public StringProperty textProperty() {
        return text;
    }

    public final String getText() {
        return textProperty().get();
    }

    public final void setText(String text) {
        textProperty().set(text);
    }
}
