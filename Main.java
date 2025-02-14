package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	double x,y = 0;
	@Override
	public void start(Stage stage) throws Exception{
		
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Parent root2 = FXMLLoader.load(getClass().getResource("CreateEvent.fxml"));
			stage.initStyle(StageStyle.UNDECORATED);
			
			root.setOnMousePressed(event -> {
				x = event.getSceneX();
				y = event.getSceneY();
			});
			
			root.setOnMouseDragged(event -> {
				stage.setX(event.getScreenX() - x);
				stage.setY(event.getScreenY() - y);
			});
			
			Scene scene = new Scene(root);
			
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);

			stage.setResizable(false);

			stage.setScene(scene);
			stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
