package application;

import java.awt.Dialog;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import com.sun.javafx.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class controller2 {
	
	@FXML private Button back;
	@FXML private Button create;
	@FXML private TextField user;
	@FXML private PasswordField pass;
	@FXML private Text error;

	@FXML
	private void back(ActionEvent event) throws IOException { 
	Parent root = FXMLLoader.load(getClass().getResource("/application/tx.fxml"));
	Scene scene = new Scene(root);
	Stage stage = new Stage(StageStyle.DECORATED);
	stage.setTitle("Login");
	stage.setScene(scene);
	stage.show();
	
	Stage stage2 = (Stage) back.getScene().getWindow();
    stage2.close();
	}
	
	@FXML
	private void crt(ActionEvent event2) throws IOException {
		Scanner x = new Scanner(System.in);
        securityClass sec = new securityClass();
        
       // System.out.println("New user? y/n");
        //String y = x.nextLine();

      //  if (y.charAt(0) == 'n' || y.charAt(0) == 'N') {
        //  boolean john = false;
           // while (!john) {
                //System.out.print("Enter username: ");
                //String z = x.nextLine();
            	String z = "";
            	z = user.getText();
                //if (z.equals("~")) {
               //     System.out.println("Goodbye!");
               //     System.exit(0);
             //   }
             //   System.out.print("Enter password: ");
               // String a = x.nextLine();
                String a = "";
                a = pass.getText();
              //  try {
                   // if (z.equals("~") || a.equals("~")) {
                //        System.exit(0);
                  //  }

                //   if (sec.ver(z, a) == true) {
                       // System.out.println("Welcome.");
                        //john = true;
                 //   }
                 //catch (Exception e) {

                 //   System.out.println("Invalid. Enter '`' to escape.");
               // }
            //} 
        //  if (y.charAt(0) == 'y' || y.charAt(0) == 'Y') {

          //  boolean bob = false;
           // while (!bob) {
               // System.out.print("Enter desired username: ");
                String user1 = "";
                user1 = user.getText();
                if (sec.setUser(user1).charAt(0) == 'S') {
                    System.out.print("Enter desired password: ");
            String pass1;
            pass1 = pass.getText();
            sec.setPass(pass1);
                	
                	System.out.println("Successful");
            
                } else {
                	error.setText("User Already Exist! Please Enter Another");
               
                   //System.out.println("Name already taken.");
                }
        
        	}/////create
}//main
