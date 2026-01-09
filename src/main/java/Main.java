import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.AnalogClock;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane(new AnalogClock());
        Scene scene = new Scene(root, 200, 200);
        stage.setTitle("Analog Clock Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
