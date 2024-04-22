package org.englishapp.englishapp.Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayAgainController implements Initializable {

    private int score;

    public TextArea showResult;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.score = 0;
        if(this.showResult!=null){
            System.out.print("Just a test");
        }
    }

    public void setScore(int score) {
        this.score = score;
        int correctAnswer = (int)(score/10);
        int inCorrectAnswere = 10 - correctAnswer;
        this.showResult.setText(String.format("Your Score: %d\nCorrect Answer: %d\nInCorrect Answer: %d",score,correctAnswer,inCorrectAnswere));
    }

    public void onExitGameButtonClick(){
    }

    public void clickPlayAgain(){

    }
}
