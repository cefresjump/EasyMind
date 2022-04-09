package com.easymind.ui;

import com.easymind.beans.IdeaNode;
import com.easymind.beans.MindMap;
import com.easymind.util.FileManager;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class MainView{
    //偏移量，用于窗口拖动
    private double xOffset = 0;
    private double yOffset = 0;

    private static MindMap mindMap = null;
    private static File root = null;
    private static IdeaNode selectedNode = null;

    @FXML
    private Label editButton;

    @FXML
    private ContextMenu menu;

    @FXML
    private TextField mindMapNameTextField;

    @FXML
    private ScrollPane scrollBoard;

    @FXML
    private AnchorPane canvas;

    @FXML
    private TreeView<String> generalView;

    //editButton编辑按钮的功能（新建，打开，保存，另存为,导出)
    @FXML
    private void openMenu() {
        if(menu.isShowing()) menu.hide();
        else menu.show(editButton,Side.BOTTOM,0,0);
    }

    @FXML
    private void newFile() {
        mindMap = new MindMap();
        root = null;
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

    @FXML
    private void exportAsPNG(){

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

    @FXML
    private void newChildNode() {
        if(selectedNode!=null){
            selectedNode.newChild();

            DisplayUtil.refreshCanvas();
            TreeViewUtil.refreshGeneralView(generalView,mindMap.getCentralIdea());
        }
    }

    @FXML
    private void newBrotherNode() {
        if(selectedNode.getNodeParent()!=null){
           selectedNode.newBrother();

            DisplayUtil.refreshCanvas();
            TreeViewUtil.refreshGeneralView(generalView,mindMap.getCentralIdea());
        }
    }

    @FXML
    private void deleteNode() {
        if(selectedNode.getNodeParent()!=null){
            selectedNode.getNodeParent().getChildIdea().remove(selectedNode);

            DisplayUtil.refreshCanvas();
            TreeViewUtil.refreshGeneralView(generalView,mindMap.getCentralIdea());
        }
    }

    @FXML
    private void setAlignLeftToRight(){
        mindMap.setALIGNMENT(DisplayUtil.ALIGNMENT.LEFT_TO_RIGHT);
        DisplayUtil.refreshCanvas();
    }

    @FXML
    private void setAlignCenter(){
        mindMap.setALIGNMENT(DisplayUtil.ALIGNMENT.CENTER);
        DisplayUtil.refreshCanvas();
    }

    @FXML
    private void setAlignRightToLeft(){
        mindMap.setALIGNMENT(DisplayUtil.ALIGNMENT.RIGHT_TO_LEFT);
        DisplayUtil.refreshCanvas();
    }

    private void initMainView(){
        mindMapNameTextField.setText(mindMap.getMindMapName());
        mindMapNameTextField.setEditable(true);

        scrollBoard.setHvalue(0.5);
        scrollBoard.setVvalue(0.5);
        
        DisplayUtil.init(canvas,mindMap);
        DisplayUtil.refreshCanvas();

        TreeViewUtil.refreshGeneralView(generalView,mindMap.getCentralIdea());
    }

    public static void setSelectedNode(IdeaNode node){
        selectedNode = node;
    }

}
