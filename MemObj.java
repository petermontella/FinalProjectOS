
/**
 *
 * @author The Back Row
 */

public class MemObj {

    private int size;
    private final int bNum;

    public MemObj(int partitionSize, int blockNum) {
        size = partitionSize;
        bNum = blockNum;
    }

    public int getSize() {
        return size;
    }
    public int getBnum(){
        return bNum;
    }

    public void setSize(int partSize) {
        size = partSize;
    }

    @Override
    public String toString() {
        return "Current memory size: " + size+ " Block Number: "+bNum;
    }

}
