package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.Person;

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
        mFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        mLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
    }

    public void setMain(Main main) {
        mMain = main;

        // Add observable list data to the table.
        mPersonTable.setItems(mMain.getPersonList());
    }

}
