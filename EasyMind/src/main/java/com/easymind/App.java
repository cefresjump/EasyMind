package com.easymind;

import javafx.application.Application;
import javafx.stage.Stage;

import com.easymind.ui.Controller;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Controller.initStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
