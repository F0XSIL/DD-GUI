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
        inputFilePathTextField.setText(file.getAbsolutePath());
    }

    @FXML
    void runButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        logTextArea.clear();
        String toLogTextArea = new String();
        String params = new String();

        if (bsCheckBox.isSelected()) params += " bs=" + String.format("%.0f", bsSpinner.getValue()) + " ";
        if (countCheckBox.isSelected()) params += " count=" + String.format("%.0f", countSpinner.getValue()) + " ";

        String command = "dd " + "if=\"" + inputFilePathTextField.getText() + "\" of=\""+ outputFilePathTextField.getText() + "\"" + params + " status=progress";

        runButton.setDisable(true);

        Process dd = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",command});

        logTextArea.setText("Running " + command + "\n");

        InputStream out = dd.getInputStream();

        toLogTextArea = logTextArea.getText();

        dd.waitFor();

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