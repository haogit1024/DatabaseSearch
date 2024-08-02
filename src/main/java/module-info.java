module com.czh.databasesearch {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.lang3;


    opens com.czh.database.search to javafx.fxml;
    exports com.czh.database.search;
    exports com.czh.database.search.ui;
    opens com.czh.database.search.ui to javafx.fxml;
}