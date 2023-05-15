package sample;

import java.io.IOException;

//import sample.Admit;
import sample.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	static Patient[] Patients = new Patient[150];
	static int patientCount=0;
	
//	static Admit[] Admissions = new Admit[100];
//	static int count=0;
	
	//registration
	@FXML
	private TextField myTextName;
	@FXML
	private TextField myTextAge;
	@FXML
	private TextField myTextId;
	@FXML
	private TextField myTextGender;
	
	//confirmation
	@FXML private Label myTextName1;
	@FXML private  Label myTextAge1;
	@FXML private  Label myTextId1;
	@FXML private  Label myTextGender1;
	@FXML private  Label myTextWardName;
	@FXML private  Label myTextWardType;
	@FXML private  Label myTextNumber;
	@FXML private Label myTextExtra;
	
	
	
	
	public void Menu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
}
	
	public void Next(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Admission.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
	
}

}
