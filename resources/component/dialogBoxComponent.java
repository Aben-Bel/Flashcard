package component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class dialogBoxComponent extends VBox {

    public TextField txtKey;
    public TextField txtTitle;
    public TextArea txtDesc;

    public Button btn;

    public dialogBoxComponent(String title){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/dialogBoxForDeck.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        txtTitle = (TextField) fxmlLoader.getNamespace().get("txtTitle");
        txtDesc = null;
        txtKey = null;
        btn = (Button) fxmlLoader.getNamespace().get("btnCreate");

        Button btnCreate = (Button) fxmlLoader.getNamespace().get("btnCreate");
        btnCreate.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                onActionProperty().get().handle(event);
            }
        });

        Button btnCancel = (Button) fxmlLoader.getNamespace().get("btnCancel");
        btnCancel.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                onActionProperty().get().handle(event);
            }
        });
    }

    public dialogBoxComponent(String key, String description){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/dialogBoxForCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        txtTitle = null;
        txtDesc = (TextArea) fxmlLoader.getNamespace().get("txtDesc");
        txtKey = (TextField) fxmlLoader.getNamespace().get("txtKey");
        btn = (Button) fxmlLoader.getNamespace().get("btnCreate");

        txtKey.setText(key);
        txtDesc.setText(description);

        Button btnCreate = (Button) fxmlLoader.getNamespace().get("btnCreate");
        btnCreate.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                onActionProperty().get().handle(event);
            }
        });

        Button btnCancel = (Button) fxmlLoader.getNamespace().get("btnCancel");
        btnCancel.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                onActionProperty().get().handle(event);
            }
        });
    }

    private ObjectProperty<EventHandler<MouseEvent>> propertyOnAction = new SimpleObjectProperty<EventHandler<MouseEvent>>();

    public final ObjectProperty<EventHandler<MouseEvent>> onActionProperty() {
        return propertyOnAction;
    }

    public final void setOnAction(EventHandler<MouseEvent> handler) {
        propertyOnAction.set(handler);
    }

    public final EventHandler<MouseEvent> getOnAction() {
        return propertyOnAction.get();
    }
}
