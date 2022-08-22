package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class scene1Controller {
	@FXML
	private Button b1;

	@FXML
	private ListView<String> listview;
	
	@FXML
	private ImageView imageview;
	// Event Listener on Button[#b1].onAction
	Movie mdetail;
	
	@FXML
	public void changescene(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchView.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		
		SearchViewControllerN sc = fxmlLoader.getController();

		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void movieSetter(Movie mv) {
		var posterNotFoundImage = new Image(getClass().getResourceAsStream("poster-not-found.png"));
		mdetail = mv;
		 try
         {
			 imageview.setImage(new Image(mv.getPoster()));
         }catch(Exception e)
         {
        	 imageview.setImage(posterNotFoundImage);
         }
		 listview.getItems().clear();
		 listview.getItems().add("Title : " + mv.getTitle());
		 listview.getItems().add("Rated : " + mv.getRated());
		 listview.getItems().add("Director : " + mv.getDirector());
		 listview.getItems().add("Genre : " + mv.getGenre());
		 listview.getItems().add("Actor : " + mv.getActors());
		 listview.getItems().add("Year : " + mv.getYear());
		 listview.getItems().add("Released : " + mv.getReleased());
		 listview.getItems().add("Runtime : " + mv.getRuntime());
		 listview.getItems().add("Writer : " + mv.getWriters());
		 listview.getItems().add("Plot : " + mv.getPlot());
	}
	
}
