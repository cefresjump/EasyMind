package com.easymind.beans;

import javafx.scene.control.TextField;

import java.io.Serializable;
import java.util.List;


public class IdeaNode extends TextField implements Serializable {

    private String message = "NewIdea";
    private final List<IdeaNode> childIdeas;
    private final IdeaNode nodeParent;

    public IdeaNode(IdeaNode parent){
        this.nodeParent=parent;
        this.setMinWidth(200);
        this.setMinHeight(40);
        this.setText(message);      //设定TextField的文本
        this.setOnAction(actionEvent -> message = getText());
        childIdeas = null;
    }

    public List<IdeaNode> getChildIdea() {
        return childIdeas;
    }

    public IdeaNode getNodeParent() {
        return nodeParent;
    }
}
