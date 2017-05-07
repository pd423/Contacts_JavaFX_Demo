package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.Person;
import sample.util.DateUtil;

public class PersonOverviewController {

    @FXML
    private TableView<Person> mPersonTable;
    @FXML
    private TableColumn<Person, String> mFirstNameColumn;
    @FXML
    private TableColumn<Person, String> mLastNameColumn;

    @FXML
    private Label mFirstNameLabel;
    @FXML
    private Label mLastNameLabel;
    @FXML
    private Label mStreetLabel;
    @FXML
    private Label mPostalCodeLabel;
    @FXML
    private Label mCityLabel;
    @FXML
    private Label mBirthdayLabel;

    // Reference to the main application.
    private Main mMain;

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        mFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        mLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        mPersonTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showPersonDetails(newValue)));
    }

    public void setMain(Main main) {
        mMain = main;

        // Add observable list data to the table.
        mPersonTable.setItems(mMain.getPersonList());
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            mFirstNameLabel.setText(person.getFirstName());
            mLastNameLabel.setText(person.getLastName());
            mStreetLabel.setText(person.getStreet());
            mPostalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            mCityLabel.setText(person.getCity());
            mBirthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person is null, remove all the text.
            mFirstNameLabel.setText("");
            mLastNameLabel.setText("");
            mStreetLabel.setText("");
            mPostalCodeLabel.setText("");
            mCityLabel.setText("");
            mBirthdayLabel.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = mPersonTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            mPersonTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selected");
            alert.setHeaderText("No Person Selected.");
            alert.setContentText("Please select a person in the person list.");
            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tmpPerson = new Person();
        boolean isOkClicked = mMain.showPersonEditDialog(tmpPerson);
        if (isOkClicked) {
            mMain.getPersonList().add(tmpPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = mPersonTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean isOkClicked = mMain.showPersonEditDialog(selectedPerson);
            if (isOkClicked) {
                showPersonDetails(selectedPerson);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the person list.");
            alert.showAndWait();
        }
    }

}
