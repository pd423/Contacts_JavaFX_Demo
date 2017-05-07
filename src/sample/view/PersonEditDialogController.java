package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Person;
import sample.util.DateUtil;
import sample.util.TextUtil;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class PersonEditDialogController {

    @FXML
    private TextField mFirstNameField;
    @FXML
    private TextField mLastNameField;
    @FXML
    private TextField mStreetField;
    @FXML
    private TextField mPostalCodeField;
    @FXML
    private TextField mCityField;
    @FXML
    private TextField mBirthdayField;

    private Stage mDialogStage;
    private Person mPerson;
    private boolean mIsOkClicked = false;

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Set the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        mDialogStage = dialogStage;
    }

    /**
     * Set the person to be edited in the dialog.
     *
     * @param person
     */
    public void setPerson(Person person) {
        mPerson = person;

        mFirstNameField.setText(mPerson.getFirstName());
        mLastNameField.setText(mPerson.getLastName());
        mStreetField.setText(mPerson.getStreet());
        mPostalCodeField.setText(Integer.toString(mPerson.getPostalCode()));
        mCityField.setText(mPerson.getCity());
        mBirthdayField.setText(DateUtil.format(mPerson.getBirthday()));
    }

    /**
     * Returns TRUE if the user clicked OK, FALSE otherwise.
     *
     * @return TRUE or FALSE
     */
    public boolean isOkClicked() {
        return mIsOkClicked;
    }

    /**
     * Called when the user clicks OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            mPerson.setFirstName(mFirstNameField.getText());
            mPerson.setLastName(mLastNameField.getText());
            mPerson.setStreet(mStreetField.getText());
            mPerson.setPostalCode(Integer.parseInt(mPostalCodeField.getText()));
            mPerson.setCity(mCityField.getText());
            mPerson.setBirthday(DateUtil.parse(mBirthdayField.getText()));

            mIsOkClicked = true;
            mDialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        mDialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return TRUE if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (TextUtil.isEmpty(mFirstNameField.getText())) {
            errorMessage += "Not valid first name!\n";
        }
        if (TextUtil.isEmpty(mLastNameField.getText())) {
            errorMessage += "Not valid last name!\n";
        }
        if (TextUtil.isEmpty(mStreetField.getText())) {
            errorMessage += "Not valid street!\n";
        }
        if (TextUtil.isEmpty(mPostalCodeField.getText())) {
            errorMessage += "Not valid postal code!\n";
        } else {
            // Try to parse the postal code into an int.
            try {
                Integer.parseInt(mPostalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Not valid postal code (must be an integer)!\n";
            }
        }
        if (TextUtil.isEmpty(mCityField.getText())) {
            errorMessage += "Not valid city!\n";
        }
        if (TextUtil.isEmpty(mBirthdayField.getText())) {
            errorMessage += "Not valid birthday!\n";
        } else {
            if (!DateUtil.validDate(mBirthdayField.getText())) {
                errorMessage += "Not valid birthday. Use the format yyyy/MM/dd!\n";
            }
        }

        if (TextUtil.isEmpty(errorMessage)) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
