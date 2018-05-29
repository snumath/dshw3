// Airline Travel Scheduler - Airport
// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;

public class Airport
{

  private LinkedList<Flight> flights;
  private String portName;
  private int connectTime;
  private int index;
  static int num = 0;

  public Airport(String portName, String connectTime) {
    this.portName = portName;
    int min = Integer.parseInt(connectTime);
    int hour = min/100;
    min = min%100;
    this.connectTime = 60*hour + min;
    flights = new LinkedList<Flight>();
    index = num++;
  }	// constructor

  public addFlight(Flight flt){
    flights.add(flt);
  }

  public String getPort(){
    return portName;
  }

  public int getTime(){
    return connectTime;
  }

  public int getIndex(int index){
    return index;
  }

  public void print() {}

}