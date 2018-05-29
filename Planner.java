import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


// Airline Travel Scheduler - Planner
// Bongki Moon (bkmoon@snu.ac.kr)

public class Planner {

  public LinkedList<Airport> airportList;
  public LinkedList<Flight> flightList;
  private LinkedList<Airport> remainder;
  private Map<String, Integer> dist;
  private Map<String, String> path;
  private Map<String, Integer> wait;

  private Map<String, int[]> find;


  // constructor
  public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
    airportList = portList;
    flightList = fltList;
    remainder = new LinkedList<Airport>();
    dist = new HashMap<String, Integer>(portList.size());
    wait = new HashMap<String, Integer>(portList.size());

    for (Airport port : portList)
      wait.put(port.getPort(), port.getTime());

    for (Flight flt : fltList){
      find.put(flt.getSrc() + flt.getDest(), new int[]{flt.getStime(), flt.getDtime()});
    }
  }

  public Itinerary Schedule(String start, String end, String departure) {
    Airport tempPort;
    String src;
    String dest;
    int time;
    int alt;

    initPath();
    dist.put(start, 0);
    time = Integer.parseInt(departure);

    while (!remainder.isEmpty()) {
      tempPort = remainder.poll(); // Change to make findMin: Airport findMin()
      src = tempPort.getPort();
      for (Airport nbd : remainder){
        alt = dist.get(src) + dist_between(tempPort, nbd, time);
        if (alt < dist.get(nbd)){
          dist.put(nbd.getPort(), alt);
          path.put(nbd.getPort(), tempPort.getPort());
        }
      }
    }

    LinkedList<Flight> route = new LinkedList<Flight>();

    dest = end;
    src = path.get(dest);
    if (src == null){
      Itinerary trash = new Itinerary();
      return trash;
    }

    time = Integer.parseInt(departure);
    while (src != start) {
      Flight prev = backTrack(src, dest, time);
      route.add(prev);
      time = prev.getDtime();
      dest = src;
      src = path.get(dest);

      if (src == null){
        Itinerary trash = new Itinerary();
        return trash;
      }
    }

    Flight prev = backTrack(src, dest, time);
    route.add(prev);

    //Collections.reverse(route);
    Itinerary schedule = new Itinerary(route);

    return schedule;
  }

  private void initPath(){
    path.clear();
    for (Airport port : airportList){
      dist.put(port.getPort(), 2000000000);
      Airport port2 = new Airport(port);
      remainder.add(port2);
    }
  }

  private int dist_between(Airport u, Airport v, int startTime){
    // @TODO
    // Define distance

    return 2000000001;
  }

  private Flight backTrack(String start, String end, int departure){
    // @TODO
    // Find Flight 

    int startTime = 1;
    int endTime = 1;
    Flight value = new Flight(start, end, startTime, endTime);
    return value;
  }

}

/*

function Dijkstra(Graph, source):
    for each vertex v in Graph: 	// Initialization
        dist[v] := infinity 	// initial distance from source to vertex v is set to infinite
        previous[v] := undefined 	// Previous node in optimal path from source
    dist[source] := 0 	// Distance from source to source
    Q := the set of all nodes in Graph 	// all nodes in the graph are unoptimized - thus are in Q
    while Q is not empty: 	// main loop
        u := node in Q with smallest dist[ ]
        remove u from Q
        for each neighbor v of u: 	// where v has not yet been removed from Q.
            alt := dist[u] + dist_between(u, v)
            if alt < dist[v] 	// Relax (u,v)
                dist[v] := alt
                previous[v] := u
    return previous[ ] 
*/
