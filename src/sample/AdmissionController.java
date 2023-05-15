package sample;

import javafx.collections.ObservableList;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdmissionController implements Initializable {

	
	private Stage stage;
	private Scene scene;
	private Parent root;
	static int s=0;


    @FXML
    private TableView<Patient> patients;

    @FXML
    private TableColumn<Patient, Integer> idCol;

    @FXML
    private TableColumn<Patient, String> ageCol;

    @FXML
    private TableColumn<Patient, String> nameCol;
    @FXML
    private TableColumn<Patient, String> genderCol;
    
    @FXML
    private TableColumn<Patient, Integer> phCol;



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
    private RadioButton yes;
    
    @FXML
    private RadioButton no;
    
    
    public static int id;
    public static int age;
    public static String name;
    public static String gender;
    public static int ph;
    public static int cost;
    public static int status;
    public static int getCost() {
		return cost;
	}

	public static void setCost(int cost) {
		AdmissionController.cost = cost;
	}

	
    public static int getStatus() {
		return status;
	}

	public static void setStatus(int status) {
		AdmissionController.status = status;
	}

	public static int getPh() {
		return ph;
	}

	public static void setPh(int ph) {
		AdmissionController.ph = ph;
	}

	public static LocalDate getDate() {
		return date;
	}

	public static void setDate(LocalDate date) {
		AdmissionController.date = date;
	}

	public static LocalDate date;


    public static int getAge() {
		return age;
	}

	public static void setAge(int age) {
		AdmissionController.age = age;
	}

	public static String getGender() {
		return gender;
	}

	public static void setGender(String gender) {
		AdmissionController.gender = gender;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		AdmissionController.id = id;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		AdmissionController.name = name;
	}

	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("age"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
        phCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("ph"));
        setupTable();
    }

    @FXML
    void submit(ActionEvent event) {
    	
    	Exceptions HS=new Exceptions();

    	
    	
    
    	
    	try {
			HS.validateId(Integer.parseInt(inputId.getText()));
			int currentId = Integer.parseInt(inputId.getText());
			setId(currentId);
			
		}catch(NegativeIdException e){
			
			System.out.println("Id is Negative");
			LoginMassageBox.display("Exception","Id isnt Correct");
			
		}
    	
    	
    	try {
			HS.validateAge(Integer.parseInt(inputAge.getText()));
			setAge(Integer.parseInt(inputAge.getText()));
			
		}catch(ZeroAgeException e){
			
			System.out.println("Age is either Negative or Zero");
			LoginMassageBox.display("Exception","Age isnt Correct");
			
		}
    	
    	
        
        
        
        
        
        setName(inputName.getText());
        
		try {
			HS.validateGender(inputGender.getText());
			setGender(inputGender.getText());
			
		}catch(UnknownGenderException e){
			
			System.out.println("Gender input isnt correct (enter male or female)");
			LoginMassageBox.display("Exception","Gender Isnt Correct");
			
		}
        
        setPh(Integer.parseInt(inputPh.getText()));
        setStatus(0);
    	
        ObservableList<Patient> currentTableData = patients.getItems();
        int currentPatientId = Integer.parseInt(inputId.getText());

        for (Patient patient1 : currentTableData) {
            if(patient1.getId() == currentPatientId) {
            	
            	try {
        			HS.validateAge(Integer.parseInt(inputAge.getText()));
        			patient1.setAge(Integer.parseInt(inputAge.getText()));
        			
        		}catch(ZeroAgeException e){
        			
        			System.out.println("Age is either Negative or Zero");
        			LoginMassageBox.display("Exception","Age isnt Correct");
        			
        		}
            	
            	patient1.setName(inputName.getText());
            	patient1.setPh(Integer.parseInt(inputPh.getText()));
            	setStatus(patient1.getStatus());
            	try {
        			HS.validateId(currentPatientId);
        			setId(currentPatientId);
        			
        		}catch(NegativeIdException e){
        			
        			System.out.println("Id is Negative");
        			LoginMassageBox.display("Exception","Id isnt Correct");
        			
        		}
                setAge(Integer.parseInt(inputAge.getText()));
                setName(inputName.getText());
            	try {
        			HS.validateGender(inputGender.getText());
        			setGender(inputGender.getText());
        			
        		}catch(UnknownGenderException e){
        			
        			System.out.println("Gender input isnt correct (enter male or female)");
        			LoginMassageBox.display("Exception","Gender Isnt Correct");
        			
        		}
                setPh(Integer.parseInt(inputPh.getText()));
               

             

                patients.setItems(currentTableData);
                patients.refresh();
                break;
            }
        }
        
        
        
        
        
        
    }
    

   

    @FXML
    void rowClicked(MouseEvent event) {
        Patient clickedPatient = patients.getSelectionModel().getSelectedItem();
        inputId.setText(String.valueOf(clickedPatient.getId()));
        inputAge.setText(String.valueOf(clickedPatient.getAge()));
        inputName.setText(String.valueOf(clickedPatient.getName()));
        inputGender.setText(String.valueOf(clickedPatient.getGender()));
        inputPh.setText(String.valueOf(clickedPatient.getPh()));
    }

    private void setupTable(){
    	
    	
    	if (s==0) {
    		
    		String filename= "Patients.txt";
    		File file = new File(filename);
    		
    		
    		
    		if(file.exists()) {
    			s=1;
    	}	
    		
    	}
    	
    	Patient[] patientsArr=new Patient[100];
    	int i=0;
    	
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
    					System.out.println("Connection Established!");
    					
    					Statement stmt=con.createStatement();
    					ResultSet rs=stmt.executeQuery("select * from Patients");


    				while(rs.next()) {
    					
    					int id= rs.getInt(1);
    					String pName = rs.getString("patient_name");
    					int pAge = rs.getInt("patient_age");
    					String pGender = rs.getString("patient_gender");
    					int cost=rs.getInt("patient_cost");
    					int ph= rs.getInt("patient_ph");
    					int st= rs.getInt("insurance_status");
    					patientsArr[i] = new Patient(pName,pGender,id,pAge,cost,ph,st);
    					patients.getItems().addAll(patientsArr[i]);
    					
    					String newS="";
    					if (st==1) {
    						newS="Yes";
    					}else {
    						newS="No";
    					}
    					
    					
    					if(s==0) {
    					//file handling
    	        		try
    					{
    						String filename= "Patients.txt";
    					    FileWriter fw = new FileWriter(filename,true);
    					     //the true will append the new data
    					    fw.write("Id: " + " " + id + " " +  "Name: "+ " "+pName + " "+ "Age: "+ " "+ pAge + " "+ "Gender: "+ " "+ pGender + " "+ "Phone#: "+ " "+ ph + " "+ "Bill: "+ " "+ cost  + " "+ "Insurace Status:" + newS + "\n");//appends the string to the file
    					    fw.close();
    					    
    						
    					}
    					catch (IOException e) {
    					      System.out.println("An error occurred.");
    					      e.printStackTrace();
    					    }
    	        		
    					
    				}
    					
    				

    				//end
    					
    				}
    				con.close();
    				
    	}catch(Exception e) {
    					System.out.println(e);
    					
    				}		

    }
    	
    
    
	public void ButtonPress(ActionEvent event) throws IOException {
		
		if (no.isSelected()) {
			setStatus(0);
		root = FXMLLoader.load(getClass().getResource("Wards.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}else {
			
			
			
			if (!(getStatus()==1)) {
				setStatus(1);
			root = FXMLLoader.load(getClass().getResource("Insurance.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			setStatus(1);
			stage.show();
			}else {
				LoginMassageBox.display("Error","You are already Subscribed to one");
			}
		}
	
	
}
	
	public void Menu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ReceptionPanel.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
	