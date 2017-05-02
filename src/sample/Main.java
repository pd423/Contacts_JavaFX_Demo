package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage mPrimaryStage;
    private BorderPane mRootLayout;

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

    public static void main(String[] args) {
        launch(args);
    }
}
