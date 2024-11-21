package org.example.kalorieberegner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class HelloController {

    @FXML
    private Button dailyEnergy;

    @FXML
    private ChoiceBox<String> activity;

    @FXML
    private TextField weight;

    @FXML
    private TextField lengthOfActivity;

    @FXML
    private Label result;

    @FXML
    private Button calculate;

    // making choices in the choicbox for the user
    @FXML
    public void initialize() {
        // Initialize the ChoiceBox with items
        activity.getItems().addAll( "Cykling lav puls", "Cykling medium puls", "Cykling høj puls", "Fodbold",
                "Langrend lav puls", "Langrend moderat puls", "Langrend høj puls",
                "Løb langsomt tempo", "Løb tempo: 5.33min/km", "Løb tempo: 3.39min/km", "Styrketræning");

        // Set a default value
        activity.setValue("Cykling lav puls");
    }

    //making a method to show the user how many calories burned in a training session
    @FXML
    protected void clickOnCalculate() {
        // the try is catching if the user as a enters a faulty input
        try {
            //Validating the user as filled all fields
            if(weight.getText().isEmpty() || lengthOfActivity.getText().isEmpty()) {
                result.setText("Udfyld alle felter");
                return;
            }

            //variables
            int personWeight = Integer.parseInt(weight.getText());
            double activityLength = Double.parseDouble(lengthOfActivity.getText());

            //assigning the met values
            double metValue = 0;
            switch (activity.getValue()) {
                case "Langrend lav puls":
                    metValue = 6.8;
                    break;
                case "Langrend moderat puls":
                    metValue = 9.0;
                    break;
                case "Langrend høj puls":
                    metValue = 12.5;
                    break;
                case "Løb langsomt tempo":
                    metValue = 7.0;
                    break;
                case "Fodbold":
                    metValue = 10;
                    break;
                case "Cykling medium puls":
                    metValue = 8;
                    break;
                case "Cykling lav puls":
                    metValue = 6.8;
                    break;
                case "Cykling høj puls":
                    metValue = 12;
                    break;
                case "Løb tempo: 5.33min/km":
                    metValue = 11.0;
                    break;
                case "Løb tempo: 3.39min/km":
                    metValue = 16.0;
                    break;
                case "Styrketræning":
                    metValue = 5.0;
                    break;
            }
            //calculation of the calories burned
            double caloriesBurned = (activityLength / 60) * personWeight * metValue;//dividing the activity length by 60 to get the value in hours

            //showing the result
            result.setText(String.format("Du har forbrændt: %.0f Kalorier", caloriesBurned));

        } catch (NumberFormatException e) {
            result.setText("Ugyldigt input. indtast kun heletal");
        }
    }

    //method to open a new scene
    @FXML
    protected void clickOnDailyEnergy() {
        try {
            HelloApplication.switchScene("SecondaryScene.fxml", "Dagligt Energiforbrug");

        } catch (Exception e) {
                e.printStackTrace();
        }
    }
}