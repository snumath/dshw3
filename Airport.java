// Airline Travel Scheduler - Airport
// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;

public class Airport
{

  private String portName;
  private int connectTime;

  public Airport(String portName, String connectTime) {
    this.portName = portName;
    this.connectTime = Integer.parseInt(connectTime);
  }	// constructor

  public Airport(Airport port){
    portName = port.getPort();
    connectTime = port.getTime();
  }

  public String getPort(){
    return portName;
  }

  public void setPort(String portName){
    this.portName = portName;
  }

  public int getTime(){
    return connectTime;
  }

  public void setTime(int connectTime){
    this.connectTime = connectTime;
  }

  public void print() {}

}
