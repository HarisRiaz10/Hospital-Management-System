package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class WardController implements Initializable {

	
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private TableView<Wards> wards;

    @FXML
    private TableColumn<Wards, Integer> idCol;

    @FXML
    private TableColumn<Wards, String> typeCol;

    @FXML
    private TableColumn<Wards, String> nameCol;
    
    @FXML
    private TableColumn<Wards, Integer> roomCol;
    
    @FXML
    private TableColumn<Wards, Integer> costCol;


    @FXML
    private TextField inputId;

    @FXML
    private TextField inputType;

    @FXML
    private TextField inputName;
    
    @FXML
    private TextField inputRoom;
    
    @FXML
    private TextField inputCost;
    
    @FXML
    private TextField inputDays;
    
    @FXML
    private DatePicker inputDate;
    
 

    public static int id;
    public static String type;
    public static String name;
    public static int rooms;
    public static int cost;
    public static LocalDate rDate;
    public static int roomNo;
    public static int noOfDays;
    
    public static int getNoOfDays() {
		return noOfDays;
	}

	public static void setNoOfDays(int noOfDays) {
		WardController.noOfDays = noOfDays;
	}

	public static int getRoomNo() {
		return roomNo;
	}

	public static void setRoomNo(int roomNo) {
		WardController.roomNo = roomNo;
	}

	public LocalDate getrDate() {
		return rDate;
	}

	public static void setrDate(LocalDate rDate) {
		WardController.rDate = rDate;
	}

	public static int getCost() {
		return cost;
	}

	public static void setCost(int cost) {
		WardController.cost = cost;
	}

	


    public static int getRooms() {
		return rooms;
	}

	public static void setRooms(int rooms) {
		WardController.rooms = rooms;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		WardController.id = id;
	}

	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		WardController.type = type;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		WardController.name = name;
	}


	
	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<Wards, Integer>("id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Wards, String>("type"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Wards, String>("name"));
        roomCol.setCellValueFactory(new PropertyValueFactory<Wards, Integer>("room"));
        costCol.setCellValueFactory(new PropertyValueFactory<Wards, Integer>("cost"));
		setupTable();
        
    }

    @FXML
    void submit(ActionEvent event) {
        ObservableList<Wards> currentTableData = wards.getItems();
        int currentWardId = Integer.parseInt(inputId.getText());

        setrDate(inputDate.getValue());
        
        for (Wards ward1 : currentTableData) {
            if(ward1.getId() == currentWardId) {
            	
            	if (ward1.getRoom()>0) {
                ward1.setType(inputType.getText());
                ward1.setName(inputName.getText());
                int x=Integer.parseInt(inputRoom.getText());
                int cc=Integer.parseInt(inputCost.getText());
                
                
            	Exceptions HS=new Exceptions();

            	
            	
                
            	
            	try {
        			HS.validateDays(Integer.parseInt(inputDays.getText()));
        			  int uu=Integer.parseInt(inputDays.getText());
        			  setNoOfDays(uu);
        			  int realcost=cc*uu;
                      setCost(realcost);
                      
        			
        
            	
                
              
               
                
                ward1.setCost(cc);
                ward1.setRoom(ward1.getRoom());
                setRoomNo(ward1.getRoom());
                ward1.decR();
                
                try {
            		Class.forName("com.mysql.cj.jdbc.Driver");
            		
            		System.out.println("Driver update loaded");
        			
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
        			
        			System.out.println("Connection Established!");
        		String sql = "UPDATE Wards SET ward_rooms=? WHERE ward_id=?";
        		PreparedStatement stmt = con.prepareStatement(sql);
        		stmt.setInt(1, ward1.getRoom());
        		stmt.setInt(2, ward1.getId());
        
        		
        		
        		int rowsinserted = stmt.executeUpdate();
        		if(rowsinserted>0)System.out.println("\nRoom Updated");
        		
        		//updating data (overwrite)
	        		
	        		Statement stmt9=con.createStatement();
					ResultSet rs9=stmt9.executeQuery("select * from Wards");
				
				int first1=0;
	        		FileWriter fw1 ;
	        		
	        		
	        		while(rs9.next()) {
   					
	        			int id= rs9.getInt(1);
    					String wardT = rs9.getString("ward_type");
    					String wardN = rs9.getString("ward_name");
    					int wardR = rs9.getInt("ward_rooms");
    					int cost = rs9.getInt("cost");
   					
   					//file handling
   	        		try
   					{
   						String filename= "Wards.txt";
   						
   						if(first1==0) {
       					    fw1 = new FileWriter(filename,false);
       					    first1=1;
       						}else {
       							fw1 = new FileWriter(filename,true);
       						}
   					     //the true will append the new data
   						fw1.write("Id: " + " " + id + " " +  "Type: "+ " "+wardT + " "+ "Name: "+ " "+ wardN + " "+ "Rooms: "+ " "+ wardR + " "+ "Cost: "+ " "+ cost + "\n");//appends the string to the file
					    fw1.close();
   					}
   					catch (IOException e) {
   					      System.out.println("An error occurred.");
   					      e.printStackTrace();
   					    }
   	        		
	        		}
   	        		//end
	        		
	        		
	        		///
	        	
        		
        		con.close();
            				}
            				catch(Exception e) {
            					System.out.println(e);
            					
            				}		
            			
                
                setId(currentWardId);
                setType(inputType.getText());
                setName(inputName.getText());
                setRooms(Integer.parseInt(inputRoom.getText()));
                
		}catch(UnknownAdmissionDaysException e){
        			
        			System.out.println("Days are Undefined");
        			LoginMassageBox.display("Exception","Days arent Correct");
        			
        		}
             

                wards.setItems(currentTableData);
                wards.refresh();
                break;
            	}else {
            		LoginMassageBox.display("No rooms available","Enter Valid");
            	
            	}
            }
        }
        
        
        
        
        
        
    }
    
    public void proceedNext(ActionEvent event) throws IOException {
    	FXMLLoader abc = new FXMLLoader(getClass().getResource("ConfirmAdd.fxml"));
    	root = abc.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
    }
    
   

    @FXML
    void rowClicked(MouseEvent event) {
        Wards clickedWard = wards.getSelectionModel().getSelectedItem();
        inputId.setText(String.valueOf(clickedWard.getId()));
        inputType.setText(String.valueOf(clickedWard.getType()));
        inputName.setText(String.valueOf(clickedWard.getName()));
        inputRoom.setText(String.valueOf(clickedWard.getRoom()));
        inputCost.setText(String.valueOf(clickedWard.getCost()));
    }

    private void setupTable(){
    	
    	Wards[] wardsArr=new Wards[15];
    	int i=0;
    	
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    					
    					System.out.println("Driver loaded");
    					
    					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","12345");
    					
    					System.out.println("Connection Established!");
    					
    					Statement stmt=con.createStatement();
    					ResultSet rs=stmt.executeQuery("select * from Wards");
    				String sql = "select * from Wards";

    				while(rs.next()) {
    					
    					int id= rs.getInt(1);
    					String wardT = rs.getString("ward_type");
    					String wardN = rs.getString("ward_name");
    					int wardR = rs.getInt("ward_rooms");
    					int cost = rs.getInt("cost");
    					
    					
    					wardsArr[i] = new Wards(id,wardT,wardN,wardR,cost);
    					wards.getItems().addAll(wardsArr[i]);
    					
    					
    					//file handling
    	        		try
    					{
    						String filename= "Wards.txt";
    						
    						File file = new File(filename);
							
    						if(!(file.exists())) {
    						
    					    FileWriter fw = new FileWriter(filename,true);
    					     //the true will append the new data
    					    fw.write("Id: " + " " + id + " " +  "Type: "+ " "+wardT + " "+ "Name: "+ " "+ wardN + " "+ "Rooms: "+ " "+ wardR + " "+ "Cost: "+ " "+ cost + "\n");//appends the string to the file
    					    fw.close();
    					}else {
    						System.out.println("File Already Exists");
    					}
    						
    					}
    					catch (IOException e) {
    					      System.out.println("An error occurred.");
    					      e.printStackTrace();
    					    }
    	        		//end
    					
    				}

    				
    				con.close();
    				}
    				catch(Exception e) {
    					System.out.println(e);
    					
    				}		

    }
    
    
	public void Menu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ReceptionPanel.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}