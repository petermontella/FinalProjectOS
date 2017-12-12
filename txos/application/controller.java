package application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class controller {
	
	@FXML private Button sign;
	@FXML private Button create;
	@FXML private TextField user;
	@FXML private PasswordField pass;
	@FXML private Text error;
	@FXML private ToggleButton ascii;
	@FXML private ImageView img;
	@FXML private Button pow;
	File file = new File("C:\\Users\\Jonathan\\workspace\\txos\\application\\ascii.png");
    Image image = new Image(file.toURI().toString());
    File powImg = new File("C:\\Users\\Jonathan\\workspace\\txos\\application\\pow.png");
    Image image2 = new Image(powImg.toURI().toString());
    File def = new File("C:\\Users\\Jonathan\\workspace\\txos\\application\\bg.jpg");
    Image image3 = new Image(def.toURI().toString());
    
    
    
	@FXML
	private void openSceneB(ActionEvent event) throws IOException { 
	Parent root = FXMLLoader.load(getClass().getResource("/application/signup.fxml"));
	Scene scene = new Scene(root);
	Stage stage = new Stage(StageStyle.DECORATED);
	stage.setTitle("Create an Account");
	stage.setScene(scene);
	stage.show();
	
	Stage stage2 = (Stage) create.getScene().getWindow();
    stage2.close();
	
	}

	
	@FXML
	private void openSceneC(ActionEvent event) throws IOException { 
		securityClass sec = new securityClass();
		Scanner x = new Scanner(System.in);
		String z = "";
		String a = "";
		z = user.getText();
		a = pass.getText();
		if (sec.ver(z, a) == true) {
             System.out.println("Welcome.");
              Parent root2 = FXMLLoader.load(getClass().getResource("/application/os.fxml"));
              Scene scene2 = new Scene(root2);
              Stage stage2 = new Stage(StageStyle.DECORATED);
              stage2.setTitle("Operating System");
              stage2.setScene(scene2);
              stage2.show();
              
              Stage stage = (Stage) sign.getScene().getWindow();
              stage.close();
              user.clear();
              pass.clear();
              
          } else {
        	  error.setText("Invalid Username or password!");
        	  System.out.println("doesnt work");
          }
		
		
	}
	
	@FXML
	private void ascii(ActionEvent event3) throws IOException {
       
		if(ascii.isSelected() == true) {
		img.setImage(image);
		} else {
			img.setImage(image3);
		}
	}

}
