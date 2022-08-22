package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SearchViewControllerN implements Initializable {
	@FXML
	private BorderPane borderPane;
	@FXML
	private TextField searchTextField;
	@FXML
	private ListView<Movie> resultsListView;
	@FXML
	private ImageView imageView;
	
	@FXML
	private Button detailbtn;
	
	Movie mv;
	
	// Event Listener on TextField[#searchTextField].onAction
	@FXML
	public void searchTextFieldSubmitted(ActionEvent event) {
		searchButtonClicked(event);
	}
	// Event Listener on Button.onAction
	@FXML
	public void searchButtonClicked(ActionEvent event) {
		var searchResults = APIManager.Instance().getMovieFromOMDBBySearchTerm(searchTextField.getText());
        resultsListView.getItems().clear();
        if(searchResults.getMovies() != null)
        {
            resultsListView.getItems().addAll(searchResults.getMovies());

            resultsListView.getSelectionModel().select(0);
        }
        else
        {
            // movie was not found - maybe output to a message label of some kind
        }
	}
	// Event Listener on Button.onAction
	@FXML
	public void detailSearchButton(ActionEvent event) throws IOException {
		var detailedMovie = APIManager.Instance().getMovieFromOMDBByTitleAndYear(mv.getTitle(),mv.getYear());
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene1.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		
		scene1Controller sc = fxmlLoader.getController();
		sc.movieSetter(detailedMovie);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		var posterNotFoundImage = new Image(getClass().getResourceAsStream("poster-not-found.png"));

        resultsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldMovieSelected, newMovieSelected) ->{
        	
        	mv = newMovieSelected;
        	detailbtn.setVisible(true);

            try
            {
                imageView.setImage(new Image(newMovieSelected.getPoster()));
            }catch(Exception e)
            {
                imageView.setImage(posterNotFoundImage);
            }
        });
		
	}
}
