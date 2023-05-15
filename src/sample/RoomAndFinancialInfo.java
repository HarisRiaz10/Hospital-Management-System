package sample;
import javafx.event.ActionEvent;import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import java.util.Date;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RoomAndFinancialInfo  {
	
	 @FXML
    private TextField txtRoomNo;

    @FXML
    private TextField txtProblemType;

    @FXML
    private TextField txtDoctorId;

    @FXML
    private TextField txtDoctorName;

    @FXML
    private TextField txtStaffId;

    @FXML
    private TextField txtStaffName;

    @FXML
    private TextField txtStaffWorkName;
    
    @FXML
    private TableView<fin> Fin;
    @FXML
    private TableColumn<fin, String> Id;

    @FXML
    private TableColumn<fin, String> name;

    @FXML
    private TableColumn<fin, Boolean> insurance;

    @FXML
    private TableColumn<fin, String> com;
    @FXML
    private TableColumn<fin, Integer> percentage;
    
   

    
    
   

    //Search code

    public void SearchRoom(ActionEvent even)throws IOException {

        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String Id = null, FirstName = null;
        Id = txtRoomNo.getText();
        //FirstName = txtFirstName.getText();

        String query = "select RId, ProblemType, DId, DoctorName, SId, StaffName, StaffWorkType from room where  RId = ?  ";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db?allowPublicKeyRetrieval=true","root","12345");

            Statement stmt=con.createStatement();
            prst = con.prepareStatement(query);
            prst.setString(1,Id);
            //prst.setString(2,FirstName);
            rs = prst.executeQuery();

            while(rs.next()){

                //    String DId = rs.getString("DId");
                String ProblemType = rs.getString("ProblemType");
                String DId = rs.getString("DId");
                String DoctorName = rs.getString("DoctorName");
                String SId = rs.getString("SId");
                String StaffName = rs.getString("StaffName");
                String StaffWorkType = rs.getString("StaffWorkType");

                txtProblemType.setText(ProblemType);
                txtDoctorId.setText(DId);
                txtDoctorName.setText(DoctorName);
                txtStaffId.setText(SId);
                txtStaffName.setText(StaffName);
                txtStaffWorkName.setText(StaffWorkType);

            }

        }catch(Exception e){ System.out.println(e);}
    }


    //Patient Table Code  
        
        
        
      
     
    public void PatientTable(ActionEvent even) throws IOException {
        ((Node)even.getSource()).getScene().getWindow().hide();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Patient.fxml"));
        primaryStage.setTitle("Hospital Management System");
        primaryStage.setScene(new Scene(root, 1080, 640));
        primaryStage.show();
    }



    //Back Code

    public void Backmain(ActionEvent even) throws IOException {
        ((Node)even.getSource()).getScene().getWindow().hide();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ReceptionPanel.fxml"));
        primaryStage.setTitle("Hospital Management System");
        primaryStage.setScene(new Scene(root, 720, 500));
        primaryStage.show();
    }
    
    ObservableList<fin> ob = FXCollections.observableArrayList();

	public void how() {
		
		String sql="select  patient_id, patient_name, insurance_status, company_name, percentage from policy , insurance, Patients where policy.policy_id=insurance.policy_id;";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap", "root", "12345");

			Statement stmt=con.createStatement();
			ResultSet r=stmt.executeQuery(sql);
			while (r.next()) {
               ob.add(new fin(r.getString("Id"),r.getString("name"),r.getString("com"),r.getInt("percentage"),r.getBoolean("insurance")));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		insurance.setCellValueFactory(new PropertyValueFactory<>("insurance"));
		com.setCellValueFactory(new PropertyValueFactory<>("com"));
		percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));
		
		Fin.setItems(ob);
		System.out.println("fuck this shit");
	}

}
