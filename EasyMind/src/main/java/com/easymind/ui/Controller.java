package com.easymind.ui;

import com.easymind.util.URLHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {

    public static void InitStage(Stage stage) {

        stage.setTitle("EasyMind");
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setMinWidth(1280);
        stage.setMinHeight(800);
        stage.setMaxWidth(1280);
        stage.setMaxHeight(800);

        Scene scene = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(URLHelper.getResource("fxml/mainview.fxml"));
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(scene);
    }
}