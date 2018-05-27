// Airline Travel Scheduler - Flight
// Bongki Moon (bkmoon@snu.ac.kr)

public class Flight
{

  String src;
  String dest;
  int stime;
  int dtime;

  // constructor
  public Flight(String src, String dest, String stime, String dtime) {
    this.src = src;
    this.dest = dest;
    this.stime = Integer.parseInt(stime);
    this.dtime = Integer.parseInt(dtime);
  }

  public void print() {}

}
