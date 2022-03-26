package com.easymind.painter;

import javafx.scene.text.Font;

import java.io.Serializable;

public class MindMap implements Serializable {

    private String mindMapName = null;
    private IdeaNode centralIdea = null;

    private final Font overallFont = new  Font("NSimSun",10);

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
