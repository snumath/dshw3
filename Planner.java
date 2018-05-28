import java.util.LinkedList;
import java.util.PriorityQueue;

// Airline Travel Scheduler - Planner
// Bongki Moon (bkmoon@snu.ac.kr)

public class Planner {

  public LinkedList<Airport> airportList;
  public LinkedList<Flight> flightList;
  private PriorityQueue<Airport> que;


  // constructor
  public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
    airportList = portList;
    flightList = fltList;
    for (Airport port : airportList){
      Airport port2 = new Airport(port);
      que.add(port2);
    }
  }

  public Itinerary Schedule(String start, String end, String departure) {
    initPath();
    Itinerary schedule = new Itinerary();

    return schedule;
  }

  private void initPath(){
    int time = 0;
  }


  private Flight shortestPath(String src){
    Flight path = new Flight(src);
    return path;
  }

}
