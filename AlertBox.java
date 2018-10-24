import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Creating Alert Boxes

public class AlertBox{
    static Stage window; 
    static File file;
    static String pathStr, fileName;
    static Button b, r;
    static TextField textField;
    static int pos;
    public static int displaySearchBox(String text, int curPos){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); 
        window.setTitle("Search");
        b = new Button("Search");
        textField = new TextField();
        textField.setMaxWidth(200);
        b.setOnAction(e -> {
           pos = text.indexOf(textField.getText(), curPos+1);
            if(pos == -1){
                pos = text.indexOf(textField.getText(), 0);
                if(pos == -1){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("String Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("\"" + textField.getText() + "\" Not Found");
                    alert.showAndWait();
                }
            }
            window.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(textField, b);
        layout.setAlignment(Pos.CENTER);

        Scene sc = new Scene(layout, 300, 200);
        window.setScene(sc);
        window.showAndWait();
        return pos;
    }
    public static void displayRenameBox(){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); 
        window.setTitle("Rename");
        b = new Button("Choose File");
        textField = new TextField();
        textField.setMaxWidth(200);
        r = new Button("Rename");
        b.setOnAction( e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./"));
            file = fileChooser.showOpenDialog(null);
            int n = file.getPath().lastIndexOf('\\');
            pathStr = file.getPath().substring(0, n+1);
            fileName = file.getPath().substring(n+1);
            textField.setText(fileName);    
        });
        r.setOnAction(e -> {
            if(file.renameTo(new File(pathStr+textField.getText()))){
                System.out.println("Renamed Successfully");
                file.delete();
            }
            else
                System.out.println("Rename Fail");
            window.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(b, textField, r);
        layout.setAlignment(Pos.CENTER);

        Scene sc = new Scene(layout, 300, 200);
        window.setScene(sc);
        window.showAndWait();
    }
}