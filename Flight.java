// Airline Travel Scheduler - Flight
// Bongki Moon (bkmoon@snu.ac.kr)

public class Flight
{

  private String src;
  private String dest;
  private int stime;
  private int dtime;

  // constructor
  public Flight(){}

  public Flight(String src){
    this.src = src;
  }

  public Flight(String src, String dest, String stime, String dtime) {
    this.src = src;
    this.dest = dest;
    this.stime = Integer.parseInt(stime);
    this.dtime = Integer.parseInt(dtime);
  }

  public Flight(String src, String dest, int stime, int dtime){
    this.src = src;
    this.dest = dest;
    this.stime = stime;
    this.dtime = dtime;
  }


  public String getSrc(){
    return src;
  }

  public void setSrc(String src){
    this.src = src;
  }

  public String getDest(){
    return dest;
  }

  public void setDest(String dest){
    this.dest = dest;
  }

  public int getStime(){
    return stime;
  }

  public void setStime(int stime){
    this.stime = stime;
  }

  public int getDtime(){
    return dtime;
  }

  public void setDtime(int dtime){
    this.dtime = dtime;
  }

  public void print() {
    System.out.print("[" + src + "->" + dest + ":");
    if (stime < 1000) System.out.print("0");
    System.out.print(stime + "->");
    if (dtime < 1000) System.out.print("0");
    System.out.print(dtime + "]");
  }

}
