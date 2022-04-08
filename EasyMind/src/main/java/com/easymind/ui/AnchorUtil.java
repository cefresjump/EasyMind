package com.easymind.ui;

import com.easymind.beans.IdeaNode;
import com.easymind.beans.MindMap;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class AnchorUtil {
    public enum ALIGNMENT implements Serializable {
        CENTER,
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    private static AnchorPane canvas;
    private static MindMap mindMap;

    private static double DEFAULT_VSPACE = 30.0;
    private static double DEFAULT_HSPACE = 30.0;
    private double VSpace = DEFAULT_VSPACE;
    private double HSpace = DEFAULT_HSPACE;

    public static void init(AnchorPane canvas,MindMap mindMap){
        AnchorUtil.canvas=canvas;
        AnchorUtil.mindMap=mindMap;
    }

    public double getHSpace() {
        return HSpace;
    }

    public double getVSpace() {
        return VSpace;
    }

    public static void refreshCanvas(){
        IdeaNode centralIdea = mindMap.getCentralIdea();
        double xOffset = 0;
        double yOffset = 0;
        canvas.getChildren().removeAll();
        setAnchor(centralIdea,xOffset,yOffset);
    }

    public static void setAnchor(IdeaNode node,double xOffset,double yOffset){
        //xOffset相当于canvas中心相对于node中心的x差距，正表示在右边，负表示在左边，yOffset同理
        if(xOffset>=0) AnchorPane.setLeftAnchor(node,canvas.getWidth()/2 + xOffset - node.getMinWidth()/2);
        else AnchorPane.setRightAnchor(node,canvas.getWidth()/2 + xOffset - node.getMinWidth()/2);
        if(yOffset>=0) AnchorPane.setBottomAnchor(node,canvas.getHeight()/2 + yOffset - node.getMinHeight()/2);
        else AnchorPane.setTopAnchor(node,canvas.getHeight()/2 + yOffset - node.getMinHeight()/2);
        canvas.getChildren().add(node);
    }
}
