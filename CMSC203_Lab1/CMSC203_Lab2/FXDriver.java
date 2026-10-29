
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class FXDriver extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // Student Task 1: Create FXMainPane
        FXMainPane root = new FXMainPane();

        // Create the scene
        stage.setScene(new Scene(root, 400, 300));

        // Display the window
        stage.setTitle("Lab 2 GUI");
        stage.show();
    }
}