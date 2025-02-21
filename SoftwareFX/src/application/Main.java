package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception{
		
			Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
			Scene scene = new Scene(root);
			
			stage.setTitle("KocNow");
			stage.setResizable(false);
			stage.centerOnScreen();

			stage.setScene(scene);
			stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
