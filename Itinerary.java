import java.util.LinkedList;

// Airline Travel Scheduler - Itinerary
// Bongki Moon (bkmoon@snu.ac.kr)

public class Itinerary
{
  
  public LinkedList<Flight> schedule;

  // constructor
  Itinerary() {
  }

  public boolean isFound() {
    if (schedule.isEmpty()) return false;

    return true;
  }

  public void print() {

    if (isFound()){
      System.out.println("No Flight Schedule Found.");
      return;
    }
    for (Flight transfer : schedule)
      transfer.print();
    System.out.println("");
  }

}
