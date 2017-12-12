
/**
 *
 * @author Bob Olkowitz
 */
import java.io.*;
import org.apache.commons.io.FileUtils;


public class FileManagerConstr {

    private File namez;
    private String path = "";

    public FileManagerConstr() {

    }

    public File getFile() {
        
        return namez;
    }

    public String getPath() {
        return path;
    }

    public void create(String path1) {
        try {
            File x = new File(path1);
            path = path1;

            if (x.createNewFile()) {
                System.out.println("File created successfully.");
                namez = x;
            } else {
                System.out.println("Could not create file. File exists.");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    } //end of create

  
    
    public void rename(String old, String nName) {
        try {
            File o = new File(old);
            File z = new File(nName);
            if (o.renameTo(z)) {
                
                System.out.println("Success.");
            } else {
                System.out.println("Fail.");
            }
        } catch (Exception e) {
            System.out.println("Fail.");
        }
    }//rename method1

    

    public void move(String from, String toPath) {
        try {
            File x = new File(from);
            File y = new File(toPath);
            
                FileUtils.moveFileToDirectory(x, y, true);
                System.out.println("Success.");
            
        } catch (Exception e) {
            System.out.println("Fail.");
        }
    } //end of move1


    
    public void copy(String fName, String toPath) {
        try {
            File x = new File(fName);
            File z = new File(toPath);
           
          
            FileUtils.copyFile(x, z);
            System.out.println("Success.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fail. Trying entering a filename, not a directory.");
        }
    } //end of copy1
    


    public void delete(String name) {
        try {
            File x = new File(name);
            if (x.exists()) {
                x.delete();
                System.out.println("Success.");
            } else {
                System.out.println("Fail.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

} //end of class
