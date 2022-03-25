package com.easymind.painter;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


import java.io.Serializable;
import java.util.List;


public class IdeaNode extends TextField implements Serializable {
    private final String text= "NewIdea";
    private final List<IdeaNode> childIdeas = null;

    public IdeaNode(){}

    //getText
    //getText方法在java.scene.control.TextInputControl存在，且为final

    public List<IdeaNode> getChildIdea() {
        return childIdeas;
    }

}
