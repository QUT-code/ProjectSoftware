package event.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by Ordom
 *
 */
public class App extends Application
{
	@Override
	public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/images/eagle.png"));
        
        String css = this.getClass().getResource("/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("KocNow");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }	
}
