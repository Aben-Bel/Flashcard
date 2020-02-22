package app.controller;

import component.DeckComponent;
import component.dialogBoxComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import app.Main;
import app.model.Deck;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class FlashcardViewController extends BaseController implements Initializable {

    public static final String URL_FXML = "/fxml/decks.fxml";

    @FXML
    FlowPane flowpaneDeckList;

    @FXML Button btnCreate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Deck deck : Main.flashcard.getDeckList()) {
            addDeck(deck);
        }
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(Main.getStage());

        btnCreate.setOnAction(e -> {
            dialogBoxComponent db = new dialogBoxComponent("");

            db.setOnAction(eve -> {
                String sourceId = ((Control) eve.getSource()).getId();
                if("btnCreate".equalsIgnoreCase(sourceId)){
                    Deck newDeck = new Deck(db.txtTitle.getText());
                    Main.flashcard.addDeck(newDeck);
                    this.addDeck(newDeck);
                }
                dialog.close();
            });
            Scene dialogScene = new Scene(db, 360, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        });
    }

    public void addDeck(Deck deck){

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(Main.getStage());

        DeckComponent deckC = new DeckComponent();

        deckC.setText(deck.getTitle()); // set component's title
        deckC.setOnEdit(e -> {
            String title = deck.getTitle();

            dialogBoxComponent db = new dialogBoxComponent(title);
            db.btn.setText("Edit");
            db.setOnAction(eve -> {
                String sourceId = ((Control) eve.getSource()).getId();
                if("btnCreate".equalsIgnoreCase(sourceId)) {
                    Deck foundDeck = Main.flashcard.getDeck(title);
                    if(foundDeck != null){
                        foundDeck.setTitle(db.txtTitle.getText());
                        deckC.setText(db.txtTitle.getText());
                    }
                }
                dialog.close();
            });
            Scene dialogScene = new Scene(db, 360, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        });
        deckC.setOnRemove(e -> {
            Main.flashcard.remove(deck);
            Main.getNavigation().load(URL_FXML).Show();
        });

        // add mouse click event on each deck component to navigate card view
        deckC.setOnAction(e -> {
            CardsViewController cards = (CardsViewController) Main.getNavigation().load(CardsViewController.URL_FXML);
            cards.setParameterFromFlashCardView(deck.getTitle()); // pass deck to cards view
            cards.Show();
        });
        // update flowpane with decks
        flowpaneDeckList.getChildren().add(deckC);
    }
}
