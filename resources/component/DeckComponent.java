package component;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


public class DeckComponent extends HBox {
    @FXML private Label title;

    public DeckComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/deckComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                onActionProperty().get().handle(event);
            }
        });


        Button btnEdit = (Button) fxmlLoader.getNamespace().get("btnEdit");
        Button btnRemove = (Button) fxmlLoader.getNamespace().get("btnRemove");

        btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onEditProperty().get().handle(mouseEvent);
            }
        });

        btnRemove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onRemoveProperty().get().handle(mouseEvent);
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


    private ObjectProperty<EventHandler<MouseEvent>> propertyOnEdit = new SimpleObjectProperty<>();

    public final ObjectProperty<EventHandler<MouseEvent>> onEditProperty() {
        return propertyOnEdit;
    }

    public final void setOnEdit(EventHandler<MouseEvent> handler) {
        propertyOnEdit.set(handler);
    }

    public final EventHandler<MouseEvent> getOnEdit(){
        return propertyOnEdit.get();
    }

    private ObjectProperty<EventHandler<MouseEvent>> propertyOnRemove = new SimpleObjectProperty<>();

    public final ObjectProperty<EventHandler<MouseEvent>> onRemoveProperty() {
        return propertyOnRemove;
    }

    public final void setOnRemove(EventHandler<MouseEvent> handler) {
        propertyOnRemove.set(handler);
    }

    public final EventHandler<MouseEvent> getOnRemove(){
        return propertyOnRemove.get();
    }


    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return title.textProperty();
    }
}