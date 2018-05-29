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
    int time = 60 * Integer.parseInt(connectTime.substring(0,2));
    time = time + Integer.parseInt(connectTime.substring(2));
    this.connectTime = time;
    flights = new LinkedList<Flight>();
    index = num++;
  }	// constructor

  public void addFlight(Flight flt){
    flights.add(flt);
  }

  public void update(int time){
    for (Flight flt : flights)
      flt.update(time);
  }

  public LinkedList<Flight> getFlights(){
    return flights;
  }

  public String getPort(){
    return portName;
  }

  public int getTime(){
    return connectTime;
  }

  public int getIndex(){
    return index;
  }

  public void print() {}

}