package app.controller;

import component.dialogBoxComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import app.Main;
import app.model.Card;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CardsViewController extends BaseController implements Initializable {
    public static final String URL_FXML = "/fxml/cards.fxml";
    @FXML
    Button btnBack;
    @FXML
    Button btnCreate;
    @FXML
    Button btnRemove;
    @FXML
    Button btnEdit;
    @FXML
    Button btnPrevious;
    @FXML
    Button btnFlip;
    @FXML
    Button btnNext;

    @FXML
    TextArea cardTextArea;

    private Boolean flipped = false;
    private int atIndex = 0;
    private List cardList;
    private String parameterFromFlashCard;

    public String getParameterFromFlashCard() {
        return parameterFromFlashCard;
    }

    public void setParameterFromFlashCardView(String parameterFromFlashCard) {
        this.parameterFromFlashCard = parameterFromFlashCard;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(Main.getStage());

        btnBack.setOnAction(e -> {
            Main.getNavigation().load(FlashcardViewController.URL_FXML).Show();
        });

        btnCreate.setOnAction(e -> {
            dialogBoxComponent db = new dialogBoxComponent("", "");
            db.setOnAction(eve -> {
                String sourceId = ((Control) eve.getSource()).getId();
                if("btnCreate".equalsIgnoreCase(sourceId)){
                    cardList.add(new Card(db.txtKey.getText(), db.txtDesc.getText()));
                      updateView();
                }
                dialog.close();
            });
            Scene dialogScene = new Scene(db, 360, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        });

        btnRemove.setOnAction(e -> {
            // TODO: a popup to warn user before they remove a card
            cardList.remove(atIndex);
            this.btnNext.fire();
        });

        btnEdit.setOnAction(e -> {
            String key = ((Card) cardList.get(atIndex)).getKey();
            String desc = ((Card) cardList.get(atIndex)).getDescription();

            dialogBoxComponent db = new dialogBoxComponent(key, desc);
            db.btn.setText("Edit");
            db.setOnAction(eve -> {
                String sourceId = ((Control) eve.getSource()).getId();
                if("btnCreate".equalsIgnoreCase(sourceId)) {
                    ((Card) cardList.get(atIndex)).setDescription(db.txtDesc.getText());
                    ((Card) cardList.get(atIndex)).setKey(db.txtKey.getText());
                }
                dialog.close();
            });
            Scene dialogScene = new Scene(db, 360, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        });

        btnPrevious.setOnAction(e -> {
            flipped = false;
            if (atIndex != 0) {
                atIndex--;
                updateView();
            }
        });

        btnFlip.setOnAction(e -> {
            if (flipped) {
                cardTextArea.setText(((Card) cardList.get(atIndex)).getKey());
                cardTextArea.setDisable(true);
                cardTextArea.setStyle("-fx-opacity: 1.0;");
                flipped = false;
            } else {
                cardTextArea.setText(((Card) cardList.get(atIndex)).getDescription());
                cardTextArea.setDisable(true);
                cardTextArea.setStyle("-fx-opacity: 1.0;");
                flipped = true;
            }
        });

        btnNext.setOnAction(e -> {
            flipped = false;
            if (atIndex < cardList.size() - 1) {
                atIndex++;
                cardTextArea.setText(((Card) cardList.get(atIndex)).getKey());
                cardTextArea.setDisable(true);
                cardTextArea.setStyle("-fx-opacity: 1.0;");
            }
        });
    }

    @Override
    public void PreShowing() {
        cardList = Main.flashcard.getDeck(parameterFromFlashCard).getCardList();
        if(cardList.size() != 0){
            updateView();
        }
    }

    public void updateView(){
        cardTextArea.setText(((Card) cardList.get(atIndex)).getKey());
        cardTextArea.setDisable(true);
        cardTextArea.setStyle("-fx-opacity: 1.0;");
    }
}
