package com.easymind.painter;

import javafx.scene.text.Font;

import java.io.File;
import java.io.Serializable;

public class MindMap implements Serializable {
    private final String mindMapName = null;
    private final IdeaNode centralIdea = null;

    private final Font overallFont = new  Font("NSimSun",10);

    public MindMap(File file){}

    public String getMindMapName() {
        return mindMapName;
    }
}
