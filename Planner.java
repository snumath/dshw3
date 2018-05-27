import java.util.LinkedList;

// Airline Travel Scheduler - Planner
// Bongki Moon (bkmoon@snu.ac.kr)

public class Planner {

  public LinkedList<Airport> airportList;
  public LinkedList<Flight> flightList;


  // constructor
  public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
    airportList = portList;
    flightList = fltList;
  }

  public Itinerary Schedule(String start, String end, String departure) {
    initPath();
    Itinerary schedule = new Itinerary();




    return schedule;
  }

  private void initPath(){
    time = 0;
  }

  private void updatePath(){

  }

  private Flight fineMin(){
    Flight path = new Flight();
  }

}

