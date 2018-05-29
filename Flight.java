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
    this.stime = HourToMin(Integer.parseInt(stime));
    this.dtime = HourToMin(Integer.parseInt(dtime));
  }

  public Flight(String src, String dest, int stime, int dtime){
    this.src = src;
    this.dest = dest;
    this.stime = stime;
    this.dtime = dtime;
  }


  private int HourToMin(int time){
    int day = time/10000;
    int hour = time%10000;
    hour = hour/100;
    int min = time%100;
    return 1440*day + 60*hour + min;
  }

  private int MinToHour(int time){
    int day = time/1440;
    int hour = time/60;
    int min = time%60;

    if (min == 60){
      min = 0;
      hour++;
    }

    if (hour == 0){
      hour = 24;
      day--;
    }
    return 10000*day + 100*hour + min;
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

    int a = MinToHour(stime);
    int b = MinToHour(dtime);

    System.out.print("[" + src + "->" + dest + ":");
    if (a < 1000) System.out.print("0");
    System.out.print(stime + "->");
    if (b < 1000) System.out.print("0");
    System.out.print(dtime + "]");
  }

}
