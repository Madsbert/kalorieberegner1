package org.example.kalorieberegner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;

public class DailyController {

    @FXML
    private ChoiceBox <String> activityLevel;

    @FXML
    private TextField weight;

    @FXML
    private TextField age;

    @FXML
    private TextField height;

    @FXML
    private CheckBox male;

    @FXML
    private CheckBox female;

    @FXML
    private Label result;

    @FXML
    private Button calculate;

    @FXML
    public void initialize() {
        // Initialize the ChoiceBox with items
        activityLevel.getItems().addAll(
                "Stillesidende arbejde og ingen eller begrænset fysisk aktivitet i hverdagen",
                "Roligt arbejde og træner lidt 1-3gange i ugen",
                "Moderat aktiv person som træner 3-5 gange i ugen eller stående eller gående arbejde",
                "Meget aktiv som træner hårdt 6-7 gange i ugen eller tungt fysisk arbejde");

        // Set a default value
        activityLevel.setValue("Stillesidende arbejde og ingen eller begrænset fysisk aktivitet i hverdagen");
    }

    //method to calculate daily calories needed
    @FXML
    protected void onCalculate() {
        try {
            //validating the user has filled everything or overfilled the checkboxes
            if(age.getText().isEmpty()||weight.getText().isEmpty()||height.getText().isEmpty()
                    ||!male.isSelected() && !female.isSelected()||male.isSelected() && female.isSelected()) {

                result.setText("Tjek venligst om alle felter er udfyldt korrekt");
            }

            //variables
            double weightOfPerson = Double.parseDouble(weight.getText());
            int ageOfPerson = Integer.parseInt(age.getText());
            double heightOfPerson = Double.parseDouble(height.getText());

            //Assigning a PAL Value
            double palValue=0;
            switch (activityLevel.getValue()) {
                case "Stillesidende arbejde og ingen eller begrænset fysisk aktivitet i hverdagen":
                    palValue = 1.4;
                    break;
                case "Roligt arbejde og træner lidt 1-3gange i ugen":
                    palValue = 1.65;
                    break;
                case "Moderat aktiv person som træner 3-5 gange i ugen eller stående eller gående arbejde":
                    palValue = 1.85;
                    break;
                case "Meget aktiv som træner hårdt 6-7 gange i ugen eller tungt fysisk arbejde":
                    palValue = 2.1;
                    break;
            }

            //calculating the daily Calories needed based on whether the user chekcs male or female
            double dailyCaloriesNeeded=0;
            if(male.isSelected()){
                dailyCaloriesNeeded=(260+(9.65*weightOfPerson)+(573*heightOfPerson)-(5.08*ageOfPerson))*palValue;
            }
            else if(female.isSelected()){
                dailyCaloriesNeeded=(43+(7.38*weightOfPerson)+(607*heightOfPerson)-(2.31*ageOfPerson))*palValue;
            }
            else{
                result.setText("Udfyld manglende felter");
                return;
            }
            result.setText(String.format("Dit daglige energibehov er: %.0f kalorier", dailyCaloriesNeeded));


        } catch (NumberFormatException e) {
            result.setText("Ugyldigt input. Indtast venligst kun tal");
        }

    }

}

