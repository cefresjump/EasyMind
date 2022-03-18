package com.easymind.painter;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class IdeaNode extends TextField {
    private Font font = new Font("NSimSun",40);


    public IdeaNode(){
        this.setText("IDEA");
        this.setFont(font);
    }
}
