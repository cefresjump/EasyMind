package com.easymind.beans;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.util.List;


public class IdeaNode extends TextField implements Serializable {

    private String message = "NewIdea";
    private final List<IdeaNode> childIdeas;

    public IdeaNode(){

        this.setText("message");      //设定TextField的文本
        this.setOnAction(actionEvent -> message = getText());
        childIdeas = null;
    }

    public List<IdeaNode> getChildIdea() {
        return childIdeas;
    }
}
