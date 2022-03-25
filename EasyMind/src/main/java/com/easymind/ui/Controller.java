package com.easymind.ui;

import com.easymind.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {

    private static Stage stage;

    public static void initStage(Stage stage) {

        Controller.setStage(stage);

        stage.setTitle("EasyMind");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setMinWidth(1850);
        stage.setMinHeight(1020);

        Scene scene = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/mainView.fxml"));
            scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().addAll(App.class.getResource("css/mainView.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(scene);
    }

    public static void setStage(Stage stage) {
        Controller.stage = stage;
    }

    public static void stageMinimize(){
        stage.setIconified(true);
    }

    public static void stageClose(){
        stage.close();
    }

    public static void dragStage(double primaryX,double primaryY,double afterX,double afterY){
        stage.setX(afterX-primaryX);
        stage.setY(afterY-primaryY);
    }
}