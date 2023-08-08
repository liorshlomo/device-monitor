package com.ssi.devicemonitor.controller;

import com.ssi.devicemonitor.DeviceMonitorApp;
import com.ssi.devicemonitor.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DeviceMonitorController {
    @FXML
    private ListView<Device> deviceListView;
    @FXML
    private TextField deviceNameTextField;
    @FXML
    private TextField deviceManufacturerTextField;
    @FXML
    private TextField deviceVersionTextField;
    @FXML
    private TextField locationHardwareTextField;
    @FXML
    private TextField macAddHardwareTextField;
    @FXML
    private TextField dateAndTimeTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addDeviceButton;
    @FXML
    private ComboBox<String> deviceTypeComboBox;
    private DeviceMonitor deviceMonitor;
    private VBox softwareDetailsContainer;
    private VBox hardwareDetailsContainer;
    private VBox mainVbox;


    public void initialize() {
        softwareDetailsContainer = new VBox(10);
        hardwareDetailsContainer = new VBox(10);
        deviceMonitor = new DeviceMonitor();

        deviceMonitor.addDevice(new GeneralDevice("Device 1"));
        deviceMonitor.addDevice(new GeneralDevice("Device 2"));
        deviceMonitor.addDevice(new GeneralDevice("Device 3"));

        deviceListView.setItems(FXCollections.observableList(deviceMonitor.getDevices()));
        deviceListView.setCellFactory(deviceListView -> new DeviceListCell());

        // Add context menu to ListView
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeItem = new MenuItem("Remove");
        MenuItem detailsItem = new MenuItem("Details");

        removeItem.setOnAction(event -> {
            Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
            if (selectedDevice != null) {
                deviceMonitor.removeDevice(selectedDevice);
                deviceListView.setItems(FXCollections.observableList(deviceMonitor.getDevices()));
                deviceListView.setCellFactory(deviceListView -> new DeviceListCell());
            }
        });

        detailsItem.setOnAction(event -> {
            Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
                if (selectedDevice != null) {
                    showDetails(selectedDevice);
                }
        }); detailsItem.setOnAction(event -> {
            Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
                if (selectedDevice != null) {
                    showDetails(selectedDevice);
                }
        });

        contextMenu.getItems().addAll(removeItem,detailsItem);
        deviceListView.setContextMenu(contextMenu);

    }

    private void showDetails(Device device) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Device Details");
        alert.setHeaderText(device.getName() + " Details");
        String details = getDetailsInformation(device);
        alert.setContentText(details);

        alert.showAndWait();
    }

    private class DataUpdateTask extends TimerTask {
        private Random random = new Random();

        @Override
        public void run() {
            refreshListView();
        }
    }

    @FXML
    private void addDevice(ActionEvent event) {
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(cancelEvent -> handleErrorCancelButton() );

        addDeviceButton.setDisable(true);
        deviceNameTextField = new TextField();
        deviceManufacturerTextField = new TextField();
        deviceVersionTextField = new TextField();
        deviceTypeComboBox = new ComboBox<>();
        deviceTypeComboBox.getItems().addAll("Software", "Hardware");
        deviceTypeComboBox.setOnAction(eventSelectedType -> handleDeviceTypeSelection());

        Label lblName = new Label("Name");
        Label lblManu = new Label("Manufacturer");
        Label lblVersion = new Label("Device Version");
        Label lblDeviceType = new Label("Device Type");

        mainVbox = new VBox(10,cancelButton,lblName, deviceNameTextField, lblManu, deviceManufacturerTextField,lblVersion,deviceVersionTextField, lblDeviceType,deviceTypeComboBox
        );
        mainVbox.setAlignment(Pos.CENTER);
        mainVbox.setPadding(new Insets(50,50,50,50));
        Scene scene = new Scene(mainVbox);
        DeviceMonitorApp.primaryStage.setScene(scene);
    }

    private void addSaveOption()
    {
        saveButton = new Button("Save Item");
        saveButton.setOnAction(cancelEvent -> handleSaveItem() );

        mainVbox.getChildren().add(saveButton);

    }
    private void handleSaveItem()
    {
        String name = deviceNameTextField.getText();
        String manufacturer = deviceManufacturerTextField.getText();
        String version = deviceVersionTextField.getText();
        GeneralDevice generalDevice;
        if(deviceTypeComboBox.equals("Software"))
        {
            generalDevice = new SoftwareDevice(name,manufacturer,version, dateAndTimeTextField.getText());
        }
        else {
            generalDevice = new HardwareDevice(name, manufacturer,locationHardwareTextField.getText(),version,macAddHardwareTextField.getText());
        }

        getToTheMainScene();

        deviceMonitor.addDevice(generalDevice);
        deviceListView.setItems(FXCollections.observableList(deviceMonitor.getDevices()));
        deviceListView.setCellFactory(deviceListView -> new DeviceListCell());
    }

    private void getToTheMainScene()
    {
        try {
            DeviceMonitorApp.ShowMainScene();
        }catch(Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
            Label lblerror = new Label("Error Getting Back :( ");
            VBox vbox = new VBox(10,lblerror);
            Scene scene = new Scene(vbox);
            DeviceMonitorApp.primaryStage.setScene(scene);
        }
    }


    private void handleErrorCancelButton() {
            getToTheMainScene();
    }
    private void handleDeviceTypeSelection() {

        if(saveButton != null)
        {
            mainVbox.getChildren().remove(saveButton);
            saveButton = null;
        }
        String selectedType = deviceTypeComboBox.getValue();
        if ("Software".equals(selectedType)) {
            showSoftwareDetails();
        } else if ("Hardware".equals(selectedType)) {
            showHardwareDetails();
        }
    }

    private void showSoftwareDetails() {

        dateAndTimeTextField = new TextField();
        Label lblLocation = new Label("Installation date and Time");

        if(!hardwareDetailsContainer.getChildren().isEmpty())
        {
            mainVbox.getChildren().remove(hardwareDetailsContainer);
        }

        // Clear existing details if needed
        hardwareDetailsContainer.getChildren().clear();
        softwareDetailsContainer.getChildren().addAll(lblLocation, dateAndTimeTextField);
        mainVbox.getChildren().add(softwareDetailsContainer);
        addSaveOption();
    }

    private void showHardwareDetails() {
        locationHardwareTextField = new TextField();
        macAddHardwareTextField = new TextField();

        Label lblLocation = new Label("Location");
        Label lblMacAdd = new Label("MAC Address");

        // if the current children of the mainVbox is software remove it
        if(!softwareDetailsContainer.getChildren().isEmpty())
        {
            mainVbox.getChildren().remove(softwareDetailsContainer);
        }
        softwareDetailsContainer.getChildren().clear();
        hardwareDetailsContainer.getChildren().addAll(lblLocation, locationHardwareTextField, lblMacAdd, macAddHardwareTextField);
        mainVbox.getChildren().add(hardwareDetailsContainer);
        addSaveOption();
    }

    public void refreshListView() {
        deviceListView.refresh();
    }

    private class DeviceListCell extends ListCell<Device> {
        @Override
        protected void updateItem(Device device, boolean empty) {
            super.updateItem(device, empty);

            if (device == null || empty) {
                setText(null);
                setGraphic(null);
                setStyle(""); // Reset the cell style
            } else {
                setText(device.getName() + " - " + device.getStatus());

                // Set the cell style based on the device status
                if (device.getStatus().equals("Online")) {
                    setStyle("-fx-text-fill: green;");
                } else if (device.getStatus().equals("Offline")) {
                    setStyle("-fx-text-fill: red;");
                } else {
                    setStyle(""); // Reset the cell style
                }
            }
        }
    }

    private String getDetailsInformation(Device device) {
        StringBuilder sb = new StringBuilder();
        sb.append("name: ");
        sb.append(device.getName());
        sb.append("\n");
        sb.append("Manufacturer: ");
        sb.append(device.getManufacturer());
        sb.append("\n");
        sb.append("Version: ");
        sb.append(device.getVersion());
        sb.append("\n");
        sb.append("Device Type: ");
        sb.append(device.getDeviceType());
        sb.append("\n");
        if (device instanceof SoftwareDevice) {
            SoftwareDevice softwareDevice = (SoftwareDevice) device;
            sb.append("Installation Date And Time: ");
            sb.append(softwareDevice.getInstallationDateAndTime());
            sb.append("\n");
        } else if (device instanceof HardwareDevice) {
            HardwareDevice hardwareDevice = (HardwareDevice) device;
            sb.append("Location: ");
            sb.append(hardwareDevice.getLocation());
            sb.append("\n");
            sb.append("MAC Address: ");
            sb.append(hardwareDevice.getMacAddress());
            sb.append("\n");
        }
        return sb.toString();
    }
}
