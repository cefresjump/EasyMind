package com.easymind.beans;


import com.easymind.ui.AnchorUtil;

import java.io.Serializable;

public class MindMap implements Serializable {

    private String mindMapName;
    private IdeaNode centralIdea;
    private AnchorUtil.ALIGNMENT ALIGNMENT = AnchorUtil.ALIGNMENT.LEFT_TO_RIGHT;

    public MindMap(){
        this.mindMapName = "newMindMap";
        this.centralIdea = new IdeaNode(null);
    }

    public String getMindMapName() {
        return mindMapName;
    }

    public void setMindMapName(String mindMapName) {
        this.mindMapName = mindMapName;
    }

    public AnchorUtil.ALIGNMENT getALIGNMENT() {
        return ALIGNMENT;
    }

    public IdeaNode getCentralIdea() {
        return centralIdea;
    }

}
