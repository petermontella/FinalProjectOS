
/**
 *
 * @author The Back Row
 */

import java.util.*;

public class BestFitMemManag {
    
    private static int totalMemSize = 4000; //megabytes
    private final static int BLOCKS = 6;
    private static ArrayList<MemObj> memSpace;
    private ProcessClass process;
    
    public BestFitMemManag(){
       
         memSpace= new ArrayList();
        

        memSpace.add(new MemObj(100,0)); //memory block; size parameter
        memSpace.add(new MemObj(900,1));
        memSpace.add(new MemObj(1200,2));
        memSpace.add(new MemObj(800,3));
        memSpace.add(new MemObj(1600,4));
        memSpace.add(new MemObj(400,5));

      
    } //end of constructor
    
    public int getMem(){
        return totalMemSize;
    }

    public void setProcess(ProcessClass x){
        process =x;
    }
    
    public ProcessClass getProcess(){
        return process;
    }
    
    public int bestFit(ProcessClass x) {

        int pSi = x.getSize();
        int allocation = -1;
        int memWaste=0;
        int memWaste1;
        int daBlock=0;
        boolean count = false;
        
        for(int i=0;i<BLOCKS;i++){
            if(pSi>memSpace.get(i).getSize()){
                
            }
            else{
                if(count == false){
                memWaste = ((memSpace.get(i).getSize())-pSi);
                daBlock = memSpace.get(i).getBnum();
                allocation++;
                count = true;
                }
                else{
                    memWaste1 = ((memSpace.get(i).getSize())-pSi);
                    if(memWaste > memWaste1){
                        memWaste = memWaste1;
                        allocation++;
                        daBlock = memSpace.get(i).getBnum();
                    }
                }
                
            }//end of outer else
            
            
        }//end of for loop
        
        if(memWaste == 0 && allocation==-1){
            System.out.println("Process too large. No space.");
            return -1;
        }
        else{
            MemObj z = memSpace.get(daBlock);
            z.setSize(memWaste);
            
            totalMemSize-=pSi;
            
            System.out.println("Success. "+ x.toString()+ " stored in Memory block: "+ daBlock);
            return daBlock;
        }
        

    } //end bestFit
    
    public void reallocate(ProcessClass x){
        MemObj y = memSpace.get(x.getBlock());
        y.setSize(y.getSize()+x.getSize());
        totalMemSize +=x.getSize();
        System.out.println("Complete. Total memory size: "+totalMemSize+" Memory block "+x.getBlock()+ 
                " size: "+y.getSize());
    }

} //end of class
