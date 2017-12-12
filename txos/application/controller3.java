package application;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.javafx.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class controller3 extends TreeItem<File>{

	@FXML private Button fileEx;
	@FXML private Button img2ascii;
	@FXML private Button cmd;
	@FXML private Button logOff;
	@FXML private Button power;
	@FXML private TreeView<String> fileView;
	@FXML private ImageView img;
	@FXML private TabPane tab;
	
	public String convert(final BufferedImage image, boolean negative) {

			StringBuilder sb = new StringBuilder((image.getWidth() + 1) * image.getHeight());
			for (int y = 0; y < image.getHeight(); y++) {
				if (sb.length() != 0) sb.append("\n");
				for (int x = 0; x < image.getWidth(); x++) {
					Color pixelColor = new Color(image.getRGB(x, y));
					double gValue = (double) pixelColor.getRed() * 0.2989 + (double) pixelColor.getBlue() * 0.5870 + (double) pixelColor.getGreen() * 0.1140;
					final char s = negative ? returnStrNeg(gValue) : returnStrPos(gValue);
					sb.append(s);
				}
			}
			return sb.toString();
		}
	
	private char returnStrPos(double g)//takes the grayscale value as parameter
	{
		final char str;

		if (g >= 230.0) {
			str = ' ';
		} else if (g >= 200.0) {
			str = '.';
		} else if (g >= 180.0) {
			str = '*';
		} else if (g >= 160.0) {
			str = ':';
		} else if (g >= 130.0) {
			str = 'o';
		} else if (g >= 100.0) {
			str = '&';
		} else if (g >= 70.0) {
			str = '8';
		} else if (g >= 50.0) {
			str = '#';
		} else {
			str = '@';
		}
		return str; // return the character

	}

	private char returnStrNeg(double g) {
		final char str;

		if (g >= 230.0) {
			str = '@';
		} else if (g >= 200.0) {
			str = '#';
		} else if (g >= 180.0) {
			str = '8';
		} else if (g >= 160.0) {
			str = '&';
		} else if (g >= 130.0) {
			str = 'o';
		} else if (g >= 100.0) {
			str = ':';
		} else if (g >= 70.0) {
			str = '*';
		} else if (g >= 50.0) {
			str = '.';
		} else {
			str = ' ';
		}
		return str;

	}
	
	@FXML
	private void fileEx(ActionEvent event) throws IOException { 
		tab.setOpacity(1);
		
		DirectoryChooser dc = new DirectoryChooser();
         dc.setInitialDirectory(new File(System.getProperty("user.home")));
         File choice = dc.showDialog(null);
         if(choice == null || ! choice.isDirectory()) {
             Alert alert = new Alert(AlertType.ERROR);
             alert.setHeaderText("Could not open directory");
             alert.setContentText("The file is invalid.");

             alert.showAndWait();
         } else {
             fileView.setRoot(getNodesForDirectory(choice));
         }
     };
     
     @FXML
     private void vanish(KeyEvent key) {
    	 tab.setOpacity(0);
     }
	
    public TreeItem<String> getNodesForDirectory(File directory) { 
     TreeItem<String> root = new TreeItem<String>(directory.getName());
     for(File f : directory.listFiles()) {
         System.out.println("Loading " + f.getName());
         if(f.isDirectory()) { //Then we call the function recursively
             root.getChildren().add(getNodesForDirectory(f));
         } else {
             root.getChildren().add(new TreeItem<String>(f.getName()));
         }
     }
     return root;

    }		
 
 @FXML
	private void commands(ActionEvent event2) throws IOException {
	    	
	    	TextInputDialog dialog = new TextInputDialog("");
	    	dialog.setTitle("COMMAND PROMPT");
	    	dialog.setHeaderText("");
	    	dialog.setContentText("Please Enter A Command:");
	    	dialog.show();
	    }
 
 	@FXML
 	private void img(ActionEvent event3) throws IOException {

 		
 		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "gif", "png"));
				while (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						File f = fileChooser.getSelectedFile();
						final BufferedImage image = ImageIO.read(f);
						if (image == null) throw new IllegalArgumentException(f + " is not a valid image.");
						final String ascii = new controller3().convert(image, false);
						final JTextArea textArea = new JTextArea(ascii, image.getHeight(), image.getWidth());
						textArea.setFont(new Font("Monospaced", Font.BOLD, 5));
						textArea.setEditable(false);
						final JDialog dialog = new JOptionPane(new JScrollPane(textArea), JOptionPane.PLAIN_MESSAGE).createDialog(controller3.class.getName());
						dialog.setResizable(true);
						dialog.setVisible(true);
						//mg.setImage(f);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				//}
				
			}
		}
 		});
 		
 		
        
       }//img
    
 	@FXML
 	private void logOff(ActionEvent event4) throws IOException {
 	// If the button is pressed go back to the login screen
 		Stage stage = (Stage) logOff.getScene().getWindow();
 	    // do what you have to do
 	    stage.close();
 	   Parent root = FXMLLoader.load(getClass().getResource("tx.fxml"));
		//Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setTitle("TXOS");
		stage.setScene(new Scene(root));
		stage.show();
 		
 	}
 	
 	@FXML
 	private void powerOff(ActionEvent event4) throws IOException {
 		//System.exit(0);
 		//FXMLLoader.
 		Platform.exit();
 	}
 	 
}//main




	


