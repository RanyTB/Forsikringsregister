module forsikringsregister {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.rtbeb to javafx.fxml;
    exports com.rtbeb.controller.redigering;
    exports com.rtbeb.controller.registrering;
    exports com.rtbeb.controller.visning;
}
