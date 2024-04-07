package org.englishapp.englishapp.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

import java.io.File;
import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.web.HTMLEditor;
import java.util.ResourceBundle;

public class SearchController implements  Initializable {
    @FXML
    private WebView displaySearchResult;
    @FXML
    private BorderPane newMainBorderPane;
    private final String PATH_DATABASE = "jdbc:sqlite:src/main/resources/org/englishapp/englishapp/Database/dict_hh.db";

    private Connection sqlConnection;
    public void initialize(URL location, ResourceBundle resources){
        try {
            sqlConnection = DriverManager.getConnection(PATH_DATABASE);
        }
        catch (SQLException e){
            System.out.println("got exception\n");
            throw new RuntimeException(e);
        }
        String sqlQuery = "SELECT html FROM av WHERE word = ? COLLATE NOCASE";
        String htmlText = null;
        PreparedStatement preparedStatement;
         try{
             preparedStatement = sqlConnection.prepareStatement(sqlQuery);
             preparedStatement.setString(1,"hello");
             ResultSet resultSet = preparedStatement.executeQuery();
             htmlText = resultSet.getString("html");
         }
         catch (Exception e){
             throw new RuntimeException(e);
         }
         if(htmlText!=null){
             System.out.println(htmlText);
             displaySearchResult.getEngine().loadContent(htmlText,"text/html");
         }
         else {
             System.out.println("Got null value of HTMl");
         }
    }
}
