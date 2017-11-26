
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

        System.out.println("Loading classes...");
        System.out.println("New user? y/n");
        String y = x.nextLine();

        if (y.charAt(0) == 'n') {
            System.out.print("Enter username: ");
            String z = x.nextLine();
            System.out.print("Enter password: ");
            String a = x.nextLine();
            if (sec.ver(z, a) == true) {
                System.out.println("Welcome.");
            }

        }
        if (y.charAt(0) == 'y') {
            System.out.print("Enter desired username: ");
            String user = x.nextLine();
            boolean bob = false;
           while (!bob) {
                if (sec.setUser(user).charAt(0) == 'S') {
                    System.out.println("Successful");
                    bob = true;
                }
            }

            System.out.print("Enter desired password: ");
            String pass = x.nextLine();
            sec.setPass(pass);
        }
        System.out.println("Chill.");
        
        
    } //end of main

} //end of class
