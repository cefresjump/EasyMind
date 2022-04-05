package com.easymind.util;

import com.easymind.beans.MindMap;
import com.easymind.ui.WarnPage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class FileManager {
    private static Stage stage;

    public FileManager(Stage stage){
        FileManager.stage =stage;
    }

    public static File selectFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select a emind file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("EMIND","*.emind"));
        return fileChooser.showOpenDialog(stage);
    }

    public static MindMap readFile(File root){
        MindMap mindMap;
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(root));
            mindMap = (MindMap) objectInputStream.readObject();
            objectInputStream.close();
        }catch (IOException | ClassNotFoundException e){
            WarnPage.WarnReport(WarnPage.WARN_TYPE.OPEN_FAILED);
            return null;
        }
        return mindMap;
    }

    public static void saveAs(MindMap mindMap){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(mindMap.getMindMapName());
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("EMIND","*.emind"));
        fileChooser.setTitle("select a directory");

        File file = fileChooser.showSaveDialog(stage);
        if(file!=null) save(mindMap,file);
    }

    public static void save(MindMap mindMap, File root){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(root));
            objectOutputStream.writeObject(mindMap);
            objectOutputStream.close();
        } catch (IOException e) {
            WarnPage.WarnReport(WarnPage.WARN_TYPE.SAVE_FAILED);
            return;
        }
        WarnPage.WarnReport(WarnPage.WARN_TYPE.SAVE_SUCCESS);
    }
}
