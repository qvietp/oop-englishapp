package org.englishapp.englishapp.Controller;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javazoom.jl.decoder.JavaLayerException;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.englishapp.englishapp.utils.TextToSpeech;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Translate;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import javafx.fxml.Initializable;
import org.englishapp.englishapp.HelloApplication;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import org.englishapp.englishapp.CustomObject.Word;
import org.englishapp.englishapp.Management.MangementDatabase;
import org.englishapp.englishapp.utils.GoogleVoiceAPI;

public class GeneralAppController implements Initializable {

    @FXML
    private Label welcomeText;

    @FXML
    private Button searchTab;

    @FXML
    private Button googleTranslateTab;

    @FXML
    private Button favoritesTab;

    @FXML
    private Button historyTab;

    @FXML
    private Button addToDictTab;

    @FXML
    private Button gameTab;

    @FXML
    private Pane LeftPaneTab;

    @FXML
    private BorderPane mainBorderPane;


    @FXML
    private ImageView HideMenuImage;

    @FXML
    private BorderPane SearchPane;

    @FXML
    private TextField searchBar;

    @FXML
    private ListView showMatchestWord;

    private TranslateTransition LeftPaneTransition;
    @FXML
    private Button ShowMenu;

    @FXML
    private WebView displaySearchResult;

    @FXML
    private ImageView UkAudio;
    private MangementDatabase handleManagement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.handleManagement = new MangementDatabase();
        this.LeftPaneTransition = new TranslateTransition(Duration.millis(300), LeftPaneTab);
        this.LeftPaneTransition.setFromX(0);
        this.LeftPaneTransition.setToX(-200);
    }

    public void ClearStatusButton() {
        searchTab.getStyleClass().removeAll("active");
        googleTranslateTab.getStyleClass().removeAll("active");
        favoritesTab.getStyleClass().removeAll("active");
        historyTab.getStyleClass().removeAll("active");
        addToDictTab.getStyleClass().removeAll("active");
        gameTab.getStyleClass().removeAll("active");
    }

    public void ChangeMainBorderPane(BorderPane newMainBorderPane) {
        this.mainBorderPane.setTop(newMainBorderPane.getTop());
        this.mainBorderPane.setLeft(newMainBorderPane.getLeft());
        this.mainBorderPane.setCenter(newMainBorderPane.getCenter());
        this.mainBorderPane.setRight(newMainBorderPane.getRight());
        this.mainBorderPane.setBottom(newMainBorderPane.getBottom());
    }

    public void HideMenu() {
        if (LeftPaneTab.getTranslateX() == 0) {
            LeftPaneTransition.playFromStart();
            Image image = new Image("file:D:\\OOP_LearningEnglighApp\\EnglishApp\\src\\main\\resources\\org\\englishapp\\englishapp\\icons\\tabs\\enter.png");
            HideMenuImage.setImage(image);
        } else {
            LeftPaneTransition.setRate(-1);
            LeftPaneTransition.play();
            HideMenuImage.setImage(new Image("file:src/main/resources/org/englishapp/englishapp/icons/tabs/exit.png"));
        }
    }

    public void disableListView() {
        this.showMatchestWord.setVisible(false);
    }

    public void checkIfEnterTyped(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            try{
                this.loadSearcher();
            }
            catch (IOException exception){
                System.out.print("Error with check Enter typed func\n");
            }
        }
    }

    public void findMatchestWord() {
        this.showMatchestWord.getItems().clear();
        this.showMatchestWord.setVisible(true);
        String wordType = null;
        wordType = this.searchBar.getText();
        this.handleManagement.findMatchestWord(wordType);
        String[] result = new String[5000];
        for (int i = 0; i < this.handleManagement.getSearchResultList().size(); i++) {
            result[i] = this.handleManagement.getSearchResultList().get(i).getWordType();
        }
        this.showMatchestWord.getItems().addAll(result);
    }

    public void handleChosenFromMenu() {
        String wordType = (String) this.showMatchestWord.getSelectionModel().getSelectedItem();
        Word resultWord = this.handleManagement.findWord(wordType);
        if (resultWord == null) {
            displaySearchResult.getEngine().loadContent("Could not find that word!", "text/html");
        } else {
            displaySearchResult.getEngine().loadContent(resultWord.getHtmlType(), "text/html");
        }
    }

    public void loadSearcher() throws IOException {
        String wordType = searchBar.getText();
        Word resultWord = this.handleManagement.findWord(wordType);
        if (resultWord == null) {
            displaySearchResult.getEngine().loadContent("Could not find that word!", "text/html");
        } else {
            displaySearchResult.getEngine().loadContent(resultWord.getHtmlType(), "text/html");
        }
    }

    public void handleClickOnUkAudio() {

    }

    public void handleClickOnUsAudio() {
        String wordType = this.searchBar.getText();
        try {
            GoogleVoiceAPI.getInstance().playAudio(GoogleVoiceAPI.getInstance().getAudio(wordType,
                    "en-US"));
        } catch (IOException | JavaLayerException e) {
            System.err.println("Failed to play Audio from Google, fallback to FreeTTS");
            TextToSpeech.speak(wordType);
        }
    }

    public void handleClickOnSave() {

    }

    public void handleClickOnDelete() {

    }

    public void loadGoogleTranslate() {
        this.ClearStatusButton();
        this.googleTranslateTab.getStyleClass().add("active");
        BorderPane newBorderPane;
        try {
            newBorderPane = FXMLLoader.load(HelloApplication.class.getResource("googletranslate.fxml"));
        }
        catch (IOException exception){
            throw new RuntimeException();
        }
        this.ChangeMainBorderPane(newBorderPane);
    }

    public void loadFavorites() {

    }

    public void loadHistory() {

    }

    public void loadAddToDict() {

    }

    public void loadGame() {

    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}