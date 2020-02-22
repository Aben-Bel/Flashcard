package app.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Navigation {

    private final Stage stage;
    private final Scene scene;

    private List<Controller> controllers = new ArrayList<>();


    public Navigation(Stage stage)
    {
        this.stage = stage;
        scene = new Scene(new Pane(), 700, 500);
        stage.setScene(scene);
    }

    public Controller load(String sUrl)
    {
        try {
            //loads the fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sUrl));
            Node root = fxmlLoader.load();

            Controller controller = fxmlLoader.getController();
            controller.setView(root);

            return controller;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void Show(Controller controller)
    {
        try {
            scene.setRoot((Parent) controller.getView());
            controllers.add(controller);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
