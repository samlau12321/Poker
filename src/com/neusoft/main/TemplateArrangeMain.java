package com.neusoft.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class TemplateArrangeMain extends Application {
    Stage stage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/neusoft/frames/TemplateArrangement.fxml"));
        primaryStage.setTitle("模板管理界面");
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:src/picture/company1.jpg"));
        primaryStage.show();
    }

    public void ShowWindow() throws IOException{
        start(stage);
    }
}
