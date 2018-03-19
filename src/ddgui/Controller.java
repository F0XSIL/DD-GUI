package ddgui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    public Spinner bsSpinner;
    public CheckBox bsCheckBox;
    public Spinner countSpinner;
    public CheckBox countCheckBox;
    public Button runButton;
    public Button inputFilePathButton;
    public Button outputFilePathButton;



    @FXML
    void bsCheckBoxOnAction (ActionEvent event) {
        if( bsCheckBox.isSelected() ) {
            bsSpinner.setDisable( false );
        }
        else {
            bsSpinner.setDisable( true );
        }

    }

    @FXML
    void countCheckBoxOnAction (ActionEvent event) {
        if( countCheckBox.isSelected() ) {
            countSpinner.setDisable( false );
        }
        else {
            countSpinner.setDisable( true );
        }

    }

    @FXML
    void runButtonOnAction (ActionEvent event) {
        if( runButton.isSelected() ) {
            runButton.setDisable( false );
        }
        else {
            runButton.setDisable( true );
        }

    }

    @FXML
    void inputFilePathButtonOnAction (ActionEvent event) {
        if( inputFilePathButton.isSelected() ) {
            inputFilePathButton.setDisable( false );
        }
        else {
            inputFilePathButton.setDisable( true );
        }

    }

    @FXML
    void outputFilePathButtonOnAction (ActionEvent event) {
        if( outputFilePathButton.isSelected() ) {
            outputFilePathButton.setDisable( false );
        }
        else {
            outputFilePathButton.setDisable( true );
        }

    }

}