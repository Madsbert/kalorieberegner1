package org.example.kalorieberegner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

//Main application class extending JavaFX Application
public class HelloApplication extends Application {

    //reference to the primary stage
    private static Stage primaryStage;

    /**
     * This method is called after application is launched
     * @param stage the primary stage window for the application
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        switchScene("hello-view.fxml", "Kalorieberegner");
    }

    /**
     * Switches the scene in primary stage to the one defined by the given FXML file
     * @param fxml FXML-File to load the new scene
     * @param title Titel to the stage window
     * @throws IOException if there is an issue loading the initial FXML file
     */
    public static void switchScene(String fxml, String title) throws IOException {
        try {
            //load the specific FXML file
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Parent root = loader.load();
            //create a new scene using the loaded FXML layout
            Scene scene = new Scene(root);
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();//resize stage to fit scene
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Fejl: Kunne ikke indlæse FXML-filen: " + fxml);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}