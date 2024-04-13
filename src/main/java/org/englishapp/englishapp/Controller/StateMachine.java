package org.englishapp.englishapp.Controller;

public class StateMachine {

    static String InitAndSeacrch = "InitAndSearch";

    static String GoogleTranslate = "GoogleTranslate";

    static String AddNewWord = "AddNewWord";

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
}
