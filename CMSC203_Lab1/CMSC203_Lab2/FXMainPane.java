
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FXMainPane extends VBox {

    // Student Task 2: Declare buttons
    Button helloButton;
    Button howdyButton;
    Button chineseButton;
    Button clearButton;
    Button exitButton;

    Label feedbackLabel;
    TextField feedbackField;

    HBox buttonBox;
    HBox feedbackBox;

    // Student Task 4: Declare DataManager
    DataManager dataManager;

    public FXMainPane() {

        // Task 2: Create buttons
        helloButton = new Button("Hello");
        howdyButton = new Button("Howdy");
        chineseButton = new Button("Chinese");
        clearButton = new Button("Clear");
        exitButton = new Button("Exit");

        feedbackLabel = new Label("Feedback:");
        feedbackField = new TextField();

        buttonBox = new HBox();
        feedbackBox = new HBox();

        // Task 3: Add components to HBoxes
        buttonBox.getChildren().addAll(
            helloButton, howdyButton, chineseButton,
            clearButton, exitButton
        );

        feedbackBox.getChildren().addAll(
            feedbackLabel, feedbackField
        );

        getChildren().addAll(buttonBox, feedbackBox);

        // Task 4: Create DataManager
        dataManager = new DataManager();

        // Make buttons respond to clicks
        ButtonHandler handler = new ButtonHandler();

        helloButton.setOnAction(handler);
        howdyButton.setOnAction(handler);
        chineseButton.setOnAction(handler);
        clearButton.setOnAction(handler);
        exitButton.setOnAction(handler);

        // Center the components
        buttonBox.setAlignment(Pos.CENTER);
        feedbackBox.setAlignment(Pos.CENTER);

        // Add spacing
        buttonBox.setSpacing(10);
        feedbackBox.setSpacing(10);
        setSpacing(20);
        setAlignment(Pos.CENTER);

        // Add margins
        Insets margin = new Insets(10);

        HBox.setMargin(helloButton, margin);
        HBox.setMargin(howdyButton, margin);
        HBox.setMargin(chineseButton, margin);
        HBox.setMargin(clearButton, margin);
        HBox.setMargin(exitButton, margin);
    }

    // Task 4: Handle button clicks
    private class ButtonHandler
            implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            Object target = event.getTarget();

            if (target == helloButton) {
                feedbackField.setText(
                    dataManager.getHello()
                );
            }
            else if (target == howdyButton) {
                feedbackField.setText(
                    dataManager.getHowdy()
                );
            }
            else if (target == chineseButton) {
                feedbackField.setText(
                    dataManager.getChinese()
                );
            }
            else if (target == clearButton) {
                feedbackField.setText("");
            }
            else if (target == exitButton) {
                Platform.exit();
                System.exit(0);
            }
        }
    }
}
