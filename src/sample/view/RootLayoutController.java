package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import sample.Main;

import java.io.File;

/**
 * The controller for the root layout. The root layout provides the basic application layout containing a menu bar and
 * space where other JavaFX elements can be placed.
 *
 * @author Marco Jakob
 */
public class RootLayoutController {

    // Reference to the main application
    private Main mMain;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param main
     */
    public void setMain(Main main) {
        mMain = main;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNiew() {
        mMain.getPersonList().clear();
        mMain.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser chooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        chooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = chooser.showOpenDialog(mMain.getPrimaryStage());

        if (file != null) {
            mMain.loadPersonDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open.
     * If there is no open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File personFile = mMain.getPersonFilePath();
        if (personFile != null) {
            mMain.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser chooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        chooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = chooser.showSaveDialog(mMain.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension.
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mMain.savePersonDataToFile(file);
        }
    }

    /**
     * Opens an aboud dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Marco Jakob\nWebsite: http://code.makery.ch");

        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void handleShowBirthdayStatistics() {
        mMain.showBirthdayStatistics();
    }
}
