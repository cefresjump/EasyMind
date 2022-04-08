package com.easymind.ui;

import com.easymind.beans.IdeaNode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeViewUtil {
    public static void refresh(TreeView<String> generalView,IdeaNode centralIdea){
        TreeItem<String> root = createTreeItem(centralIdea);
        generalView.setRoot(root);
    }

    private static TreeItem<String> createTreeItem(IdeaNode Node){
        TreeItem<String> treeItem = new TreeItem<>(Node.getText());
        if(Node.getChildIdea()!=null){
            for(IdeaNode ideaNode : Node.getChildIdea()){
                treeItem.getChildren().add(createTreeItem(ideaNode));
            }
        }
        return  treeItem;
    }
}
