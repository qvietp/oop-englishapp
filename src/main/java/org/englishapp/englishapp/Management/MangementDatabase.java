package org.englishapp.englishapp.Management;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.englishapp.englishapp.CustomObject.Word;

public class MangementDatabase {

    private final String PATH_DATABASE = "jdbc:sqlite:src/main/resources/org/englishapp/englishapp/Database/dict_hh.db";

    private Connection sqlConnection;

    private final List<Word> searchResultList;

    public MangementDatabase() {
        this.searchResultList = new ArrayList<Word>();
        try {
            this.sqlConnection = DriverManager.getConnection(PATH_DATABASE);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<Word> getSearchResultList() {
        return this.searchResultList;
    }

    public Connection getSqlConnection() {
        return sqlConnection;
    }

    public Word findWord(String wordType) {
        String sqlQuery = "SELECT html FROM av WHERE word = ? COLLATE NOCASE";
        String htmlText = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = sqlConnection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, wordType);
            ResultSet resultSet = preparedStatement.executeQuery();
            htmlText = resultSet.getString("html");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (htmlText == null) {
            return null;
        }
        return new Word(wordType, htmlText);
    }

    public void findMatchestWord(String wordType){
        this.searchResultList.clear();
        String sqlQuery = "SELECT word, html FROM av WHERE word LIKE ? ORDER BY word ASC LIMIT 5000";
        PreparedStatement preparedStatement;
        try{
            preparedStatement = sqlConnection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,wordType+"%s");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                this.searchResultList.add(new Word(resultSet.getString("word"),resultSet.getString("html")));
            }
        }
        catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    //public Word find
}
