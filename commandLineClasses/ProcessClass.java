/**
 *
 * @author The Back Row
 */

public class ProcessClass {
 
    private String fCom;
    private String sCom;
    private String tCom;
    private int size;
    private int id=0;
    private int block=-1;
    private int uID;


    public ProcessClass(String x, int s, int unId){
        fCom = x;
        size =s;
        id = 1;
        uID=unId;
    }
    
    public ProcessClass(String x, String y, int s, int unId){
        fCom = x;
        sCom = y;
        size = s;
        id=2;
        uID=unId;
    }
    
    public ProcessClass(String x, String y, String z, int s, int unId){
        fCom = x;
        sCom = y;
        tCom = z;
        size =s;
        id=3;
        uID=unId;
    }
//end of Constructors 

  public String getC1(){
      return fCom;
  }
  public String getC2(){
      return sCom;
  }
  public String getC3(){
      return tCom;
  }
  
  public int getSize(){
      return size;
  }
  public int getID(){
      return id;
  }
  public int getBlock(){
      return block;
  }
  public void setBlock(int b){
      block =b;
  }
  public int getuID(){
      return uID;
  }

    @Override
    public String toString() {
        
        switch(id) {
            case 1:
                return "Process: "+fCom+" Size: "+size+ " Unique ID: "+uID;
            case 2:
                return "Process: "+fCom+" "+sCom+ " Size: "+size+ " Unique ID: "+uID;
            case 3:
                return "Process: "+fCom+" "+sCom+ " "+ tCom+"Size: "+size+ " Unique ID: "+uID;
            default:
                return "Invalid.";
            
        }
        
        
    }//end of toString


    
}//end of class


