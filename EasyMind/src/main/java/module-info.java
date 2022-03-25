module EasyMind {
    requires javafx.fxml;
    requires javafx.controls;


    exports com.easymind;
    opens com.easymind.ui to javafx.fxml;
}