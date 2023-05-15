package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MedicalRecordController implements Initializable {
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	public static int patientId;
	public static int insuranceStatus;
	
	public static int getInsuranceStatus() {
		return insuranceStatus;
	}


	public static void setInsuranceStatus(int insuranceStatus) {
		MedicalRecordController.insuranceStatus = insuranceStatus;
	}


	public static int getPatientId() {
		return patientId;
	}


	public static void setPatientId(int patientId) {
		MedicalRecordController.patientId = patientId;
	}


	@FXML
    private TextField inputId;
    @FXML
    private TextField inputAge;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputGender;
    @FXML
    private TextField inputPh;
    @FXML
    private TextField inputRoom;
    @FXML
    private TextField inputReg;
    @FXML
    private TextField inputEnd;
    @FXML
    private TextField inputBill;
    
    
	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
   
		ConfirmationController obj = new ConfirmationController();
		
		System.out.println("ID:"+obj.getPatientId());
		
		
		
		try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
    					System.out.println("Connection Established!");
    					
    					PreparedStatement stmt;
    					ResultSet rs;
    					String sql="select * from Patients WHERE patient_id=?";
    					stmt=con.prepareStatement(sql);
    					stmt.setInt(1, obj.getPatientId());
    					rs = stmt.executeQuery();
    					
    				while(rs.next()) {
    					setInsuranceStatus(rs.getInt("insurance_status"));
    				  inputId.setText(String.valueOf(rs.getInt("patient_id")));
    				    inputAge.setText(String.valueOf(rs.getInt("patient_age")));
    				     inputName.setText(rs.getString("patient_name"));
    				     inputGender.setText(rs.getString("patient_gender"));
    				     inputPh.setText(String.valueOf(rs.getInt("patient_ph")));
    				     inputBill.setText(String.valueOf(rs.getInt("patient_cost")));
    				}

    				
    				PreparedStatement stmt1;
					ResultSet rs1;
					String sql1="select * from Admission WHERE patient_id=?";
					stmt1=con.prepareStatement(sql1);
					stmt1.setInt(1, obj.getPatientId());
					rs1 = stmt1.executeQuery();
					
					while (rs1.next()) {
						 	inputRoom.setText(String.valueOf(rs1.getInt("room_no")));
		 				    inputReg.setText(rs1.getString("reg_date"));
		 				    inputEnd.setText(rs1.getString("end_date"));
		    				
						
					}
    			
    				con.close();
    				}
    				catch(Exception e) {
    					System.out.println(e);
    					
    				}		
		
				
		setPatientId(obj.getPatientId());
    }
	
	
	public void claimInsurance(ActionEvent event) throws IOException {
		
		
		if (getInsuranceStatus()==1) {
		
		root = FXMLLoader.load(getClass().getResource("ClaimInsurance.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		}else {
			LoginMassageBox.display("Error","You are NOT Subscribed to one");
		}
	
	}
	
	public void Menu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ReceptionPanel.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void Back(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ConfirmAdmissionScreen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
}
	public void Paid(ActionEvent event) throws IOException {
		
		
		String bill= inputBill.getText();
		LoginMassageBox.display("Paid", "Amount : " + bill + " paid");
		root = FXMLLoader.load(getClass().getResource("AdminReceptionCheck.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
			
		
	

	}	
	
	
	
	


}
