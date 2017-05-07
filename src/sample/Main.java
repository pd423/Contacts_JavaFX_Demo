package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.view.PersonEditDialogController;
import sample.view.PersonOverviewController;

import java.io.IOException;

public class Main extends Application {

    private Stage mPrimaryStage;
    private BorderPane mRootLayout;

    private ObservableList<Person> mPersonList = FXCollections.observableArrayList();

    public Main() {
        mPersonList.add(new Person("Hans", "Muster"));
        mPersonList.add(new Person("Ruth", "Mueller"));
        mPersonList.add(new Person("Heinz", "Kurz"));
        mPersonList.add(new Person("Cornelia", "Meier"));
        mPersonList.add(new Person("Werner", "Meyer"));
        mPersonList.add(new Person("Lydia", "Kunz"));
        mPersonList.add(new Person("Anna", "Best"));
        mPersonList.add(new Person("Stefan", "Meier"));
        mPersonList.add(new Person("Martin", "Mueller"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mPrimaryStage = primaryStage;
        mPrimaryStage.setTitle("Contacts");

        initRootLayout();
        showPersonOverview();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            mRootLayout = loader.load();

            Scene scene = new Scene(mRootLayout);
            mPrimaryStage.setScene(scene);
            mPrimaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = loader.load();

            if (mRootLayout != null) {
                mRootLayout.setCenter(personOverview);
            }

            PersonOverviewController controller = loader.getController();
            if (controller != null) {
                controller.setMain(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the main stage.
     * @return the main stage
     */
    public Stage getPrimaryStage() {
        return mPrimaryStage;
    }

    /**
     * Returns the data as an observable list of Persons.
     * @return An observable list.
     */
    public ObservableList<Person> getPersonList() {
        return mPersonList;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user clocks OK, the changes are saved into the
     * provided person object and true is returned.
     *
     * @param person the person object to be edited
     * @return TRUE if the user clicked OK, FALSE otherwise
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane personEditDialog = loader.load();

            // Create the dialog stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mPrimaryStage);
            Scene scene = new Scene(personEditDialog);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
