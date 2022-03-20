package com.easymind.util;

import com.easymind.painter.MindMap;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileManager {
    private static Stage stage;

    public FileManager(Stage stage){
        FileManager.stage =stage;
    }

    public static void selectAndOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select a emind file");
        File file = fileChooser.showOpenDialog(stage);

    }

    public static void selectAndSave(MindMap mindMap){

    }

    public static void save(MindMap mindMap, File root){

    }
}
