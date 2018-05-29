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

  public void setStime(int time){
    stime = time;
  }

  public int getDtime(){
    return dtime;
  }

  public void setDtime(int time){
    dtime = time;
  }

  public void print() {
    System.out.print("[" + src + "->" + dest + ":" + stimeName + "->" + dtimeName + "]");
  }

}
