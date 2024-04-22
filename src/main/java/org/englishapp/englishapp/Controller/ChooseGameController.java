package org.englishapp.englishapp.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseGameController implements Initializable {

    @FXML
    private Button guessWordGameButton;
    @FXML
    private Button enterGameButton;
    private GeneralAppController generalAppController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setGeneralAppController(GeneralAppController generalAppController){
        this.generalAppController = generalAppController;
    }
    public void onGuessGameButtonClick() {
    }

    public void onEnterGameButtonClick() {
         this.generalAppController.loadGuessWordGame();
    }
}
