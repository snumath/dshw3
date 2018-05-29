import java.util.LinkedList;

// Airline Travel Scheduler - Itinerary
// Bongki Moon (bkmoon@snu.ac.kr)

public class Itinerary
{
  
  public LinkedList<Flight> schedule;

  // constructor
  public Itinerary() {}

  public Itinerary(LinkedList<Flight> route){
    schedule = route;
  }

  public boolean isFound(){
    return schedule.isEmpty();
  }


  public void print() {

    if (schedule.isEmpty()){
      System.out.println("No Flight Schedule Found.");
      return;
    }
    for (Flight transfer : schedule)
      transfer.print();
    System.out.println("");
  }

}
