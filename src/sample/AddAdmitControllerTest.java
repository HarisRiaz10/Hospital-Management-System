package sample;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.junit.BeforeClass;
import org.junit.Test;

public class AddAdmitControllerTest {
	AddAdmitController obj = new AddAdmitController();
   
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testIndex() {
		 obj.setIndex(3);
		 assertEquals(obj.getIndex(),3);
	}

	@Test
	public void testData() {
		String[] name= {"mujtaba","ali","aaema","azka","azka","huzziafa","Maryam","Kashfa","Musa","Zainab","ali","aaemaa","aaema","azka"};
		String[] names=new String[14];
		try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
    					System.out.println("Connection Established!");
    			
    					Statement stmt=con.createStatement();
    					ResultSet rs=stmt.executeQuery("select patient_name from Patients");
    					
    					boolean found=false;
int i=0;
    				while(rs.next()) {
    					
    					
    					names[i]= rs.getString(1);
    					i++;
    				}
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
	for(int i=0; i<10; i++)
	{
		assertEquals(name[i],names[i]);
	}
	}         	
	}
