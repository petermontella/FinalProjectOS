package application;

import java.util.*;
import java.io.*;

public class securityClass {

    private static HashMap<Integer, String> user;
    private static HashMap<Integer, String> pass;
    private static HashMap<String, String> verify;

    private static File us;
    private static File pa;

    //private File storage = filePath
    public securityClass() {
        user = new HashMap();
        pass = new HashMap();
        verify = new HashMap();

        try {
            us = new File("C:\\Users\\Jonathan\\workspace\\txos\\application\\userNames.txt");
            pa = new File("C:\\Users\\Jonathan\\workspace\\txos\\application\\passwords.txt");
            System.out.println("it worked");
        } catch (Exception e) {
            System.out.println("Could not find un and pass files.");
        }

        try {
            Scanner x = new Scanner(new File("C:\\Users\\Jonathan\\workspace\\txos\\application\\userNames.txt"));

            int col;
            String col2;

            while (x.hasNextLine()) {
                col = x.nextInt();
                Integer xmute = col;
                //System.out.print(col);
                if (x.hasNext()) {
                    col2 = x.next();
                    //System.out.print(col2);

                    user.put(xmute, col2);
                }

            } //end of while

            x.close();
        } catch (IOException e) {
            System.out.println("Problem opening user file.");
        }
        try {
            Scanner y = new Scanner(new File("C:\\Users\\Jonathan\\workspace\\txos\\application\\passwords.txt"));

            int coll;
            String coll2;

            while (y.hasNextInt()) {
                coll = y.nextInt();
                Integer xmute2 = coll;
                //System.out.print(coll);
                if (y.hasNext()) {
                    coll2 = y.next();
                    //System.out.println(coll2);

                    pass.put(xmute2, coll2);
                }
            }

            y.close();
        } catch (IOException e) {
            System.out.println("Problem opening password file.");
        }
    }//end of constructor

    public String setUser(String x) {

        if (user.containsKey(hashF(x))) {
            //open file to check username
            return "Username already in system.";

        } else {
            //write to file
            FileWriter fw = null;
            BufferedWriter bw = null;
            PrintWriter out = null;
            try {
            	System.out.println("writing worked");
                fw = new FileWriter("C:\\Users\\Jonathan\\workspace\\txos\\application\\userNames.txt", true);
                bw = new BufferedWriter(fw);
                out = new PrintWriter(bw);
                out.println();
                out.print(hashF(x) + " " + x);
                
                out.close();
            } catch (IOException e) {
                System.out.println("Couldn't write to file. Out.");
            } finally{
             try{
                 if(fw !=null)
                     fw.close();
             } catch (IOException e){
                 System.out.println("FW error");
             }
             try{
                 if(bw!=null)
                     bw.close();
             }catch(IOException e){
                 System.out.println("bw error");
             }
            }//end of finally

            user.put(hashF(x), x);

            return "Successful.";
        }
    }

    public void setPass(String x) {
        //write to file
        
        FileWriter fw = null;
            BufferedWriter bw = null;
            PrintWriter out = null;
            try {
                fw = new FileWriter("C:\\Users\\Jonathan\\workspace\\txos\\application\\passwords.txt", true);
                bw = new BufferedWriter(fw);
                out = new PrintWriter(bw);
                out.println();
                out.print(hashF(x) + " " + x);
                
                out.close();
            } catch (IOException e) {
                System.out.println("Couldn't write to file. Out.");
            } finally{
             try{
                 if(fw !=null)
                     fw.close();
             } catch (IOException e){
                 System.out.println("FW error");
             }
             try{
                 if(bw!=null)
                     bw.close();
             }catch(IOException e){
                 System.out.println("bw error");
             }
            }//end of finally
        
        
        pass.put(hashF(x), x);
        System.out.println("Successful.");
    }

    public String getUser(String x) {
    //	if(x != null) {
    	return user.get(hashF(x));
    	//} else {
     ////   System.out.println("user null");
    	//}
		//return "ufailed";
    }

    public String getPass(String x) {
    	//if (x != null) {
        return pass.get(hashF(x));
    	//} else {
    	//	System.out.println("pass null");
    //	}
		//return " pfailed";
    }

    public boolean ver(String x, String y) {
    	//if(x == null || y == null) {
    		
    	boolean test;
    		test = (user.get(hashF(x)).equals(x)) && (pass.get(hashF(y)).equals(y));
    	//} else {
    	
        return test;
    	
    	
    }

    public int hashF(String x) {
        int hash = 17;

        for (int i = 0; i < x.length(); i++) {
            hash = hash * 13 + x.charAt(i);
        }
        return hash;
    }

}