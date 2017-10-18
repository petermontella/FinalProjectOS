
/**
 *
 * @author Bob Olkowitz
 */
import java.io.*;

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
                System.out.println("File exists bro.");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    } //end of create

    public void rename(String nName) {
        try {
            File z = new File(nName);
            if (namez.renameTo(z)) {
                namez = z;
                System.out.println("Success.");
            } else {
                System.out.println("Fail.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end of rename
    
    public void rename(String old, String nName) {
        try {
            File o = new File(old);
            File z = new File(nName);
            if (o.renameTo(z)) {
                z = o;
                o.delete();
                System.out.println("Success.");
            } else {
                System.out.println("Fail.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//rename method1

    public void move(String toPath) {
        try {
            File x = new File(toPath);
            if (x.exists()) {
                System.out.println("Sorry, exists.");
            } else if (x.createNewFile()) {
                namez.delete();
                namez = x;
                System.out.println("Nice.");
            } else {
                System.out.println("Could not move.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    } //end of move

    public void move(String from, String toPath) {
        try {
            File x = new File(from);
            File y = new File(toPath);
            if (y.exists()) {
                System.out.println("Sorry, exists.");
            } else if (y.createNewFile()) {
                x.delete();
                namez = y;
                System.out.println("Nice.");
            } else {
                System.out.println("Could not move.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //end of move1

    public void update() {

    }

    public void copy() {
        try {
            
            File z = new File(path);
            if (z.createNewFile()) {
                System.out.println("Success.");
            } else {
                System.out.println("Fail.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //end of copy
    
    public void copy(String fName, String toPath) {
        try {
            File x = new File(fName);
            File y = new File(fName);
            File z = new File(toPath);
            y=x;
            if (!z.exists()) {
                if(z.createNewFile()){
                    z=y;
                    y.delete();
                System.out.println("Success.");
                }
            } else {
                System.out.println("Fail.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //end of copy1
    

    public void delete() {
        try {
            if (namez.exists()) {
                namez.delete();
                System.out.println("File deleted.");
            } else {
                System.out.println("File does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //end of delete

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
