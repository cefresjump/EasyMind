module EasyMind {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.swing;
    requires java.desktop;

    exports com.easymind;

    opens com.easymind.util to java.desktop;
    opens com.easymind.ui to javafx.fxml;
}