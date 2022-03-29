package com.easymind.ui;

import com.easymind.beans.IdeaNode;
import com.easymind.beans.MindMap;
import com.easymind.util.FileManager;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class MainView{
    //偏移量，用于窗口拖动
    private double xOffset = 0;
    private double yOffset = 0;

    private MindMap mindMap = null;
    private File root = null;

    @FXML
    private Label editButton;

    @FXML
    private ContextMenu menu;

    @FXML
    private TextField mindMapNameTextField;

    @FXML
    private AnchorPane canvas;

    //editButton编辑按钮的功能（新建，打开，保存，另存为)
    @FXML
    private void openMenu() {
        if(menu.isShowing()) menu.hide();
        else menu.show(editButton,Side.BOTTOM,0,0);
    }

    @FXML
    private void newFile() {
        mindMap = new MindMap();
        initMainView();
    }

    @FXML
    private void openFile() {
        File temp = FileManager.selectFile();
        if(temp!=null){
            root = temp;
            mindMap = FileManager.readFile(root);
            if(mindMap!=null) initMainView();
        }
    }

    @FXML
    private void saveFile() {
        if(root!=null) FileManager.save(mindMap,root);
        else if(mindMap!=null) FileManager.saveAs(mindMap);
    }

    @FXML
    private void saveAs(){
        if(mindMap!=null) FileManager.saveAs(mindMap);
    }

    //在编辑按钮右侧的mindMapNameTextField文本输入栏功能（修改思维导图名称)
    @FXML
    public void editMindMapName() {
        mindMap.setMindMapName(mindMapNameTextField.getText());
    }

    //窗口操作（窗口拖动，最小化，关闭）
    @FXML
    private void dragStart(MouseEvent mouseEvent) {
        xOffset = mouseEvent.getSceneX();
        yOffset = mouseEvent.getSceneY();
    }

    @FXML
    private void dragEnd(MouseEvent mouseEvent) {
        Controller.dragStage(xOffset,yOffset,mouseEvent.getScreenX(),mouseEvent.getScreenY());
    }

    @FXML
    private void minimize() {
        Controller.stageMinimize();
    }

    @FXML
    private void close() {
        Controller.stageClose();
    }

    private void initMainView(){
        mindMapNameTextField.setText(mindMap.getMindMapName());
        mindMapNameTextField.setEditable(true);


        refreshCanvas();

        System.out.println("加载完毕");
    }

    private void refreshCanvas(){
        IdeaNode centralIdea = mindMap.getCentralIdea();
        canvas.getChildren().add(centralIdea);
        AnchorPane.setLeftAnchor(centralIdea, 2000.0);
    }
}
