import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class EditorController {

    @FXML
    private TextArea areaText;

    private TextFile currentTextFile;

    private EditorModel model;

    public EditorController(EditorModel model) {
        this.model = model;
    }
   
    //File Action
    @FXML
    private void onOpenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(null);
       
        if (file != null) {
            IOResult<TextFile> io = model.load(file.toPath());

            if (io.isOk() && io.hasData()) {
                currentTextFile = io.getData();

                areaText.clear();
                currentTextFile.getContent().forEach(line -> areaText.appendText(line + "\n"));
            } else {
                System.out.println("Failed");
            }
        }
    }

    @FXML
    private void onSaveFile() {
        TextFile textFile;
        if(currentTextFile == null){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./"));
            //Show save file dialog
            File file = fileChooser.showSaveDialog(null);
            if(file == null)
                return;
            textFile = new TextFile(file.toPath(), Arrays.asList(areaText.getText().split("\n")));
            currentTextFile = textFile;
        }
        else
            textFile = new TextFile(currentTextFile.getFile(), Arrays.asList(areaText.getText().split("\n")));
        model.save(textFile);
    }

    @FXML
    private void onDeleteFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        for(File f : selectedFiles){
            f.delete();
        }
    }
    @FXML
    private void onRenameFile(){
        AlertBox.displayRenameBox();
    }
    @FXML
    private void onSearchFile(){
        int pos = AlertBox.displaySearchBox(areaText.getText(), areaText.getCaretPosition());
        areaText.positionCaret(pos);
    }
 

    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("OOAD Assignment - 1\nAuthor:- Sidharth Srinivas Padhee\nMajor functionality:\n1. Create Directory\n2. Display the content of a Directory \n3. Create a file \n4. Open an existing file and show the content in a text area \n5. Modify and save the file \n6. Delete a file \n7. Rename a file \n8. Search for a string pattern in a file");
        alert.show();
    }
}