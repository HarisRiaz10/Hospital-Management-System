package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPassword;

    public void Login(ActionEvent even) throws Exception{

        String email = txtUser.getText();
        String password = txtPassword.getText();

        if(check(email, password)){
            LoginMassageBox.display("Login Status", "Login Successfully");

            ((Node)even.getSource()).getScene().getWindow().hide();

            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
            primaryStage.setTitle("Hospital Management System");
            primaryStage.setScene(new Scene(root, 720, 500));
            primaryStage.show();
        }
        else {
            LoginMassageBox.display("Login Status", "Wrong Password and try again");

        }
    }

    public static boolean check(String email, String password){

        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "select email,pass from admin where email=? and pass=?";

        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db?allowPublicKeyRetrieval=true","root","12345");
            pst = con.prepareStatement(query);
            pst.setString(1,email);
            pst.setString(2,password);
            rs = pst.executeQuery();

            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){

            return false;
        }
    }

    public void LogintoSignup(ActionEvent even) throws Exception{

        ((Node)even.getSource()).getScene().getWindow().hide();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        primaryStage.setTitle("Hospital Management System");
        primaryStage.setScene(new Scene(root, 720, 500));
        primaryStage.show();
    }

    public void RegtoLogin(ActionEvent even) throws Exception{

        ((Node)even.getSource()).getScene().getWindow().hide();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
        primaryStage.setTitle("Hospital Management System");
        primaryStage.setScene(new Scene(root, 720, 500));
        primaryStage.show();
    }

    @FXML
    private TextField ratxtname;

    @FXML
    private TextField ratxtpassword;

    @FXML
    private TextField ratxtemail;

    @FXML
    private TextField ratxtphone;

 /*   @FXML
    private RadioButton ratxtmale;
    @FXML
    private RadioButton ratxtfemale;

    @FXML
    private RadioButton ratxtother;
*/
    public void loginpage(ActionEvent even)throws Exception{
        String name = ratxtname.getText();
        String pass = ratxtpassword.getText();
        String email = ratxtemail.getText();
        String phone = ratxtphone.getText();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db?allowPublicKeyRetrieval=true&useSSL=false","root","12345");

            Statement stmt=con.createStatement();

            stmt.executeUpdate("INSERT INTO admin VALUES (id,'"+name+"','"+pass+"','"+email+"','"+phone+"')");

            LoginMassageBox.display("Registration Status", "Registration Successfully");

            ((Node)even.getSource()).getScene().getWindow().hide();

            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
            primaryStage.setTitle("Hospital Management System");
            primaryStage.setScene(new Scene(root, 720, 500));
            primaryStage.show();
            con.close();

        }catch(Exception e){ System.out.println(e);}

    }

}
