// Airline Travel Scheduler - Flight
// Bongki Moon (bkmoon@snu.ac.kr)

public class Flight
{

  public String src;
  public String dest;
  public int stime;
  public int dtime;

  // constructor
  public Flight(){}

  public Flight(String src){
    this.src = src;
  }

  public Flight(String src, String dest, String stime, String dtime) {
    this.src = src;
    this.dest = dest;
    //this.stime = stime;
    //this.dtime = dtime;
    this.stime = Integer.parseInt(stime);
    this.dtime = Integer.parseInt(dtime);
  }

  public void print() {
    System.out.print("[" + src + "->" + dest + ":");
    if (stime < 1000) System.out.print("0");
    System.out.print(stime + "->");
    if (dtime < 1000) System.out.print("0");
    System.out.print(dtime + "]");
  }

}
