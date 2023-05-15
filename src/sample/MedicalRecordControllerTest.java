package sample;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.BeforeClass;
import org.junit.Test;

public class MedicalRecordControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		String ed="2021-12-16", rd="2021-12-10";
		int no=49;
		try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
    					System.out.println("Connection Established!");

		PreparedStatement stmt1;
		ResultSet rs1;
		String sql1="select * from Admission WHERE patient_id=77";
		stmt1=con.prepareStatement(sql1);
		
		rs1 = stmt1.executeQuery();
		
		while (rs1.next()) {
			 	int a=rs1.getInt("room_no");
				 String b= rs1.getString("reg_date");
				  String  c= rs1.getString("end_date");
				assertEquals(ed,c);
				assertEquals(rd,b);
				assertEquals(no,a);
			
		}
	
		con.close();
		}
		catch(Exception e) {
			System.out.println(e);
			
		}		
	}

}
