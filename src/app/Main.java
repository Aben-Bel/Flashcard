package app;

import app.model.Flashcard;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Window;
import app.controller.Navigation;
import app.controller.FlashcardViewController;

public class Main extends Application {

    private static Navigation navigation;
    private static Window ps;

    public static Navigation getNavigation()
    {
        return navigation;
    }

    public static Window getStage(){ return ps; }


    public static Flashcard flashcard = new Flashcard();

    @Override
    public void start(Stage primaryStage){

        navigation = new Navigation(primaryStage);
        ps = primaryStage;

        primaryStage.setResizable(false);
        primaryStage.setTitle("Flash card");
        primaryStage.show();

        //navigate to first view
        Main.getNavigation().load(FlashcardViewController.URL_FXML).Show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}