// Airline Travel Scheduler - Flight
// Bongki Moon (bkmoon@snu.ac.kr)

public class Flight
{

  private String src;
  private String dest;
  private int stime;
  private int dtime;
  private final String stimeName;
  private final String dtimeName;

  // constructor

  public Flight(String src, String dest, String stime, String dtime) {
    this.src = src;
    this.dest = dest;
    this.stime = HourToMin(Integer.parseInt(stime));
    this.dtime = HourToMin(Integer.parseInt(dtime));
    stimeName = stime;
    dtimeName = dtime;
  }

  public Flight(String src, String dest, int stime, int dtime) {
    this.src = src;
    this.dest = dest;
    this.stime = stime;
    this.dtime = dtime;
    stimeName = "10000000";
    dtimeName = "10000000";
  }

  private int HourToMin(int time){
    int day = time/10000;
    int hour = time%10000;
    hour = hour/100;
    int min = time%100;
    return 1440*day + 60*hour + min;
  }

  public String getSrc(){
    return src;
  }

  public String getDest(){
    return dest;
  }

  public int getStime(){
    return stime;
  }

  public int getDtime(){
    return dtime;
  }

  public void update(int time){
    int day = time/1440;
    stime = stime % 1440;
    dtime = dtime % 1440;
    stime += day * 1440;
    dtime += day * 1440;
    if(stime < time){
      stime += 1440;
      dtime += 1440;
    }
    if(dtime < stime){
      dtime += 1440;
    }
  }

  public void print() {
    System.out.print("[" + src + "->" + dest + ":" + stimeName + "->" + dtimeName + "]");
  }

}
