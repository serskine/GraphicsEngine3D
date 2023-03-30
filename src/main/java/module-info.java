module soupthatisthick.com.onelonecoder {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens soupthatisthick.com.onelonecoder to javafx.fxml;
    exports soupthatisthick.com.onelonecoder;
}