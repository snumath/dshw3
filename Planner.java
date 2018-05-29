import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

// @TODO
// Make path: using Flight,Flight

// Airline Travel Scheduler - Planner
// Bongki Moon (bkmoon@snu.ac.kr)

public class Planner {

  public LinkedList<Airport> airportList;
  public LinkedList<Flight> flightList;
  private LinkedList<Airport> remainder;
  private Map<String, Integer> dist;
  private Map<String, Integer> wait;
  private Map<String, Flight> path;
  private Map<String, LinkedList<int[]>> find;


  // constructor
  public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
    airportList = portList;
    flightList = fltList;
    remainder = new LinkedList<Airport>();
    dist = new HashMap<String, Integer>(portList.size());
    wait = new HashMap<String, Integer>(portList.size());
    find = new HashMap<String, LinkedList<int[]>>();
    path = new HashMap<String, Flight>();
    

    for (Airport port : portList){
      wait.put(port.getPort(), port.getTime());
    }


    Iterator<Flight> it = fltList.iterator();
    Flight tempFlight;

    while(it.hasNext()){
      tempFlight = it.next();
      String tempString = tempFlight.getSrc() + tempFlight.getDest();
      LinkedList<int[]> findTemp = new LinkedList<int[]>();
      findTemp.add(new int[]{tempFlight.getStime(), tempFlight.getDtime()});
      while(tempString == tempFlight.getSrc() + tempFlight.getDest()){
        findTemp.add(new int[]{tempFlight.getStime(), tempFlight.getDtime()});
        tempFlight = it.next();
      }
      find.put(tempString, findTemp);
    }
  }

  public Itinerary Schedule(String start, String end, String departure) {
    Airport tempPort;
    Flight tempFlight;
    String src;
    String dest;
    int time;
    int alt;

    initPath();
    time = Integer.parseInt(departure);
    alt = time/100;
    time = time%100;
    time = 60 * alt + time;
    dist.put(start, time);

    for (Airport port : remainder){
      if (port.getPort() == start){
        tempPort = port;
        remainder.remove(port);
      }
    }

    for (Iterator<Airport> iter = remainder.iterator(); iter.hasNext();){
      Airport port = iter.next();
      if (port.getPort() == start) {
        iter.remove();
      }
    }

    for (Airport nbd : remainder){
      LinkedList<int[]> timeTable = find.get(start + nbd.getPort());

      if (timeTable != null){

        int startCompTime;
        int endCompTime;

        int tempTime = 2000000000;
        int compTime = 2000000000;

        for(int[] timeSet : timeTable){
          startCompTime = timeSet[0];
          endCompTime = timeSet[1];

          compTime = endCompTime;
          if (startCompTime < time) compTime += 1440;

          if (compTime < tempTime){
            tempTime = compTime;
          }
        }
        dist.put(nbd.getPort(), time + compTime);
      }
    }
    
    while (!remainder.isEmpty()) {
      tempPort = findMin(remainder);
      System.out.println(tempPort.getPort());
      dest = tempPort.getPort();
      for (Airport nbd : remainder){
        src = nbd.getPort();
        if (find.get(src + dest) != null){
          time = dist.get(src)%1440;
          tempFlight = nearFlight(nbd, tempPort, time);
          alt = dist.get(src) + dist_between(tempFlight);
          System.out.println(alt);
          if (alt < dist.get(dest)){
            System.out.println(1);
            dist.put(dest, alt);
            path.put(dest, tempFlight);
          }
        }
      }
    }


    LinkedList<Flight> route = new LinkedList<Flight>();

    dest = end;
    tempFlight = path.get(dest);
    if (tempFlight == null){
      Itinerary trash = new Itinerary();
      return trash;
    }

    while (tempFlight.getSrc() != start){
      route.add(tempFlight);

      dest = tempFlight.getSrc();
      tempFlight = path.get(dest);

      if (tempFlight == null){
        Itinerary trash = new Itinerary();
        return trash;
      }
    }

    route.add(tempFlight);

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

  private int dist_between(Flight flt){
    int a = flt.getStime();
    int b = flt.getDtime();
    int c = b - a;

    if (c < 0) c += 1440;

    return c;
  }

  /*
  private Flight backTrack(String start, String end, int departure){
    // @TODO
    // Find Flight 

    int startTime = 1;
    int endTime = 1;
    Flight value = new Flight(start, end, startTime, endTime);
    return value;
  }
  */

  private Airport findMin(LinkedList<Airport> rem){
    Iterator<Airport> iterValue = rem.iterator();
    Airport value = rem.getLast();
    Airport port;
    int comp;
    int distance = 2000000000;

    for(Iterator<Airport> iter = rem.iterator(); iter.hasNext();) {
      port = iter.next();
      comp = dist.get(port.getPort());
      if (comp < distance){
        distance = comp;
        value = port;
      }
    }

    rem.remove(value);
    return value;
  }

  private Flight nearFlight(Airport start, Airport end, int time){
    LinkedList<int[]> timeTable = find.get(start.getPort() + end.getPort());
    int startTime = 0;
    int endTime = 0;

    int startCompTime;
    int endCompTime;

    int tempTime = 2000000000;
    int compTime;

    for(int[] timeSet : timeTable){
      startCompTime = timeSet[0];
      endCompTime = timeSet[1];

      compTime = endCompTime;
      if (startTime < time) compTime += 1440;

      if (compTime < tempTime){
        tempTime = compTime;
        startTime = startCompTime;
        endTime = endCompTime;
      }
    }
    Flight value = new Flight(start.getPort(), end.getPort(), startTime, endTime);
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
