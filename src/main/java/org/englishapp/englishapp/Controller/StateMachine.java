package org.englishapp.englishapp.Controller;

public class StateMachine {

    static final String InitAndSeacrch = "InitAndSearch";

    static final String GoogleTranslate = "GoogleTranslate";

    static final String AddNewWord = "AddNewWord";

    static final String Game = "Game";
    static final String ChooseGame = "ChooseGame";

    static final String PlayAgain = "PlayAgain";
    static String state = "";

    public static void setInitAndSeacrch() {
        state = InitAndSeacrch;
    }

    public static void setGoogleTranslate() {
        state = GoogleTranslate;
    }

    public static void setAddNewWord() {
        state = AddNewWord;
    }

    public static void setGame() {
        state = Game;
    }

    public static void setChooseGame() {
        state = ChooseGame;
    }

    public static void setPlayAgain(){
         state = PlayAgain;
    }

}
