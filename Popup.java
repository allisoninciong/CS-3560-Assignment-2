package v4;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Popup      // visitor pattern
{
    public static void start(String title, String output)
    {
        // declare variable
        Label label = new Label();
        label.setText(output);

        // format layout
        VBox vbox = new VBox(10, label);
        vbox.setAlignment(Pos.CENTER);

        // create stage and scene
        Stage popup = new Stage();
        Scene scene = new Scene(vbox, 250, 200);
        popup.setTitle(title);
        popup.setScene(scene);
        popup.showAndWait();
    }
}
