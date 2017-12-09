/**
 *
 * @author The Back Row
 * Boot Class
 *
 */

import java.util.*;

public class boot {
    
    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        securityClass sec = new securityClass();

        System.out.println("New user? y/n");
        String y = x.nextLine();
        
        if (y.charAt(0) == 'n' || y.charAt(0) == 'N') {
            boolean john = false;
            while(!john){
                System.out.print("Enter username: ");
                String z = x.nextLine();
                if(z.equals("~")){
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                System.out.print("Enter password: ");
                String a = x.nextLine();
                try{
                    if(z.equals("~") || a.equals("~")){
                        System.exit(0);
                    }
                    
                    if (sec.ver(z, a) == true) {
                        System.out.println("Welcome.");
                        john = true;
                    }
                } catch (Exception e){
                    
                    System.out.println("Invalid. Enter '`' to escape.");
                }
            }
        }
        else if (y.charAt(0) == 'y' || y.charAt(0)=='Y') {
            
            boolean bob = false;
            while (!bob) {
                System.out.print("Enter desired username: ");
                String user = x.nextLine();
                if (sec.setUser(user).charAt(0) == 'S') {
                    System.out.println("Successful");
                    bob = true;
                } else {
                    System.out.println("Name already taken.");
                }
            }
            
            System.out.print("Enter desired password: ");
            String pass = x.nextLine();
            sec.setPass(pass);
        }else {
            System.out.println("Invalid entry, start over.");
            System.exit(0);
        }
        
        System.out.println("Welcome to the TX Operating System\n"+
                           "Here are the six possible commands: "+
                           "create, delete, copy, rename, open, shutdown");
        System.out.println();
        System.out.println("Enter one of the six commands.\n"+
                "shutdown closes the system.\n"+
                "create and delete require entering the file path to utilize these functions.\n"+
                "copy, rename, and move require two inputs. press \"enter\" after each one.");
        
        boolean pete = false;
        String felly;
        String sel;
        String john;
        
        while(!pete){
            felly = x.nextLine();
            if(felly.equals("shutdown")){
                System.out.println("Goodbye!");
                System.exit(0);
            }
                         
                switch(felly){
                        
                    case "create":
                        sel = x.nextLine();
                        FileManagerConstr b = new FileManagerConstr();
                        b.create(sel);
                        
                        break;
                    case "delete":
                        sel = x.nextLine();
                        FileManagerConstr c = new FileManagerConstr();
                        c.delete(sel);
                        break;
                    case "copy":
                        sel = x.nextLine();
                        john = x.nextLine();
                        FileManagerConstr d = new FileManagerConstr();
                        d.copy(sel, john);
                        break;
                    case "rename":
                        sel = x.nextLine();
                        john = x.nextLine();
                        FileManagerConstr e = new FileManagerConstr();
                        e.rename(sel, john);
                        break;
                    case "move":
                        sel = x.nextLine();
                        john = x.nextLine();
                        FileManagerConstr f = new FileManagerConstr();
                        f.move(sel, john);
                        break;
                    case "shutdown":
                        pete = true;
                        System.out.println("Goodbye.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid command.");
                        
                        break;
                        
                } //end of switch
          
           
            
        }//end of while
        System.out.println("Goodbye");
        System.exit(0);
    } //end of main
    
} //end of class

