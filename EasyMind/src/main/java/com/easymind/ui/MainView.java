package com.easymind.ui;

import com.easymind.util.FileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainView{
    @FXML
    private static MenuItem open;

    @FXML
    private static MenuItem save;

    @FXML
    public void newFile(ActionEvent actionEvent) {
        System.out.println("新建");
    }

    @FXML
    public void openFile(ActionEvent actionEvent) {
        FileManager.selectAndOpen();
        System.out.println("打开");
    }

    @FXML
    public void saveFile(ActionEvent actionEvent) {
        System.out.println("保存");
    }
}
