package com.easymind.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class WarnPage {
     public enum WARN_TYPE{
         //INFORMATION
         SAVE_SUCCESS,

         //WARNING
         SAVE_FAILED,
         OPEN_FAILED,
    }

    public static void WarnReport(WARN_TYPE WARN_TYPE){
        switch (WARN_TYPE) {
            case SAVE_SUCCESS -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"保存成功",ButtonType.CLOSE);
                alert.show();
            }
            case SAVE_FAILED -> {
                Alert alert = new Alert(Alert.AlertType.WARNING,"保存失败",ButtonType.CLOSE);
            }
            case OPEN_FAILED -> {
                Alert alert = new Alert(Alert.AlertType.WARNING,"文件已损坏",ButtonType.CLOSE);
                alert.show();
            }
            default -> {
            }
        }
    }
}
