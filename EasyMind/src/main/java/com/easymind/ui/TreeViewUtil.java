package com.easymind.ui;

import com.easymind.beans.IdeaNode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeViewUtil {
    private static TreeView<String> generalView;
    private static IdeaNode centralIdea;

    public static void init(TreeView<String> generalView, IdeaNode centralIdea){
        TreeViewUtil.generalView = generalView;
        TreeViewUtil.centralIdea = centralIdea;
    }

    public static void refreshGeneralView(){
        TreeItem<String> root = createTreeItem(centralIdea);
        generalView.setRoot(root);
    }

    private static TreeItem<String> createTreeItem(IdeaNode node){
        TreeItem<String> treeItem = new TreeItem<>(node.getMessage());
        if(!node.isLeaf()){
            for(IdeaNode ideaNode : node.getChildIdea()){
                treeItem.getChildren().add(createTreeItem(ideaNode));
            }
        }
        return  treeItem;
    }
}