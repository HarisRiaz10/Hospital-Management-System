package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddAdmitController {
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	static int index;
	static int index1;
	static int s=0;
	
	public static int getIndex1() {
		return index1;
	}

	public static void setIndex1(int index1) {
		AddAdmitController.index1 = index1;
	}

	public static void setIndex(int index) {
		AddAdmitController.index = index;
	}

	public static int getIndex() {
		return index;
	}

	public void IncrementIndex() {
		AddAdmitController.index++;
	}
	public void IncrementIndex1() {
		AddAdmitController.index1++;
	}
	

	public void Add(ActionEvent event) throws IOException{
		AdmissionController obj = new AdmissionController();
		WardController obj1 = new WardController();
		InsuranceController obj2 = new InsuranceController();
		
		boolean checkCost=false;
		boolean checkAdmission=false;
		int checkCost1;
		int realcost=0;
		
		System.out.println("RoomNo: " + obj1.getRoomNo());
		
		
		

//Patient		
		int y=obj1.cost+obj.cost;
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
    					System.out.println("Connection Established!");
    					
    					Statement stmt=con.createStatement();
    					ResultSet rs=stmt.executeQuery("select * from Patients");
    					
    					boolean found=false;

    				while(rs.next()) {
    					
    					int id= rs.getInt(1);
    					
    					if(id==obj.getId()) {
    						
    						found=true;
    					}
    					
    				}
    				
    					if (found==false) {
    						
    					String sql = "INSERT INTO Patients (patient_id,patient_name,patient_age,patient_gender,patient_cost,patient_ph,insurance_status) VALUES (?,?,?,?,?,?,?)";
    					PreparedStatement stmt1 = con.prepareStatement(sql);
    					stmt1.setInt(1,obj.getId());
    					stmt1.setString(2, obj.getName());
    					stmt1.setInt(3,obj.getAge());
    					stmt1.setString(4, obj.getGender());
    					stmt1.setInt(5,y);
    					stmt1.setInt(6,obj.getPh());
    					stmt1.setInt(7,obj.getStatus());
    					
    					String newS="";
    					if (obj.getStatus()==1) {
    						newS="Yes";
    					}else {
    						newS="No";
    					}
    					
    					int rowsinserted = stmt1.executeUpdate();
    	        		if(rowsinserted>0)System.out.println("\nPatient Added");
    	        		
    	        		//file handling
    	        		try
    					{
    						String filename= "Patients.txt";
    					    FileWriter fw = new FileWriter(filename,true);
    					     //the true will append the new data
    					    fw.write("Id: " + " " + obj.getId() + " " +  "Name: "+ " "+obj.getName() + " "+ "Age: "+ " "+ obj.getAge() + " "+ "Gender: "+ " "+ obj.getGender() + " "+ "Phone#: "+ " "+ obj.getPh() + " "+ "Bill: "+ " "+ y  + " "+ "Insurace Status:" + newS + "\n");//appends the string to the file
    					    fw.close();
    					}
    					catch (IOException e) {
    					      System.out.println("An error occurred.");
    					      e.printStackTrace();
    					    }
    	        		//end
    	        		
    	        		
    					
    				}else {
    					System.out.println("Patient Already Exists");
    					
    					

						try {
							
							
							
		            		Class.forName("com.mysql.cj.jdbc.Driver");
		            		
		            		System.out.println("Driver update loaded");
		        			
		        			Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
		        			
		        			System.out.println("Connection Established!");
		        			
		        			//if (checkAdmission==true){

	    					PreparedStatement stmt5;
	    					ResultSet rs5;
	    					String sql5="select patient_cost from Patients WHERE patient_id=?";
	    					stmt5=con1.prepareStatement(sql5);
	    					stmt5.setInt(1, obj.getId());
	    					rs5 = stmt5.executeQuery();
	    					
	    					

	    				while(rs5.next()) {
	    					
	    					realcost = rs5.getInt("patient_cost");
	    				}
		        			
    					
    					
    					checkCost1=y+realcost;
    					
    					System.out.println("CheckCOst:"+checkCost1);
    					System.out.println("RealCost:"+realcost);
    					System.out.println("Y:"+y);
    					
    					if (checkCost1>realcost) {
    						
    						
    						checkCost=true;
    						
    						
    					}else {
    						System.out.println("No Updation");
    					}
    					
						//}
    					PreparedStatement stmt6;
    					ResultSet rs6;
    					String sql6="select insurance_status from Patients WHERE patient_id=?";
    					stmt6=con1.prepareStatement(sql6);
    					stmt6.setInt(1, obj.getId());
    					rs6 = stmt6.executeQuery();
    					
    					int oldStatus=0;

    				while(rs6.next()) {
    					
    					oldStatus = rs6.getInt("insurance_status");
    				}
    					
    				
    				if(oldStatus==0 && obj.getStatus()==1) {
    					
    					int newStatus=1;
		        		String sql7 = "UPDATE Patients SET insurance_status=? WHERE patient_id=?";
		        		PreparedStatement stmt7 = con1.prepareStatement(sql7);
		        		stmt7.setInt(1, newStatus);
		        		stmt7.setInt(2, obj.getId());
		        
		        		
		        		
		        		int rowsinserted = stmt7.executeUpdate();
		        		if(rowsinserted>0)System.out.println("\nInsurance Updated");
		        		
		        		//updating data (overwrite)
   		        		
   		        		Statement stmt9=con.createStatement();
    					ResultSet rs9=stmt.executeQuery("select * from Patients");
    					
    					//int first1=0;
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
   	    						fw1 = new FileWriter(filename,false);
   	    						
//   	    						if(first1==0) {
//   	        					    fw1 = new FileWriter(filename,false);
//   	        					    first1=1;
//   	        						}else {
//   	        							fw1 = new FileWriter(filename,true);
//   	        							first1=0;
//   	        						}
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
   		        	
		        		
					}else {
						System.out.println("No Updation");
					}		
    		        		con.close();
    		            				}
    		            				catch(Exception e) {
    		            					System.out.println(e);
    		            					
    		            				}
    				}
    				con.close();
    	}catch(Exception e) {
    					System.out.println(e);
    					
    				}
    	
    	
    	
//Admission   	
    	
    	int count=0;
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
    					System.out.println("Connection Established!");
    					
    					boolean found1=false;

    					if (s==1) {
    					Statement stmt=con.createStatement();
    					ResultSet rs=stmt.executeQuery("select patient_id from Admission");
    					
    					
    					
    				while(rs.next()) {
    					
    					int id= rs.getInt(1);
    					
    					if(id==obj.getId()) {
    						
    						found1=true;
    					}
    					
    					count++;
    					
    					
    				}
    				
    					}
    				
    				s=1;
    				
    				System.out.println("FOUND:"+found1);
    				
    					if (found1==false) {
    						
    						
    					   	Configuration cont = new Configuration();
    				        cont.configure().addAnnotatedClass(Admit.class);
    				        SessionFactory sf= cont.buildSessionFactory();
    				        Session session= sf.openSession();
    				        Transaction trans= session.beginTransaction();
    				        String abc=obj1.getrDate().toString();
    				        LocalDate tomorrow = obj1.getrDate().plusDays(obj1.getNoOfDays());
        					String xyz=tomorrow.toString();
        					setIndex(count);
        					IncrementIndex();
    				        Admit c1 = (new Admit(getIndex(),obj.getId(),obj1.getId(),obj1.getRoomNo(),abc,xyz));
    						session.save(c1);
    						trans.commit();
//    						
//    					String sql8 = "INSERT INTO Admission (admission_id,patient_id,ward_id,room_no,reg_date,end_date) VALUES (?,?,?,?,?,?)";
//    					PreparedStatement stmt8 = con.prepareStatement(sql8);
//    					
//    					setIndex(count);
//    					IncrementIndex();
//    					stmt8.setInt(1,getIndex());
//    					stmt8.setInt(2,obj.getId());
//    					stmt8.setInt(3,obj1.getId());
//    					stmt8.setInt(4,obj1.getRoomNo());
//    					String abc=obj1.getrDate().toString();
//    					stmt8.setString(5, abc);
//    					
//    					LocalDate tomorrow = obj1.getrDate().plusDays(obj1.getNoOfDays());
//    					String xyz=tomorrow.toString();
//    					
//    					stmt8.setString(6, xyz);
//   
//    					
//    					int rowsinserted = stmt8.executeUpdate();
    						int rowsinserted=1;
    	        		if(rowsinserted>0) {
    	        			System.out.println("\nAdmit Successfully");
    	        			
    	        			//file handling
        	        		try
        					{
        						String filename= "Admission.txt";
        					    FileWriter fw = new FileWriter(filename,true);
        					     //the true will append the new data
        					    fw.write("Index: " + " " + getIndex() + " " +  "Patient Id: "+ " "+obj.getId() + " "+ "Ward Id: "+ " "+ obj1.getId() + " "+ "Room No: "+ " "+ obj1.getRoomNo() + " "+ "Registration Date: "+ " "+ abc + " "+ "End Date: "+ " "+ xyz  + "\n");//appends the string to the file
        					    fw.close();
        					}
        					catch (IOException e) {
        					      System.out.println("An error occurred.");
        					      e.printStackTrace();
        					    }
        	        		//end
        					
        				
    	        			
    	        			checkAdmission=true;
    	        			
    	        				if (checkCost==true) {
    	        					
    	
    	        					int newCost=y+realcost;
    	    		        		String sql = "UPDATE Patients SET patient_cost=? WHERE patient_id=?";
    	    		        		PreparedStatement stmt2 = con.prepareStatement(sql);
    	    		        		stmt2.setInt(1, newCost);
    	    		        		stmt2.setInt(2, obj.getId());
    	    		        
    	    		        		
    	    		        	
    	    		        		int rowsinserted2 = stmt2.executeUpdate();
    	    		        		if(rowsinserted2>0)System.out.println("\nCost Updated");
    	    		        		
    	    		        		//updating data (overwrite)
    		   		        		
    		   		        		Statement stmt10=con.createStatement();
    		    					ResultSet rs10=stmt10.executeQuery("select * from Patients");
    		    					
    		    					int first2=0;
    		   		        		FileWriter fw2 ;
    		   		        		
    		   		        		
    		   		        		while(rs10.next()) {
    		   	    					
    		   	    					int id1= rs10.getInt(1);
    		   	    					String pName = rs10.getString("patient_name");
    		   	    					int pAge = rs10.getInt("patient_age");
    		   	    					String pGender = rs10.getString("patient_gender");
    		   	    					int cost9=rs10.getInt("patient_cost");
    		   	    					int ph= rs10.getInt("patient_ph");
    		   	    					int st1= rs10.getInt("insurance_status");
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
    		   	    					  fw2 = new FileWriter(filename,false);
    		   	    						
//    		   	    						if(first2==0) {
//    		   	        					    fw2 = new FileWriter(filename,false);
//    		   	        					    first2=1;
//    		   	        						}else {
//    		   	        							fw2 = new FileWriter(filename,true);
//    		   	        							first2=0;
//    		   	        						}
    		   	    					     //the true will append the new data
    		   	    					    fw2.write("Id: " + " " + id1 + " " +  "Name: "+ " "+pName + " "+ "Age: "+ " "+ pAge + " "+ "Gender: "+ " "+ pGender + " "+ "Phone#: "+ " "+ ph + " "+ "Bill: "+ " "+ cost9  + " "+ "Insurace Status:" + newS + "\n");//appends the string to the file
    		   	    					    fw2.close();
    		   	    					}
    		   	    					catch (IOException e) {
    		   	    					      System.out.println("An error occurred.");
    		   	    					      e.printStackTrace();
    		   	    					    }
    		   	    	        		
    		   		        		}
    		   	    	        		//end
    		   		        		
    		   		        		
    		   		        		///
    		   		        	
    	        				}
    	        				

    				}
    					}else {
    					System.out.println("Admission Already Exists");
    				}
    					}catch(Exception e) {
    					System.out.println(e);
    					
    				}

		
		
    	
    	
    	
 //Insurance
    	
    	int count1=0;
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
    					System.out.println("Connection Established!");
    					
    					Statement stmt=con.createStatement();
    					ResultSet rs=stmt.executeQuery("select patient_id,i_status from claimInsurance");
    					
    					
    					boolean found1=false;

    				while(rs.next()) {
    					
    					int id= rs.getInt(1);
    					String abc=rs.getString("i_status");
    					
    					if(id==obj.getId()) {
    						
    						if (abc.equals("Not Claimed")) {
    						 found1=true;
    						}
    					}
    					
    					count1++;
    					
    					
    				}
    				
    					if (found1==false) {
    						
    					String sql8 = "INSERT INTO claimInsurance (c_id,patient_id,insurance_id,policy_id,i_status) VALUES (?,?,?,?,?)";
    					PreparedStatement stmt8 = con.prepareStatement(sql8);
    					
    					setIndex1(count1);
    					IncrementIndex1();
    					stmt8.setInt(1,getIndex1());
    					stmt8.setInt(2,obj.getId());
    					stmt8.setInt(3,obj2.getId());
    					stmt8.setInt(4,obj2.getPobj().getId());
    					stmt8.setString(5, "Not Claimed");
   
    					
    					int rowsinserted = stmt8.executeUpdate();
    	        		if(rowsinserted>0) {
    	        			System.out.println("\nInsurance Assigned Successfully");
    	        			
    	        			
    	        			//file handling
        	        		try
        					{
        						String filename= "ClaimInsurance.txt";
        					    FileWriter fw = new FileWriter(filename,true);
        					     //the true will append the new data
        					    fw.write("Index: " + " " + getIndex1() + " " +  "Patient Id: "+ " "+obj.getId() + " "+ "Insurance Id: "+ " "+ obj2.getId() + " "+ "Policy No: "+ " "+ obj2.getPobj().getId() + " "+ "Insurance Status: "+ " "+ "Not Claimed" + "\n");//appends the string to the file
        					    fw.close();
        					}
        					catch (IOException e) {
        					      System.out.println("An error occurred.");
        					      e.printStackTrace();
        					    }
        	        		//end
    	        			
    				}
    					}else {
    					System.out.println("Insurance Already Exists");
    				}
    					}catch(Exception e) {
    					System.out.println(e);
    					
    				}

		
    	
		
	}
	
	
	public void Next(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ConfirmAdmissionScreen.fxml"));
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
	
	public void MedicalRecord(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ConfirmAdmissionScreen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
}
