package sample;

import java.io.FileWriter;
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

public class ClaimInsuranceController implements Initializable {
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
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
    private TextField inputInsurance;
    @FXML
    private TextField inputCompany;
    @FXML
    private TextField inputTitle;
    @FXML
    private TextField inputBill;
    @FXML
    private TextField inputNewBill;
    @FXML
    private TextField inputPolicy;
    @FXML
    private TextField inputDescription;
    @FXML
    private TextField inputPercentage;
    
    
	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
   
		MedicalRecordController obj = new MedicalRecordController();
		
		System.out.println("ID:"+obj.getPatientId());
		
		int PolicyId=0;
		int InsuranceId=0;
		int cost=0;
		int iStatus=0;
		
		
		
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
    					
    					iStatus=rs.getInt("insurance_status");
    				  inputId.setText(String.valueOf(rs.getInt("patient_id")));
    				    inputAge.setText(String.valueOf(rs.getInt("patient_age")));
    				     inputName.setText(rs.getString("patient_name"));
    				     inputGender.setText(rs.getString("patient_gender"));
    				     inputPh.setText(String.valueOf(rs.getInt("patient_ph")));
    				     inputBill.setText(String.valueOf(rs.getInt("patient_cost")));
    				     cost=rs.getInt("patient_cost");
    				}

    				
    				PreparedStatement stmt1;
					ResultSet rs1;
					String sql1="select * from claimInsurance WHERE patient_id=?";
					stmt1=con.prepareStatement(sql1);
					stmt1.setInt(1, obj.getPatientId());
					rs1 = stmt1.executeQuery();
					
					while (rs1.next()) {
						 	
						InsuranceId=rs1.getInt("insurance_id");
						PolicyId=rs1.getInt("policy_id");
						
					}
					
					
					PreparedStatement stmt2;
					ResultSet rs2;
					String sql2="select * from insurance WHERE insurance_id=?";
					stmt2=con.prepareStatement(sql2);
					stmt2.setInt(1, InsuranceId);
					rs2 = stmt2.executeQuery();
					
					while(rs2.next()) {
						 inputInsurance.setText(String.valueOf(InsuranceId));
						    
						    inputCompany.setText(rs2.getString("company_name"));
						  
						     inputTitle.setText(rs2.getString("name"));
					}
					
					
					PreparedStatement stmt3;
					ResultSet rs3;
					String sql3="select * from Policy WHERE policy_id=?";
					stmt3=con.prepareStatement(sql3);
					stmt3.setInt(1, PolicyId);
					rs3 = stmt3.executeQuery();
					
					while(rs3.next()) {
						 inputPolicy.setText(String.valueOf(PolicyId));
						    
						    inputDescription.setText(rs3.getString("description"));
						    
						    inputPercentage.setText(String.valueOf(rs3.getInt("percentage")));
						  
						    System.out.println("cost:"+cost);
						    
						   int x = rs3.getInt("percentage");
						   System.out.println("x:"+x);
						   double h= new Double(x);
						   double y = (h/100);
						   System.out.println("y:"+y);
						   double z = y*cost;
						   System.out.println("z:"+z);
						   
					
   		        	
						   
						   
						   double newCost=cost-z;
						   
						   int nc=(int)Math.round(newCost);
						   
						   inputNewBill.setText(String.valueOf(newCost));
						   
						   
							String sql4 = "UPDATE Patients SET patient_cost=?,insurance_status=? WHERE patient_id=?";
	   		        		PreparedStatement stmt4 = con.prepareStatement(sql4);
	   		        		stmt4.setInt(1, nc);
	   		        		stmt4.setInt(2, 0);
	   		        		stmt4.setInt(3, obj.getPatientId());
	   		      
	   		        		int rowsinserted2 = stmt4.executeUpdate();
	   		        		if(rowsinserted2>0)System.out.println("\nRecords Updated");
	   		        		
	   		        		//
	   		        		
//updating data (overwrite)
	   		        		
	   		        		Statement stmt9=con.createStatement();
	    					ResultSet rs9=stmt.executeQuery("select * from Patients");
	    					
	    					int first1=0;
	   		        		FileWriter fw1 ;
	   		        		
	   		        		
	   		        		while(rs9.next()) {
	   	    					
	   	    					int id1= rs9.getInt(1);
	   	    					String pName = rs9.getString("patient_name");
	   	    					int pAge = rs9.getInt("patient_age");
	   	    					String pGender = rs9.getString("patient_gender");
	   	    					int cost9=rs9.getInt("patient_cost");
	   	    					int ph= rs9.getInt("patient_ph");
	   	    					int st1= rs9.getInt("insurance_status");
	   	    					String newS="";
	   	    					if (st1==1) {
	   	    						newS="Yes";
	   	    					}else {
	   	    						newS="No";
	   	    					}
	   	    					
	   	    					//file handling
	   	    	        		try
	   	    					{
	   	    						String filename= "Patients.txt";
	   	    						
	   	    						if(first1==0) {
	   	        					    fw1 = new FileWriter(filename,false);
	   	        					    first1=1;
	   	        						}else {
	   	        							fw1 = new FileWriter(filename,true);
	   	        						}
	   	    					     //the true will append the new data
	   	    					    fw1.write("Id: " + " " + id1 + " " +  "Name: "+ " "+pName + " "+ "Age: "+ " "+ pAge + " "+ "Gender: "+ " "+ pGender + " "+ "Phone#: "+ " "+ ph + " "+ "Bill: "+ " "+ cost9  + " "+ "Insurace Status:" + newS + "\n");//appends the string to the file
	   	    					    fw1.close();
	   	    					}
	   	    					catch (IOException e) {
	   	    					      System.out.println("An error occurred.");
	   	    					      e.printStackTrace();
	   	    					    }
	   	    	        		
	   		        		}
	   	    	        		//end
	   		        		
	   		        		
	   		        		///
	   		        		
	   		        		
	   		        		String sql5 = "UPDATE claimInsurance SET i_status=? WHERE patient_id=?";
	   		        		PreparedStatement stmt5 = con.prepareStatement(sql5);
	   		        		stmt5.setString(1, "Claimed");
	   		        		stmt5.setInt(2, obj.getPatientId());
	   		      
	   		        		int rowsinserted3 = stmt5.executeUpdate();
	   		        		if(rowsinserted3>0)System.out.println("\nInsurance Status Updated");
	   		        		
	   		        		
	   		        		//updating data (overwrite)
	   		        		
	   		        		int first=0;
	   		        		FileWriter fw ;
	   		        		
	   		        		Statement stmtUpdate=con.createStatement();
	    					ResultSet rsUpdate=stmtUpdate.executeQuery("select * from claimInsurance");
	    

	    				while(rsUpdate.next()) {
	    					
	    					int id= rsUpdate.getInt(1);
	    					int patient_id = rsUpdate.getInt("patient_id");
	    					int insurance_id=rsUpdate.getInt("insurance_id");
	    					int pid= rsUpdate.getInt("policy_id");
	    					String st = rsUpdate.getString("i_status");
	    					
	    					//file handling
        	        		try
        					{
        						String filename= "ClaimInsurance.txt";
        						if(first==0) {
        					    fw = new FileWriter(filename,false);
        					    first=1;
        						}else {
        							fw = new FileWriter(filename,true);
        						}
        					     //the true will append the new data
        					    fw.write("Index: " + " " + id + " " +  "Patient Id: "+ " "+patient_id + " "+ "Insurance Id: "+ " "+ insurance_id + " "+ "Policy No: "+ " "+ pid + " "+ "Insurance Status: "+ " "+ st + "\n");//appends the string to the file
        					    fw.close();
        					}
        					catch (IOException e) {
        					      System.out.println("An error occurred.");
        					      e.printStackTrace();
        					    }
        	        		//end
	    					
	    					
	    				}
	   		        		
	   		        		
	   		        	
	   		        		
						   
						   
					}
					
					
    			
    				con.close();
    				}
    				catch(Exception e) {
    					System.out.println(e);
    					
    				}		
		
				
		
    }
	
	 public void Calculate(ActionEvent even)throws IOException{
		  String id = inputId.getText();
		 
		  MedicalRecordController obj = new MedicalRecordController();
			
			System.out.println("ID:"+id);
			
			int PolicyId=0;
			int InsuranceId=0;
			int cost=0;
			int iStatus=0;
			
			
			
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
	    					
	    					iStatus=rs.getInt("insurance_status");
	    				  inputId.setText(String.valueOf(rs.getInt("patient_id")));
	    				    inputAge.setText(String.valueOf(rs.getInt("patient_age")));
	    				     inputName.setText(rs.getString("patient_name"));
	    				     inputGender.setText(rs.getString("patient_gender"));
	    				     inputPh.setText(String.valueOf(rs.getInt("patient_ph")));
	    				     inputBill.setText(String.valueOf(rs.getInt("patient_cost")));
	    				     cost=rs.getInt("patient_cost");
	    				}

	    				
	    				PreparedStatement stmt1;
						ResultSet rs1;
						String sql1="select * from claimInsurance WHERE patient_id=?";
						stmt1=con.prepareStatement(sql1);
						stmt1.setInt(1, obj.getPatientId());
						rs1 = stmt1.executeQuery();
						
						while (rs1.next()) {
							 	
							InsuranceId=rs1.getInt("insurance_id");
							PolicyId=rs1.getInt("policy_id");
							
						}
						
						
						PreparedStatement stmt2;
						ResultSet rs2;
						String sql2="select * from insurance WHERE insurance_id=?";
						stmt2=con.prepareStatement(sql2);
						stmt2.setInt(1, InsuranceId);
						rs2 = stmt2.executeQuery();
						
						while(rs2.next()) {
							 inputInsurance.setText(String.valueOf(InsuranceId));
							    
							    inputCompany.setText(rs2.getString("company_name"));
							  
							     inputTitle.setText(rs2.getString("name"));
						}
						
						
						PreparedStatement stmt3;
						ResultSet rs3;
						String sql3="select * from Policy WHERE policy_id=?";
						stmt3=con.prepareStatement(sql3);
						stmt3.setInt(1, PolicyId);
						rs3 = stmt3.executeQuery();
						
						while(rs3.next()) {
							 inputPolicy.setText(String.valueOf(PolicyId));
							    
							    inputDescription.setText(rs3.getString("description"));
							    
							    inputPercentage.setText(String.valueOf(rs3.getInt("percentage")));
							  
							    System.out.println("cost:"+cost);
							    
							   int x = rs3.getInt("percentage");
							   System.out.println("x:"+x);
							   double h= new Double(x);
							   double y = (h/100);
							   System.out.println("y:"+y);
							   double z = y*cost;
							   System.out.println("z:"+z);
							   
						
	   		        	
							   
							   
							   double newCost=cost-z;
							   
							   int nc=(int)Math.round(newCost);
							   
							   inputNewBill.setText(String.valueOf(newCost));
							   
							   
								String sql4 = "UPDATE Patients SET patient_cost=?,insurance_status=? WHERE patient_id=?";
		   		        		PreparedStatement stmt4 = con.prepareStatement(sql4);
		   		        		stmt4.setInt(1, nc);
		   		        		stmt4.setInt(2, 0);
		   		        		stmt4.setInt(3, obj.getPatientId());
		   		      
		   		        		int rowsinserted2 = stmt4.executeUpdate();
		   		        		if(rowsinserted2>0)System.out.println("\nRecords Updated");
		   		        		
		   		        		
		   		        		String sql5 = "UPDATE claimInsurance SET i_status=? WHERE patient_id=?";
		   		        		PreparedStatement stmt5 = con.prepareStatement(sql5);
		   		        		stmt5.setString(1, "Claimed");
		   		        		stmt5.setInt(2, obj.getPatientId());
		   		      
		   		        		int rowsinserted3 = stmt5.executeUpdate();
		   		        		if(rowsinserted3>0)System.out.println("\nInsurance Status Updated");
							   
							   
						}
						
						
	    			
	    				con.close();
	    				}
	    				catch(Exception e) {
	    					System.out.println(e);
	    					
	    				}		
			
	  }
	  public void Paid(ActionEvent even)throws IOException{
		  String id = inputNewBill.getText();
		  LoginMassageBox.display("BILL PAID ", id+ " Following amount has been paid" );
		  ((Node) even.getSource()).getScene().getWindow().hide();

	        Stage primaryStage = new Stage();
	        Parent root = FXMLLoader.load(getClass().getResource("AdminReceptionCheck.fxml"));
	        primaryStage.setTitle("Hospital Management System");
	        primaryStage.setScene(new Scene(root, 720, 500));
	        primaryStage.show();
	  }


}
