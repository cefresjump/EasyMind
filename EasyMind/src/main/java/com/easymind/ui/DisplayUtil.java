package com.easymind.ui;

import com.easymind.beans.IdeaNode;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayUtil {

    public enum ALIGNMENT{
        CENTER,
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    private static final double DEFAULT_VSPACE = 30.0;
    private static final double DEFAULT_HSPACE = 50.0;

    private static double VSpace = DEFAULT_VSPACE;
    private static double HSpace = DEFAULT_HSPACE;

    public static Group refreshMindMapView(IdeaNode centralIdea,DisplayUtil.ALIGNMENT alignment){
        if(alignment == ALIGNMENT.CENTER) return combineMindMapView(centralIdea);
        else return getMindMapView(centralIdea,alignment);
    }

    private static Group combineMindMapView(IdeaNode centralIdea){
        Group mindMapView = new Group();
        TextField centralTextField = centralIdea.getTextField();
        List<Group> leftGroups = new ArrayList<>();
        List<Group> rightGroups = new ArrayList<>();
        double leftGroupsHeight = 0.0;
        double rightGroupsHeight = 0.0;
        double yOffset;
        for(int orderNum = 0;orderNum<centralIdea.getChildIdea().size() / 2;orderNum++){
            Group group = getMindMapView(centralIdea.getChildIdea().get(orderNum),ALIGNMENT.RIGHT_TO_LEFT);
            leftGroupsHeight = leftGroupsHeight + group.getLayoutBounds().getHeight();
            leftGroups.add(group);
        }
        for(int orderNum = centralIdea.getChildIdea().size() / 2;orderNum < centralIdea.getChildIdea().size();orderNum++){
            Group group = getMindMapView(centralIdea.getChildIdea().get(orderNum),ALIGNMENT.LEFT_TO_RIGHT);
            rightGroupsHeight = rightGroupsHeight + group.getLayoutBounds().getHeight();
            rightGroups.add(group);
        }

        mindMapView.getChildren().add(centralTextField);
        centralTextField.setLayoutX(0.0);
        centralTextField.setLayoutY((leftGroupsHeight - centralTextField.getMinHeight()) / 2);

        yOffset = 0.0;
        for(Group node : leftGroups){
            mindMapView.getChildren().add(node);
            node.setLayoutX(- (centralTextField.getWidth() + HSpace));
            node.setLayoutY(yOffset);
            yOffset = yOffset + node.getLayoutBounds().getHeight() + VSpace;

            Line connectLine = new Line();
            mindMapView.getChildren().add(connectLine);
            connectLine.setStartX(0.0);
            connectLine.setStartY(0.0);
            connectLine.setEndX(-HSpace);
            connectLine.setEndY(( node.getLayoutY() + node.getLayoutBounds().getCenterY()) - (centralTextField.getLayoutY() + (centralTextField.getHeight() / 2)));
            connectLine.setLayoutX(centralTextField.getLayoutX());
            connectLine.setLayoutY(centralTextField.getLayoutY()+centralTextField.getHeight()/2);
        }

        yOffset = (leftGroupsHeight - rightGroupsHeight)/2;
        for(Group node : rightGroups){
            mindMapView.getChildren().add(node);
            node.setLayoutX(centralTextField.getWidth() + HSpace);
            node.setLayoutY(yOffset);
            yOffset = yOffset + node.getLayoutBounds().getHeight() + VSpace;

            Line connectLine = new Line();
            mindMapView.getChildren().add(connectLine);
            connectLine.setStartX(0.0);
            connectLine.setStartY(0.0);
            connectLine.setEndX(HSpace);
            connectLine.setEndY(( node.getLayoutY() + node.getLayoutBounds().getCenterY()) - (centralTextField.getLayoutY() + (centralTextField.getHeight() / 2)));
            connectLine.setLayoutX(centralTextField.getLayoutX() + centralTextField.getMinWidth());
            connectLine.setLayoutY(centralTextField.getLayoutY()+centralTextField.getHeight()/2);
        }

        return mindMapView;
    }

    public static Group getMindMapView(IdeaNode centralIdea,DisplayUtil.ALIGNMENT alignment){
        Map<IdeaNode,Group> ideaNodeNodeMap = new HashMap<>();

        for(IdeaNode ideaNode:centralIdea.getLeaves()){
            Group group = new Group(ideaNode.getTextField());
            ideaNodeNodeMap.put(ideaNode,group);
        }

        int currentDepth = centralIdea.getDeepestDepth();
        while(currentDepth != 1){
            currentDepth--;

            for (IdeaNode parent: centralIdea.getNodesInSpecificDepth(currentDepth)) {
                if(!parent.isLeaf()){
                    List<Group> groups = new ArrayList<>();
                    for(IdeaNode node : parent.getChildIdea()){
                        groups.add(ideaNodeNodeMap.get(node));
                    }
                    ideaNodeNodeMap.put(parent,createGroup(parent.getTextField(),groups,alignment));
                }
            }
        }
        return ideaNodeNodeMap.get(centralIdea);
    }

    public static Group createGroup(TextField parent, List<Group> groups,DisplayUtil.ALIGNMENT alignment){
        Group group = new Group();
        double yOffset = 0.0;
        group.getChildren().addAll(groups);
        group.getChildren().add(parent);
        if(alignment == ALIGNMENT.LEFT_TO_RIGHT){
            for(Group node : groups){
                node.setLayoutX(parent.getMinWidth() + HSpace);
                node.setLayoutY(yOffset);
                yOffset = yOffset + node.getLayoutBounds().getHeight() + VSpace;
            }
            parent.setLayoutX(0.0);
            parent.setLayoutY( (yOffset - VSpace - parent.getHeight()) / 2);
            for(Group node : groups){
                Line connectLine = new Line();
                connectLine.setStartX(0.0);
                connectLine.setStartY(0.0);
                connectLine.setEndX(HSpace);
                //线高 = 子节点左中的坐标 - 父节点右中的坐标
                connectLine.setEndY(( node.getLayoutY() + node.getLayoutBounds().getCenterY()) - (parent.getLayoutY() + (parent.getHeight() / 2)));

                connectLine.setLayoutX(parent.getLayoutX() + parent.getMinWidth());
                connectLine.setLayoutY(parent.getLayoutY()+parent.getHeight()/2);
                group.getChildren().add(connectLine);
            }
        }
        else if(alignment == ALIGNMENT.RIGHT_TO_LEFT){
            for(Group node : groups){
                node.setLayoutX(-(parent.getWidth() + HSpace));   //对称取负
                node.setLayoutY(yOffset);
                yOffset = yOffset + node.getLayoutBounds().getHeight() + VSpace;
            }
            parent.setLayoutX(0.0);
            parent.setLayoutY( (yOffset - VSpace - parent.getHeight()) / 2);
            for(Group node : groups){
                Line connectLine = new Line();
                connectLine.setStartX(0.0);
                connectLine.setStartY(0.0);
                connectLine.setEndX(-HSpace);    //线根据X轴相对于上方的做对称
                connectLine.setEndY(( node.getLayoutY() + node.getLayoutBounds().getCenterY()) - (parent.getLayoutY() + (parent.getHeight() / 2)));

                connectLine.setLayoutX(parent.getLayoutX());  //线从父节点左中开始，不需要加上父节点的长度
                connectLine.setLayoutY(parent.getLayoutY()+parent.getHeight()/2);
                group.getChildren().add(connectLine);
            }
        }
        return group;
    }

    public static double getVSpace() { return VSpace;}
    public static double getHSpace() { return HSpace;}

    public static void setHSpace(double HSpace) { DisplayUtil.HSpace = HSpace;}
    public static void setVSpace(double VSpace) { DisplayUtil.VSpace = VSpace;}

}
