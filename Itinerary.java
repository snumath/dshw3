import java.util.LinkedList;

// Airline Travel Scheduler - Itinerary
// Bongki Moon (bkmoon@snu.ac.kr)

public class Itinerary
{

  LinkedList<Flight> schedule;

  // constructor
  Itinerary() {
  }

  public boolean isFound() {}

  public void print() {
    for (Flight transfer : schedule)
      transfer.print();
    System.out.println("");
  }

}
