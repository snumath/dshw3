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

    ListIterator<Flight> iter = fltList.listIterator();
		while (iter.hasNext()) {
			Flight flt = iter.next();
			Airport port = hash.get(flt.getSrc());
			if (port == null)
				continue;
			if (flt.getSrc().equals(port.getPort())){
				port.addFlight(flt);
				iter.remove();
			}
    }
  }

  public Itinerary Schedule(String start, String end, String departure) {
    for (int i = 0; i < numPort; ++i){
      flights[i] = null;
      visited[i] = false;
    }

    int time;
    Airport src = StoA.get(start);
    Airport dest = StoA.get(end);
    if (src == null || dest == null) return new Itinerary();

    int startIndex = src.getIndex();
    int endIndex = dest.getIndex();

    remainder[startIndex] = false;

    while (remainder[endIndex]){
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
      node = flights[hash.get(flt.getSrc).getIndex()];
      if (flt.getSrc.equals(start)) break;
    }

    if (flt.getSrc() != flt.getDest()) route.add(flt);

    return new Itinerary(route);
  }

  private void update(Airport port){
    int time = flights[port.getIndex()].getDtime() + port.getTime();
    Flight value;

    LinkedList<Flight> route = port.getFlights();

    for (Flight node : route){
      Airport dest = StoA.get(node.getDest());
      value = flights[dest.getIndex()];

      if (dest == null) continue;
      if (value == null) flights[dest.getIndex()] = node;
      else if (node.getDtime() > value.getDtime()) flights[dest.getIndex()] = node;
    }
  }

  private Flight findMin(){
    Flight result = null;
    Flight value = null;
    for (int i = 0; i < numPort; ++i){
      value = flights[i];
      if (value != null && queue[i] == true){
        if (result == null) result = value;
      }
    }
    return result;
  }
}