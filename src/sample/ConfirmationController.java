package sample;

import javafx.collections.ObservableList;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ConfirmationController implements Initializable {

	
	private Stage stage;
	private Scene scene;
	private Parent root;

	
	private int id;
	private int roomNo;
	private String reg_Date;
	private String end_Date;
	private String patientName;
	
	public static int patientId;


    public static int getPatientId() {
		return patientId;
	}

	public static void setPatientId(int patientId) {
		ConfirmationController.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@FXML
    private TableView<Admit> admissions;
    

    @FXML
    private TableColumn<Admit, Integer> idCol;
    @FXML
    private TableColumn<Admit, Integer> pidCol;
    @FXML
    private TableColumn<Admit, String> nameCol;
    @FXML
    private TableColumn<Admit, Integer> widCol;
    @FXML
    private TableColumn<Admit, Integer> roomNoCol;
    @FXML
    private TableColumn<Admit, String> regDate;
    @FXML
    private TableColumn<Admit, String> endDate;
    @FXML
    private TableColumn<Admit, Integer> ageCol;
    @FXML
    private TableColumn<Admit, String> wardNameCol;
    
    @FXML
    private TextField inputId;

    
    
	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<Admit, Integer>("id"));
        pidCol.setCellValueFactory(new PropertyValueFactory<Admit, Integer>("PatientId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Admit, String>("PatientName"));
        widCol.setCellValueFactory(new PropertyValueFactory<Admit, Integer>("WardId"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<Admit, Integer>("roomNo"));
        regDate.setCellValueFactory(new PropertyValueFactory<Admit, String>("regDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<Admit, String>("endDate"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Admit, Integer>("PatientAge"));
        wardNameCol.setCellValueFactory(new PropertyValueFactory<Admit, String>("WardName"));
   
        setupTable();
    }
	
	   @FXML
	    void rowClicked(MouseEvent event) {
	        Admit clickedAdmission = admissions.getSelectionModel().getSelectedItem();
	        inputId.setText(String.valueOf(clickedAdmission.getPatientId()));
	    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getReg_Date() {
		return reg_Date;
	}

	public void setReg_Date(String reg_Date) {
		this.reg_Date = reg_Date;
	}

	public String getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(String end_Date) {
		this.end_Date = end_Date;
	}
	
	
    private void setupTable(){
    	
    	Admit[] admitArr=new Admit[100];
    	int i=0;
    	
    	Patient[] patientsArr=new Patient[100];
    	int j=0;
    	
    	Wards[] wardsArr=new Wards[15];
    	int k=0;
    	
    	
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
    					System.out.println("Connection Established!");
    					
//    					Statement stmtNew=con.createStatement();
//    					ResultSet rsNew=stmtNew.executeQuery("select * from Admission");
//
//    				while(rsNew.next()) {
//    					
//    					int id= rsNew.getInt(1);
//    					int room_no = rsNew.getInt("room_no");
//    					String reg_Date = rsNew.getString("reg_date");
//    					String end_Date = rsNew.getString("end_date");
//    					
//    					
//    					admitArr[i] = new Admit(id,room_no,reg_Date,end_Date);
//    					admissions.getItems().addAll(admitArr[i]);
//    					
//    				}

    				
    				Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from Admission");
    				
    				while(rs.next()) {
    					
    					int id= rs.getInt(1);
    					int p_id= rs.getInt("patient_id");
    					int w_id = rs.getInt("ward_id");
    					int room_no = rs.getInt("room_no");
    					String reg_Date = rs.getString("reg_date");
    					String end_Date = rs.getString("end_date");
    					

    					PreparedStatement stmt1;
    					ResultSet rs1;
    					String sql1="select * from Patients WHERE patient_id=?";
    					stmt1=con.prepareStatement(sql1);
    					stmt1.setInt(1, p_id);
    					rs1 = stmt1.executeQuery();
    					
    					while(rs1.next()) {
    					String pName = rs1.getString("patient_name");
    					int pAge = rs1.getInt("patient_age");
    					String pGender = rs1.getString("patient_gender");
    					int cost=rs1.getInt("patient_cost");
    					int ph= rs1.getInt("patient_ph");
    					int st= rs1.getInt("insurance_status");
    					patientsArr[j] = new Patient(pName,pGender,p_id,pAge,cost,ph,st);
    				}
    				
    					PreparedStatement stmt2;
    					ResultSet rs2;
    					String sql2="select * from Wards WHERE ward_id=?";
    					stmt2=con.prepareStatement(sql2);
    					stmt2.setInt(1, w_id);
    					rs2 = stmt2.executeQuery();
    					
    					while (rs2.next()) {
    						
        					String wardT = rs2.getString("ward_type");
        					String wardN = rs2.getString("ward_name");
        					int wardR = rs2.getInt("ward_rooms");
        					int cost = rs2.getInt("cost");
        					
        					
        					wardsArr[k] = new Wards(w_id,wardT,wardN,wardR,cost);
    					}
    				
    				
    				
    				admitArr[i] = new Admit(id,room_no,reg_Date,end_Date,patientsArr[j].getName(),patientsArr[j].getId(),patientsArr[j].getAge(),wardsArr[k].getId(),wardsArr[k].getName());	
    				admissions.getItems().addAll(admitArr[i]);
    					
    				}

    				
    				con.close();
    				}
    				catch(Exception e) {
    					System.out.println(e);
    					
    				}
    	

    }
	
	public void Menu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ReceptionPanel.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

	
	public void MedicalRecord(ActionEvent event) throws IOException {
		
		setPatientId(Integer.parseInt(inputId.getText()));
		root = FXMLLoader.load(getClass().getResource("MedicalRecord.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
}
	

	
}
