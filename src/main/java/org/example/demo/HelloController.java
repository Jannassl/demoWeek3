package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Button saveButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;

    @FXML
    public void initialize() {
        languageComboBox.getSelectionModel().select("English");
        updateLabels(new Locale("en", "EN"));
    }

    @FXML
    private void changeLanguage() {
        String selectedLanguage = languageComboBox.getSelectionModel().getSelectedItem();
        Locale locale;
        switch (selectedLanguage) {
            case "Farsi":
                locale = new Locale("fa", "IR");
                break;
            case "Japanese":
                locale = new Locale("ja", "JP");
                break;
            default:
                locale = new Locale("en", "EN");
        }
        updateLabels(locale);
    }

    private void updateLabels(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        firstNameLabel.setText(bundle.getString("firstname"));
        lastNameLabel.setText(bundle.getString("lastname"));
        emailLabel.setText(bundle.getString("email"));
        saveButton.setText(bundle.getString("save"));
    }

    @FXML
    private void saveData() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String selectedLanguage = languageComboBox.getSelectionModel().getSelectedItem();
        String tableName;

        switch (selectedLanguage) {
            case "Farsi":
                tableName = "employee_fa";
                break;
            case "Japanese":
                tableName = "employee_ja";
                break;
            default:
                tableName = "employee_en";
        }

        DatabaseConnector.saveData(firstName, lastName, email, tableName);
    }
}