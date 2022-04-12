package com.easymind.ui;

import com.easymind.beans.IdeaNode;
import com.easymind.beans.MindMap;
import com.easymind.util.FileManager;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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
            if(mindMap!=null) {
                mindMap.getCentralIdea().initAllNodes();
                initMainView();
            }
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
        FileManager.exportAsPNG(scrollBoard,mindMap);
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

    //节点的新增，删除操作
    @FXML
    private void newChildNode() {
        if(selectedNode!=null){
            selectedNode.newChild();

            scrollBoard.setContent(DisplayUtil.refreshMindMapView(mindMap.getCentralIdea(),mindMap.getALIGNMENT()));
            TreeViewUtil.refreshGeneralView();
        }
    }

    @FXML
    private void newBrotherNode() {
        if(selectedNode.getNodeParent()!=null){
           selectedNode.newBrother();

            scrollBoard.setContent(DisplayUtil.refreshMindMapView(mindMap.getCentralIdea(),mindMap.getALIGNMENT()));
            TreeViewUtil.refreshGeneralView();
        }
    }

    @FXML
    private void deleteNode() {
        if(selectedNode.getNodeParent()!=null){
            selectedNode.getNodeParent().getChildIdea().remove(selectedNode);

            scrollBoard.setContent(DisplayUtil.refreshMindMapView(mindMap.getCentralIdea(),mindMap.getALIGNMENT()));
            TreeViewUtil.refreshGeneralView();
        }
    }

    @FXML
    private void setAlignLeftToRight(){
        mindMap.setALIGNMENT(DisplayUtil.ALIGNMENT.LEFT_TO_RIGHT);
        scrollBoard.setContent(DisplayUtil.refreshMindMapView(mindMap.getCentralIdea(),mindMap.getALIGNMENT()));
    }

    @FXML
    private void setAlignCenter(){
        mindMap.setALIGNMENT(DisplayUtil.ALIGNMENT.CENTER);
        scrollBoard.setContent(DisplayUtil.refreshMindMapView(mindMap.getCentralIdea(),mindMap.getALIGNMENT()));
    }

    @FXML
    private void setAlignRightToLeft(){
        mindMap.setALIGNMENT(DisplayUtil.ALIGNMENT.RIGHT_TO_LEFT);
        scrollBoard.setContent(DisplayUtil.refreshMindMapView(mindMap.getCentralIdea(),mindMap.getALIGNMENT()));
    }

    private void initMainView(){
        mindMapNameTextField.setText(mindMap.getMindMapName());
        mindMapNameTextField.setEditable(true);

        scrollBoard.setContent(DisplayUtil.refreshMindMapView(mindMap.getCentralIdea(),mindMap.getALIGNMENT()));

        TreeViewUtil.init(generalView,mindMap.getCentralIdea());
        TreeViewUtil.refreshGeneralView();
    }

    public static void setSelectedNode(IdeaNode node){
        selectedNode = node;
    }

}
