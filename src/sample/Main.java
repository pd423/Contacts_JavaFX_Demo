package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
}
