package com.easymind.beans;

import java.io.Serializable;

public class MindMap implements Serializable {

    private String mindMapName;
    private final IdeaNode centralIdea;

    public MindMap(){
        this.mindMapName = "newMindMap";
        this.centralIdea = new IdeaNode();
    }

    public String getMindMapName() {
        return mindMapName;
    }

    public void setMindMapName(String mindMapName) {
        this.mindMapName = mindMapName;
    }

    public IdeaNode getCentralIdea() {
        return centralIdea;
    }

}
