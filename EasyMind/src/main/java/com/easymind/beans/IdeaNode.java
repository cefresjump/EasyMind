package com.easymind.beans;

import com.easymind.ui.MainView;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.Serializable;
import java.util.List;


public class IdeaNode extends TextField implements Serializable {

    private String message = "NewIdea";
    private int depth;
    private final List<IdeaNode> childIdeas;
    private IdeaNode nodeParent;

    public IdeaNode(IdeaNode parent){

        this.nodeParent=parent;
        if(parent==null) depth = 1;
        else depth = parent.getDepth();
        childIdeas = null;

        this.setMinWidth(200);
        this.setMinHeight(40);
        this.setText(message);      //设定TextField的文本

        this.setOnAction(edited -> message = getText());
        this.setOnMouseReleased(selectNode -> MainView.setSelectedNode(IdeaNode.this));
        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

            }
        });
    }

    public int getDepth() {
        return depth;
    }

    public List<IdeaNode> getChildIdea() {
        return childIdeas;
    }

    public IdeaNode getNodeParent() {
        return nodeParent;
    }

    public void setNodeParent(IdeaNode parent){
        this.nodeParent = parent;
    }
}
