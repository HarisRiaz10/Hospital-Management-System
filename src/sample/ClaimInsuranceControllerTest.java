package sample;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.BeforeClass;
import org.junit.Test;

public class ClaimInsuranceControllerTest {
	ClaimInsuranceController obj= new ClaimInsuranceController();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		int[] Id=new int [2];
		int[] policy=new int[2];
		int cid=4,cid1=3, cpolicy1=4, cpolicy=1;
		try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
		PreparedStatement stmt1;
		ResultSet rs1;
		String sql1="select * from claimInsurance WHERE patient_id=69";
		stmt1=con.prepareStatement(sql1);
		rs1 = stmt1.executeQuery();
		int i=0;
		while (rs1.next()) {
			 	
			Id[i]=rs1.getInt("insurance_id");
			policy[i]=rs1.getInt("policy_id");
			i++;
		}
		assertEquals(cid,Id[0]);
		assertEquals(cid1,Id[1]);
		assertEquals(cpolicy1,Id[0]);
		assertEquals(cpolicy,policy[1]);
	}
		catch(Exception e) {
			System.out.println(e);
			
		}		
	
	}
		


}
