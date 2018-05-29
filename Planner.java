import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

// @TODO
// Make path: using Flight,Flight

// Airline Travel Scheduler - Planner
// Bongki Moon (bkmoon@snu.ac.kr)

public class Planner {

  private Flight[] flights;
  private boolean[] remainder;
  private HashMap<String, Airport> StoA;
  private int numPort;

  // constructor
  public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
    numPort = fltList.size();
		flights = new Flight[numPort];
    remainder = new boolean[numPort];
    StoA = new HashMap<String, Airport>();

    for (Airport port : portList){
      StoA.put(port.getPort(), port);
    }

    //ListIterator<Flight> iter = fltList.listIterator();
    Iterator<Flight> iter = fltList.iterator();
		while (iter.hasNext()) {
			Flight flt = iter.next();
			Airport port = StoA.get(flt.getSrc());
			if (port == null)
				continue;
			if (flt.getSrc().equals(port.getPort()) == true){
				port.addFlight(flt);
				iter.remove();
			}
    }
  }

  public Itinerary Schedule(String start, String end, String departure) {
    for (int i = 0; i < numPort; ++i){
      flights[i] = null;
      remainder[i] = true;
    }

    Airport src = StoA.get(start);
    Airport dest = StoA.get(end);
    if (src == null || dest == null) return new Itinerary();

    int startIndex = src.getIndex();
    int endIndex = dest.getIndex();

    int time = 60 * Integer.parseInt(departure.substring(0,2));
    time = time + Integer.parseInt(departure.substring(2));

    remainder[startIndex] = false;
    flights[startIndex] = new Flight(start, start, time, time - src.getTime());
    update(src);

    while (remainder[endIndex] == true){
      Flight flt = findMin();
      if (flt == null) return new Itinerary();

      Airport arrive = StoA.get(flt.getDest());
      flights[arrive.getIndex()] = flt;
      remainder[arrive.getIndex()] = false;
      update(arrive);
    }

    LinkedList<Flight> route = new LinkedList<Flight>();
    Flight node = flights[endIndex];

    while (true){
      route.add(node);
      node = flights[StoA.get(node.getSrc()).getIndex()];
      if (node.getSrc().equals(start))
        break;
    }

    if (node.getSrc() != node.getDest()) route.add(node);

    return new Itinerary(route);
  }

  private void update(Airport port){
    int time = flights[port.getIndex()].getDtime() + port.getTime();
    int index;
    port.update(time);

    LinkedList<Flight> route = port.getFlights();

    for (Flight node : route){
      Airport dest = StoA.get(node.getDest());

      if (dest == null) continue;
      index = dest.getIndex();
      if (flights[index] == null)
        flights[index] = node;
      if (node.getDtime() < flights[index].getDtime())
        flights[index] = node;
    }
  }

  private Flight findMin(){
    Flight result = null;
    for (int i = 0; i < numPort; ++i){
      if (flights[i] != null && remainder[i] == true){
        if (result == null)
          result = flights[i];
        else if(result.getDtime() > flights[i].getDtime())
          result = flights[i];
      }
    }
    return result;
  }
}