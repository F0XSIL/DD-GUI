package ddgui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Controller {

    @FXML
    public Spinner bsSpinner;
    public CheckBox bsCheckBox;
    public Spinner countSpinner;
    public CheckBox countCheckBox;
    public Button runButton;
    public Button inputFilePathButton;
    public Button outputFilePathButton;
    public TextField outputFilePathTextField;
    public TextArea logTextArea;
    public TextField inputFilePathTextField;



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
    void outputFilePathButtonOnAction(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select output files.");
        File file = chooser.showOpenDialog(new Stage());
        outputFilePathTextField.setText(file.getAbsolutePath());
    }

    @FXML
    void inputFilePathButtonOnAction(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select input files.");
        File file = chooser.showOpenDialog(new Stage());
        inputFilePathButton.setText(file.getAbsolutePath());
    }

    @FXML
    void runButtonOnAction(ActionEvent event) throws IOException {
        logTextArea.clear();
        String toLogTextArea = new String();
        String params = new String();

        if (bsCheckBox.isSelected()) params += "--iterations=" + bsSpinner.getValue().toString() + " ";
        if (countCheckBox.isSelected()) params += "--random-source=" + countSpinner.getValue().toString() + " ";

        String command = "dd " + "if=" + inputFilePathTextField.getText();

        runButton.setDisable(true);
        Process dd = Runtime.getRuntime().exec(command);

        logTextArea.setText("Running " + command + "\n");

        InputStream out = dd.getInputStream();

        toLogTextArea = logTextArea.getText();
        int c;
        while ((c = out.read()) != -1) {
            toLogTextArea += (char) c;
            logTextArea.setText(toLogTextArea);
        }
        out.close();

        runButton.setDisable(false);
        logTextArea.setText(logTextArea.getText() + "Finished!");

        }
    }