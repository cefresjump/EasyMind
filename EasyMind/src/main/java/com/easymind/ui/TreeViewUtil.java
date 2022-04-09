package com.easymind.ui;

import com.easymind.beans.IdeaNode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeViewUtil {
    public static void refreshGeneralView(TreeView<String> generalView, IdeaNode centralIdea){
        TreeItem<String> root = createTreeItem(centralIdea);
        generalView.setRoot(root);
    }

    private static TreeItem<String> createTreeItem(IdeaNode node){
        TreeItem<String> treeItem = new TreeItem<>(node.getMessage());
        if(node.getChildIdea()!=null){
            for(IdeaNode ideaNode : node.getChildIdea()){
                treeItem.getChildren().add(createTreeItem(ideaNode));
            }
        }
        return  treeItem;
    }
}
