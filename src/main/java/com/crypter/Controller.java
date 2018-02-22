package com.crypter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField passwordText;

    @FXML
    private TextField rawValueText;

    @FXML
    private TextField encryptedResultText;

    @FXML
    private TextField encryptedValueText;

    @FXML
    private TextField decryptedResultText;

    private StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        passwordText.textProperty().addListener((observable, oldValue, newValue) -> {
            stringEncryptor = new StandardPBEStringEncryptor();

            if (newValue!=null && !newValue.equals("")) {
                stringEncryptor.setPassword(newValue);
                refreshValue();
            }
        });

        rawValueText.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshValue();
        });

        stringEncryptor.setPassword("-");


        encryptedValueText.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshDecryptedValue();
        });


    }

    private void refreshDecryptedValue() {
        try {
            decryptedResultText.setText(stringEncryptor.decrypt(encryptedValueText.getText()));
        } catch (EncryptionOperationNotPossibleException e) {
            decryptedResultText.setText("");
        }
    }

    private void refreshValue() {
        encryptedResultText.setText(stringEncryptor.encrypt(rawValueText.getText()));
    }
}
