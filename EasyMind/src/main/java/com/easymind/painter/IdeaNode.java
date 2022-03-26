package com.easymind.painter;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.util.List;


public class IdeaNode extends TextField implements Serializable {

    private List<IdeaNode> childIdeas;

    public IdeaNode(){
        this.setText("NewIdea");      //设定TextField的文本
        childIdeas = null;
    }

    public List<IdeaNode> getChildIdea() {
        return childIdeas;
    }

}
