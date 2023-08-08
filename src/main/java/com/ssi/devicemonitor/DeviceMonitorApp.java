package com.ssi.devicemonitor;

import com.ssi.devicemonitor.controller.DeviceMonitorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeviceMonitorApp extends Application {

    public static Stage primaryStage;
    private static DeviceMonitorController controller;
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        ShowMainScene();
    }

    public static void ShowMainScene() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(DeviceMonitorApp.class.getResource("device_monitor.fxml"));

        if (controller == null) {
            controller = new DeviceMonitorController();
        }

        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Device Monitor");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
