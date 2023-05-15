package sample;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.BeforeClass;
import org.junit.Test;

public class AdmissionControllerTest {
	AdmissionController obj= new AdmissionController();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
	  String[]  names= {"Kashfa"};
	  String[] name=new String[14];
		try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
    					System.out.println("Connection Established!");
    					
    					Statement stmt=con.createStatement();
    					ResultSet rs=stmt.executeQuery("select patient_name from Patients where patient_id=69");

int i=0;
    				while(rs.next()) {
    					
    					name[i]= rs.getString(1);
    					
	                       }}
    				catch(Exception e) {
    					System.out.println(e);
    				}
		assertEquals(names[0], name[0]);
	}
}