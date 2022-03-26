package com.easymind.util;

import com.easymind.painter.MindMap;
import com.easymind.ui.WarnPage;
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
        if(mindMap==null) WarnPage.WarnReport(WarnPage.WARN_TYPE.OPEN_FAILED);
        return mindMap;
    }

    public static void saveAs(MindMap mindMap){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(mindMap.getMindMapName());
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("EMIND","*.emind"));
        fileChooser.setTitle("select a directory");

        File file = fileChooser.showSaveDialog(stage);
        save(mindMap,file);
    }

    public static void save(MindMap mindMap, File root){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(root));
            objectOutputStream.writeObject(mindMap);
        } catch (FileNotFoundException| NotSerializableException exception) {
            WarnPage.WarnReport(WarnPage.WARN_TYPE.SAVE_FAILED);
            return;
        }
        catch (IOException e){}
        WarnPage.WarnReport(WarnPage.WARN_TYPE.SAVE_SUCCESS);
    }
}
