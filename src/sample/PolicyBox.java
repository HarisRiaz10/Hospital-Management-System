package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PolicyBox {

    public static void display(String title, Policy obj){
        Stage window = new Stage();
        window.setTitle(title);
        window.setWidth(300);
        window.setHeight(400);
        
        Label label = new Label();
        label.setText("Policy ID");
        Label label1 = new Label();
        label1.setText(String.valueOf(obj.getId()));
        Label label2 = new Label();
        label2.setText("Policy Title");
        Label label3 = new Label();
        label3.setText(obj.getTitle());
        Label label4 = new Label();
        label4.setText("Policy Description");
        Label label5 = new Label();
        label5.setText(obj.getDescription());
        Label label6 = new Label();
        label6.setText("Policy Percentage");
        Label label7 = new Label();
        label7.setText(String.valueOf(obj.getPercentage()));
        
        
        

        Button button = new Button("Close");
        button.setOnAction(e -> window.close());
        

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,label1,label2,label3,label4,label5,label6,label7,button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
