package com.neusoft.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class QuestionViewMain extends Application {
    Stage stage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/neusoft/frames/QuestionViewSurface.fxml"));
        primaryStage.setTitle("问题查看界面");
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
