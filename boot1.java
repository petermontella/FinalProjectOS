
/**
 *
 * @author The Back Row
 * Boot Class for GUI integration.
 *
 */

import java.util.*;

public class boot1 {

    public boot1() {
        
        
        Scanner x = new Scanner(System.in);
        securityClass sec = new securityClass();

        System.out.println("New user? y/n");
        String y = x.nextLine();

        if (y.charAt(0) == 'n' || y.charAt(0) == 'N') {
            boolean john = false;
            while (!john) {
                System.out.print("Enter username: ");
                String z = x.nextLine();
                if (z.equals("~")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                System.out.print("Enter password: ");
                String a = x.nextLine();
                try {
                    if (z.equals("~") || a.equals("~")) {
                        System.exit(0);
                    }

                    if (sec.ver(z, a) == true) {
                        System.out.println("Welcome.");
                        john = true;
                    }
                } catch (Exception e) {

                    System.out.println("Invalid. Enter '`' to escape.");
                }
            }
        } else if (y.charAt(0) == 'y' || y.charAt(0) == 'Y') {

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
        } else {
            System.out.println("Invalid entry, start over.");
            System.exit(0);
        }

        System.out.println("Welcome to the TX Operating System\n"
                + "Here are the six possible commands: "
                + "create, delete, copy, rename, open, shutdown");
        System.out.println();
        System.out.println("Enter one of the six commands.\n"
                + "shutdown closes the system.\n"
                + "create and delete require entering the file path to utilize these functions.\n"
                + "copy, rename, and move require two inputs. press \"enter\" after each one.");
        BestFitMemManag memory = new BestFitMemManag();
        ProcessBatchQueue batch = new ProcessBatchQueue();

        System.out.println("Total system memory is: " + memory.getMem() + " kilobytes."
                + "");

        boolean pete = false;
        String felly;
        String sel;
        String john;
        int uID = 0;

        while (!pete) {
            felly = x.nextLine();
            if (felly.equals("shutdown")) {
                ProcessClass p = new ProcessClass(felly, 1,uID);
                uID++;
                batch.add(p);
                int dablock = memory.bestFit(p);
                p.setBlock(dablock);
                batch.exe();
                memory.reallocate(p);
                System.out.println("Goodbye!");
                System.exit(0);
            }

            switch (felly) {

                case "create":
                    sel = x.nextLine();
                    FileManagerConstr b = new FileManagerConstr();
                    ProcessClass p = new ProcessClass(felly, sel, 450, uID);
                    uID++;
                    batch.add(p);
                    int dablock = memory.bestFit(p);
                    if(dablock==-1){
                        System.out.println("Try another command or deleting files you created.");
                        batch.exe();
                    }else{
                    batch.exe();
                    b.create(sel);
                    p.setBlock(dablock);
                    System.out.println("Total memory size: "+memory.getMem());
                    }
                    break;
                    
                case "delete":
                    sel = x.nextLine();
                    boolean tru = false;
                    int unID=-1;
                    while(!tru){
                    System.out.println("Enter file's unique ID: ");
                    if(x.hasNextLine()){
                        String temp1 = x.nextLine();
                    unID = Integer.parseInt(temp1);
                    tru = true;
                    }
                    else{
                        System.out.println("Invalid entry.");
                    }
                    
                    }//end of while
                    FileManagerConstr c = new FileManagerConstr();
                    ProcessClass q =batch.equals1(sel, unID);
                    if(q==null){
                        System.out.println("Could not find process.");
                        
                    } else{
                    batch.add(q);
                    if(q.getBlock()==-1){
                        System.out.println("Invalid block number.");
                        batch.exe();
                    } else{
                    memory.reallocate(q);
                    batch.exe();
                    c.delete(sel);
                    }//end of else
                    }//end of else2
                    break;
                case "copy":
                    sel = x.nextLine();
                    john = x.nextLine();
                    FileManagerConstr d = new FileManagerConstr();
                    
                    ProcessClass r = new ProcessClass(felly, sel, john, 450, uID);
                    uID++;
                    batch.add(r);
                    int dablock1 = memory.bestFit(r);
                    
                    if(dablock1==-1){
                        System.out.println("Invalid block number.");
                        batch.exe();
                        
                    } else{
                    
                    batch.exe();
                    d.copy(sel, john);
                    r.setBlock(dablock1);
                    System.out.println("Total memory size: "+memory.getMem());
                    }//end of else
                    
                    
                    break;
                case "rename":
                    sel = x.nextLine();
                    john = x.nextLine();
                    FileManagerConstr e = new FileManagerConstr();
                    ProcessClass s = new ProcessClass(felly, sel, john, 450, uID);
                    uID++;
                    batch.add(s);
                    int dablock2 = memory.bestFit(s);
                    
                    if(dablock2==-1){
                        System.out.println("Invalid block number.");
                        batch.exe();
                        
                    } else{
                    
                    batch.exe();
                    e.rename(sel, john);
                    boolean tru1 = false;
                    int unID1=-1;
                    while(!tru1){
                    System.out.println("Enter original file's unique ID: ");
                    if(x.hasNextLine()){
                        String temp = x.nextLine();
                    unID1 = Integer.parseInt(temp);
                    tru1 = true;
                    }
                    else{
                        System.out.println("Invalid entry.");
                    }
                    
                    }//end of while
                    ProcessClass del = batch.equals1(sel, unID1);
                    
                    memory.reallocate(del);
                    batch.remove(del);
                    s.setBlock(dablock2);
                    System.out.println("Total memory size: "+memory.getMem());
                    }//end of else
                    
                    break;
                    
                case "move":
                    sel = x.nextLine();
                    john = x.nextLine();
                    FileManagerConstr f = new FileManagerConstr();
                    ProcessClass t = new ProcessClass(felly, sel, john, 450, uID);
                    uID++;
                    batch.add(t);
                    int dablock3 = memory.bestFit(t);
                    
                    if(dablock3==-1){
                        System.out.println("Invalid block number.");
                        batch.exe();
                        
                    } else{
                    
                    batch.exe();
                    f.move(sel, john);
                    boolean tru2 = false;
                    int unID2=-1;
                    while(!tru2){
                    System.out.println("Enter original file's unique ID: ");
                    if(x.hasNextLine()){
                        String temp = x.nextLine();
                    unID2 = Integer.parseInt(temp);
                    tru2 = true;
                    }
                    else{
                        System.out.println("Invalid entry.");
                    }
                    
                    }//end of while
                    ProcessClass del = batch.equals1(sel, unID2);
                    
                    memory.reallocate(del);
                    batch.remove(del);
                    t.setBlock(dablock3);
                    System.out.println("Total memory size: "+memory.getMem());
                    }//end of else
                    
                    break;
                    
                              
                default:
                    System.out.println("Invalid command.");

                    break;

            } //end of switch

        }//end of while
        System.out.println("Goodbye");
        System.exit(0);
    } //end of constructor

} //end of class

