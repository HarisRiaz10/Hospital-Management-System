package sample;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.BeforeClass;
import org.junit.Test;

public class LoginControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		String e="Kashfa@gmail.com", pas="1234";
		 PreparedStatement pst = null;
	        ResultSet rs = null;
	        String query = "select email,pass from admin where email='Kashfa@gmail.com' and pass=1234";

	        try{

	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/db?allowPublicKeyRetrieval=true","root","12345");
	            pst = con.prepareStatement(query);
	            rs = pst.executeQuery();

	            if(rs.next()){
	                String email=rs.getString("email");
	                String pass=rs.getString(2);
	                assertEquals(e,email);
	                assertEquals(pas,pass);
	                
	            }
	            
	        }catch(Exception r) {
				System.out.println(r);
	           }
	}

}
