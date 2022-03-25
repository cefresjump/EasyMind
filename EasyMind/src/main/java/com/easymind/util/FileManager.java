package com.easymind.util;

import com.easymind.painter.MindMap;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class FileManager {
    private static Stage stage;

    public FileManager(Stage stage){
        FileManager.stage =stage;
    }

    public static File selectAndOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select a emind file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("EMIND","*.emind"));
        File file = fileChooser.showOpenDialog(stage);
        return file;
    }

    public static MindMap readFile(File root){
        MindMap mindMap = null;
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(root));
            mindMap = (MindMap) objectInputStream.readObject();
            objectInputStream.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return mindMap;
    }

    public static void saveAs(MindMap mindMap){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select a directory");
        File file = fileChooser.showSaveDialog(stage);
        System.out.println(file.getPath());
        save(mindMap,file);
    }

    public static void save(MindMap mindMap, File root){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(root));
            objectOutputStream.writeObject(mindMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("保存成功");
    }
}
