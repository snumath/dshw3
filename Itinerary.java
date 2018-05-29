import java.util.LinkedList;

// Airline Travel Scheduler - Itinerary
// Bongki Moon (bkmoon@snu.ac.kr)

public class Itinerary
{
  
  public LinkedList<Flight> schedule;
  boolean existence = false;

  // constructor
  public Itinerary() {}

  public Itinerary(LinkedList<Flight> route){
    schedule = route;
    existence = true;
  }

  public boolean isFound(){
    return existence;
  }


  public void print() {

    if (existence == false){
      System.out.println("No Flight Schedule Found.");
      return;
    }
    for (Flight transfer : schedule)
      transfer.print();
    System.out.println("");
  }
}
