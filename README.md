## Device Monitor Application - GitHub README
---
I did 2-5 sections and solved one bug related to the add item button which didn’t work.
Unfortunately I couldn't do the authority sections because of lack of time

### Explanation about the sections:
### 2. Device Removal
Done by deleting the object from the list of device monitor and updating the device list view.

### 3. Device Types
I created two classes “SoftwareDevice” and “HardwareDevice” that represents Device and inherits from GeneralDevice. New kinds of devices will be built by adding one class that represents them and inherits from generalDevice. 
The application supports the two types of devices because those devices are also represent GeneralDevice so a few changes were needed.

### 4. Adding Devices
The user can choose the type of device when adding new one by choosing the desired option in the combox.
after choosing the desired option depending on the selected type, specific fields for that device type will be displayed.

### 5. Device Details
The option to show the device details will be attached to “remove” option in the MenuItem, after choosing the details option an alert window will be opened and show the device details according to its type. 

### Future Improvements
while the Application is functional, there are areas that could be improved with additional time and effort:
-	Add validation to the user input
- Handling better errors.
-	Use more constants instead of creating such as strings used for UI labels or button labels, into separate static final fields to make them easier to manage and update.
-	Would change the logic in the showMainScene() to get back to the DeviceMonitorApp class.
-	I would pay attention to separation of concerns and modularization theme, 
considering breaking down the code into smaller classes or modules to improve separation of concerns. For instance, to separate classes for UI management, device handling, and dialog creation it also allows reusability.
-	Another important theme is the dependency injection - instead of creating instances of UI elements within the controller methods, I would use dependency injection to pass these elements as parameters to the methods. This can make your methods more testable and decoupled.
-	Handling the problem that happens after saving new item by checking if there is something to improve in the code so that the devicemonitor data will be saved when getting back to the DeviceMonitorApp class
-	Change the installation date and time text field to datepicker and the SoftwareDevice class data member respectively
-	Changing the location text field to combo box using specified locations
-	Implement Singleton for the controller because one instance is enough.


## Application Structure

The Device Monitor App consists of the following classes:

- `DeviceMonitorApp`: The main application class responsible for launching the JavaFX application.
- `DeviceMonitorController`: The controller class that manages the UI and interactions of the app.
- `Device`: The base class representing a device with its name, manufacturer, device type, and version.
- `GeneralDevice`: A subclass of `Device` that includes a constructor for creating devices.
- `HardwareDevice`: A subclass of `GeneralDevice` representing hardware devices with additional location and MAC address details.
- `SoftwareDevice`: A subclass of `GeneralDevice` representing software devices with an installation date and time.



