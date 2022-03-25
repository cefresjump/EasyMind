package com.easymind.ui;

import com.easymind.painter.MindMap;
import com.easymind.util.FileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;

public class MainView{
    private double xOffset = 0;
    private double yOffset = 0;
    private MindMap mindMap = null;
    private File root = null;

    @FXML
    private Label editButton;

    @FXML
    private ContextMenu menu;

    @FXML
    private Label mindMapNameLabel;

    @FXML
    public void newFile(ActionEvent e) {
        System.out.println("新建");
    }

    @FXML
    public void openFile(ActionEvent e) {
        File temp = FileManager.selectAndOpen();
        if(temp==null) System.out.println("没打开");
        else {
            root = temp;
            mindMap = FileManager.readFile(root);
            if(mindMap==null) System.out.println("空");
            else System.out.println("打开");
        }
    }

    @FXML
    public void saveFile(ActionEvent e) {
        if(root!=null) FileManager.save(mindMap,root);
        else if(mindMap!=null) FileManager.saveAs(mindMap);
        else System.out.println("你保存了空气");
    }

    @FXML
    public void saveAs(ActionEvent e){
        if(mindMap!=null) FileManager.saveAs(mindMap);
        else System.out.println("你保存了空气");
    }

    @FXML
    public void openMenu(MouseEvent mouseEvent) {
        if(menu.isShowing()) menu.hide();
        else menu.show(editButton,Side.BOTTOM,0,0);
    }

    @FXML
    public void minimize(MouseEvent mouseEvent) {
        Controller.stageMinimize();
    }

    @FXML
    public void close(MouseEvent mouseEvent) {
        Controller.stageClose();
    }

    @FXML
    public void dragStart(MouseEvent mouseEvent) {
        xOffset = mouseEvent.getSceneX();
        yOffset = mouseEvent.getSceneY();
    }

    @FXML
    public void dragEnd(MouseEvent mouseEvent) {
        Controller.dragStage(xOffset,yOffset,mouseEvent.getScreenX(),mouseEvent.getScreenY());
    }
}
