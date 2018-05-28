// Airline Travel Scheduler - Airport
// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;

public class Airport
{

  public String port;
  public String connectTime;

  public Airport(String port, String connectTime) {
    this.port = port;
    this.connectTime = connectTime;
  }	// constructor

  public Airport(Airport port2){
    port = port2.port;
    connectTime = port2.connectTime;
  }

  public void print() {}

}
