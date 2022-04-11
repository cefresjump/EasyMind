package com.easymind.beans;

import com.easymind.ui.MainView;
import com.easymind.ui.TreeViewUtil;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IdeaNode implements Serializable {
    private static final double DEFAULT_WIDTH = 200;
    private static final double DEFAULT_HEIGHT = 40;

    private transient TextField textField;

    private String message = "newIdea";
    private final int depth;
    private final IdeaNode nodeParent;
    private final List<IdeaNode> childIdeas;

    public IdeaNode(IdeaNode parent){

        if(parent==null) depth = 1;
        else depth = parent.getDepth() + 1;
        this.nodeParent=parent;
        childIdeas = new ArrayList<>();

        initNode();
    }

    public void initNode(){
        this.textField = new TextField();
        this.textField.setMinWidth(DEFAULT_WIDTH);
        this.textField.setMinHeight(DEFAULT_HEIGHT);
        this.textField.setText(message);      //设定TextField的文本

        this.textField.setOnAction(edited -> {
            message = textField.getText();
            TreeViewUtil.refreshGeneralView();
        });
        this.textField.setOnMouseReleased(selectNode -> MainView.setSelectedNode(IdeaNode.this));
    }

    public void initAllNodes(){
        this.initNode();
        if(!this.isLeaf()){
            for(IdeaNode ideaNode : this.getChildIdea())
                initAllNodes();
        }
    }

    public TextField getTextField() { return textField;}
    public String getMessage() { return message;}
    public int getDepth() { return depth;}
    public List<IdeaNode> getChildIdea() { return childIdeas;}
    public IdeaNode getNodeParent() { return nodeParent;}

    public boolean isLeaf(){ return this.getChildIdea().isEmpty();}

    public void newChild(){ this.getChildIdea().add(new IdeaNode(this));}

    public void newBrother(){ this.getNodeParent().getChildIdea().add(new IdeaNode(this.getNodeParent()));}

    public List<IdeaNode> getNodesInSpecificDepth(int targetDepth){
        List<IdeaNode> nodes = new ArrayList<>();
        if(this.getDepth()==targetDepth) nodes.add(this);
        else {
            if(!this.isLeaf()){
                for(IdeaNode node : this.getChildIdea())
                    nodes.addAll(node.getNodesInSpecificDepth(targetDepth));
            }
        }
        return nodes;
    }

    public int getDeepestDepth(){
        int deepestDepth = 1;
        for(IdeaNode node:this.getLeaves()){
            if(node.getDepth()>deepestDepth) deepestDepth=node.getDepth();
        }
        return deepestDepth;
    }

    public List<IdeaNode> getLeaves(){
        List<IdeaNode> nodes = new ArrayList<>();
        if(this.isLeaf()) nodes.add(this);
        else {
            for(IdeaNode child : this.getChildIdea()){
                nodes.addAll(child.getLeaves());
            }
        }
        return nodes;
    }

}
