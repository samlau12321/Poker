package com.neusoft.controller;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application {
    public MainController(){
    }

    public void changeWindow() throws Exception{
        RegisterController registerController = new RegisterController();
        registerController.showWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/neusoft/frames/Welcome.fxml"));
        primaryStage.setTitle("东软颐养中心管理系统");
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getClassLoader().getResource("com/neusoft/frames/WelcomeStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:src/picture/company1.jpg"));
        primaryStage.setResizable(false);
        primaryStage.requestFocus();
        primaryStage.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!primaryStage.isFocused()){
                    primaryStage.requestFocus();
                }
            }
        });
        primaryStage.show();
    }
}
