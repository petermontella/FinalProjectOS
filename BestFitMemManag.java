
/**
 *
 * @author The Back Row
 */

import java.util.*;

public class BestFitMemManag {

    public static void main(String[] args) {

        ArrayList<MemObj> memSpace = new ArrayList();
        ArrayList<ProcessClass> proc = new ArrayList();

        memSpace.add(new MemObj(100)); //memory block; size parameter
        memSpace.add(new MemObj(500));
        memSpace.add(new MemObj(200));
        memSpace.add(new MemObj(300));
        memSpace.add(new MemObj(600));

        proc.add(new ProcessClass(1, 7, 345)); //process object. last number = size
        proc.add(new ProcessClass(3, 10, 400));
        proc.add(new ProcessClass(2, 1, 219));
        proc.add(new ProcessClass(4, 4, 175));
        proc.add(new ProcessClass(5, 9, 99));

        int b = memSpace.size(); //total memory size
        int p = proc.size(); //# of processes

        bestFit(memSpace, b, proc, p);

    } //end of main


    /*
Parameters: array list of memory blocks; number of memory blocks;
            array list of processes; number of processes            
     */
    public static void bestFit(ArrayList<MemObj> x, int mSp, ArrayList<ProcessClass> y, int pSi) {

        int[] allocation = new int[pSi]; //used to store taken memory blocks

        /*
    default each array value as -1
         */
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        /*
    Double loop. For each process, loop through memory blocks to see which 
    process fits the best.
         */
        for (int i = 0; i < pSi; i++) {
            int bInd = -1;
            for (int j = 0; j < mSp; j++) {
                if (x.get(j).getSize() >= y.get(i).getPrioNum()) {
                    if (bInd == -1) { //if default value is -1
                        bInd = j;
                    } //if mem slot j is >= process size &&
                    //bInd has a mem slot value
                    //if mem slot(bInd) > mem slot j
                    //make bInd, j
                    else if (x.get(bInd).getSize() > x.get(j).getSize()) {
                        bInd = j;
                    }
                }
            } //end of inner for loop
            if (bInd != -1) {
                allocation[i] = bInd;
                x.get(bInd).setSize(x.get(bInd).getSize() - y.get(i).getPrioNum());
            }

        } //end of outer for loop

        /*
    Print Process number, process size with taken memory blocks. -1 = not used.
         */
        System.out.println();
        for (int i = 0; i < pSi; i++) {
            System.out.println("Process Num: " + (i + 1) + " " + "Proc Size: " + (y.get(i).getPrioNum()));
            if (allocation[i] != -1) {
                System.out.println("Block: " + (allocation[i] + 1));

            } else {
                System.out.println("Not allocated.");
            }
            System.out.println();
        }

    } //end bestFit

} //end of class
