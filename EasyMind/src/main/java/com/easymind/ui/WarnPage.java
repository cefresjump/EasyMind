package com.easymind.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class WarnPage {
     public enum WARN_TYPE{
         //INFORMATION
         SAVE_SUCCESS,
         EXPORT_SUCCESS,

         //WARNING
         STAGE_LOAD_FAILED,
         SAVE_FAILED,
         OPEN_FAILED,
         EXPORT_FAILED,
    }

    public static void WarnReport(WARN_TYPE WARN_TYPE){
        switch (WARN_TYPE) {
            case SAVE_SUCCESS -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"保存成功",ButtonType.CLOSE);
                alert.show();
            }
            case EXPORT_SUCCESS -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"导出成功",ButtonType.CLOSE);
                alert.show();
            }
            case STAGE_LOAD_FAILED -> {
                Alert alert = new Alert(Alert.AlertType.WARNING,"界面打开失败",ButtonType.CLOSE);
                alert.showAndWait();
                System.exit(1);
            }
            case SAVE_FAILED -> {
                Alert alert = new Alert(Alert.AlertType.WARNING,"保存失败",ButtonType.CLOSE);
                alert.show();
            }
            case OPEN_FAILED -> {
                Alert alert = new Alert(Alert.AlertType.WARNING,"文件已损坏",ButtonType.CLOSE);
                alert.show();
            }
            case EXPORT_FAILED -> {
                Alert alert = new Alert(Alert.AlertType.WARNING,"导出失败",ButtonType.CLOSE);
                alert.show();
            }
            default -> {

            }
        }
    }
}
