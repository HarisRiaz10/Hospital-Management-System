package sample;

import javafx.collections.ObservableList;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InsuranceController implements Initializable {

	
	private Stage stage;
	private Scene scene;
	private Parent root;
	static int s=0;
	
    @FXML
    private TableView<Insurance> insurance;

    @FXML
    private TableColumn<Insurance, Integer> idCol;

    @FXML
    private TableColumn<Insurance, String> nameCol;
    
    @FXML
    private TableColumn<Insurance, String> companyCol;

    @FXML
    private TextField inputId;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputCompany;

    public static int id;
    public static String name;
    public static String com;
    public static int policyId;
    public static Policy pobj;


	public static Policy getPobj() {
		return pobj;
	}

	public static void setPobj(Policy pobj) {
		InsuranceController.pobj = pobj;
	}

	public static int getPolicyId() {
		return policyId;
	}

	public static void setPolicyId(int policyId) {
		InsuranceController.policyId = policyId;
	}

	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<Insurance, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Insurance, String>("name"));
        companyCol.setCellValueFactory(new PropertyValueFactory<Insurance, String>("companyName"));
        setupTable();
    }

    @FXML
    void submit(ActionEvent event) {
    	
    	
    	
    	AdmissionController obj7 = new AdmissionController();
    	obj7.setStatus(1);
    	
    	
    	
        ObservableList<Insurance> currentTableData = insurance.getItems();
        int currentInsuranceId = Integer.parseInt(inputId.getText());

        for (Insurance insurance1 : currentTableData) {
            if(insurance1.getId() == currentInsuranceId) {
            	insurance1.setName(inputName.getText());
            	insurance1.setCompanyName(inputCompany.getText());
            	
            	Policy newp = new Policy();
            	newp=insurance1.getPolicies();
            	
                setId(currentInsuranceId);
                setName(inputName.getText());
                setComName(inputCompany.getText());
                setPobj(newp);
                

             

                insurance.setItems(currentTableData);
                insurance.refresh();
                break;
            }
        }
        
        
        
        
        
        
    }
    

   

    public static int getId() {
		return id;
	}

	public static void setId(int id) {
		InsuranceController.id = id;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		InsuranceController.name = name;
	}

	public static String getComName() {
		return com;
	}

	public static void setComName(String comName) {
		InsuranceController.com = comName;
	}

	@FXML
    void rowClicked(MouseEvent event) {
        Insurance clickedInsurance = insurance.getSelectionModel().getSelectedItem();
        inputId.setText(String.valueOf(clickedInsurance.getId()));
        inputName.setText(String.valueOf(clickedInsurance.getName()));
        inputCompany.setText(String.valueOf(clickedInsurance.getCompanyName()));
        PolicyBox.display("Policy Details",clickedInsurance.getPolicies());

    }

    private void setupTable(){
    	
    	//System.out.println("LMAO");
    	
    	Insurance[] insuranceArr=new Insurance[100];
    	int i=0;
    	
    	Policy[] policyArr=new Policy[100];
    	int j=0;
    	
    	
    	if (s==0) {
    		
    		String filename= "Insurance.txt";
			File file = new File(filename);
			
			String filename1= "Policies.txt";
			File file1 = new File(filename1);
			
			if(file.exists() && file1.exists()) {
				s=1;
    	}	
			
    	}
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
    					System.out.println("Connection Established!");
    					
    					Statement stmt=con.createStatement();
    					ResultSet rs=stmt.executeQuery("select * from Insurance");
    					
    					
    					
    					

    				while(rs.next()) {
    					
    					int id= rs.getInt(1);
    					int pID=rs.getInt("policy_id");
    					String iName = rs.getString("name");
    					String cName = rs.getString("company_name");
    					
    					if (s==0) {
    					//file handling
    	        		try
    					{
    						String filename= "Insurance.txt";
    					    FileWriter fw = new FileWriter(filename,true);
    					     //the true will append the new data
    					    fw.write("Insurance Id: " + " " + id + " " +  "Insurance Name: "+ " "+iName + " "+ "Company Name: "+ " "+ cName + "\n");//appends the string to the file
    					    fw.close();
    						}catch (IOException e) {
    					      System.out.println("An error occurred.");
    					      e.printStackTrace();
    					    }
    	        		
    					}
    					
    				
    	        		//end
    					
    				
    					
    					
    					String sql = "select * from Policy where policy_id=?";
    	        		PreparedStatement stmt1 = con.prepareStatement(sql);
    					stmt1.setInt(1, pID);
    					ResultSet rs1=stmt1.executeQuery();
    					while(rs1.next()) {
        					String title = rs1.getString("title");
        					String description = rs1.getString("description");
        					int percentage = rs1.getInt("percentage");
        					policyArr[j] = new Policy(pID,title,description,percentage);
        					
        					if (s==0) {
        					//file handling
        	        		try
        					{
        						String filename= "Policies.txt";
        					    FileWriter fw = new FileWriter(filename,true);
        					     //the true will append the new data
        					    fw.write("Policy Id: " + " " + pID + " " +  "Policy Title: "+ " "+title + " "+ "Policy Description: "+ " "+ description + " "+ "Percentage: "+ " "+ percentage + "\n");//appends the string to the file
        					    fw.close();
        						
        					}
        					catch (IOException e) {
        					      System.out.println("An error occurred.");
        					      e.printStackTrace();
        					    }
        	        		
        					}
        	        		//end
        					
        					
    					
    						
    					insuranceArr[i] = new Insurance(id,iName,cName,policyArr[j]);
    					insurance.getItems().addAll(insuranceArr[i]);
    					i++;
    					j++;
    					
    					}
    	
    
    				}
    				
    				s=1;
    				
    				con.close();
    				}
    				catch(Exception e) {
    					System.out.println(e);
    					
    				}		
    }
    
	public void ButtonPress(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("Wards.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
}
	
	public void Menu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ReceptionPanel.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void Back(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Patient.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
}
	