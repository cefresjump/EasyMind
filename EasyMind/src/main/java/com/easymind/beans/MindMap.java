package com.easymind.beans;


import com.easymind.ui.DisplayUtil;

import java.io.Serializable;

public class MindMap implements Serializable {

    private String mindMapName;
    private final IdeaNode centralIdea;
    private DisplayUtil.ALIGNMENT ALIGNMENT;

    public MindMap(){
        this.mindMapName = "newMindMap";
        this.centralIdea = new IdeaNode(null);
        ALIGNMENT = DisplayUtil.ALIGNMENT.LEFT_TO_RIGHT;
    }

    public String getMindMapName() { return mindMapName;}
    public IdeaNode getCentralIdea() { return centralIdea;}
    public DisplayUtil.ALIGNMENT getALIGNMENT() { return ALIGNMENT;}

    public void setMindMapName(String mindMapName) { this.mindMapName = mindMapName;}
    public void setALIGNMENT(DisplayUtil.ALIGNMENT ALIGNMENT) { this.ALIGNMENT = ALIGNMENT;}

}