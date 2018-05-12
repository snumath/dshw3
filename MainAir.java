// Test Main for Flight Scheduler by Dijkstra's Short Path Algoritm
//
// Bongki Moon (bkmoon@snu.ac.kr)


import java.io.*;
import java.util.*;
import java.lang.Object;
import java.lang.management.*;

public class MainAir {
  public static void main(String args[]) throws IOException
  {
    int numAirports=0, numFlights=0;
    int numCustomers=0, numItineraries=0;

    if (args.length != 3) {
       System.err.println("Usage: java Main airports flights sched_request");
       System.exit(0);
    }

    TextInputStream afs = new TextInputStream(args[0]);
    TextInputStream ffs = new TextInputStream(args[1]);
    TextInputStream sfs = new TextInputStream(args[2]);

    LinkedList<Airport> airportList = new LinkedList<Airport>();
    LinkedList<Flight> flightList = new LinkedList<Flight>();

    while(afs.ready()) {
	String portName = afs.readWord();
	String conTime = afs.readWord();
	Airport port = new Airport(portName,conTime);
	airportList.add(port);
	numAirports++;
    }

    while(ffs.ready()) {
	String src = ffs.readWord();
	String dst = ffs.readWord();
	String deptTime = ffs.readWord();
	String arvlTime = ffs.readWord();
	Flight flt = new Flight(src,dst,deptTime,arvlTime);
	flightList.add(flt);
	numFlights++;
    }

    long planning = System.currentTimeMillis();
    Planner planner = new Planner(airportList,flightList);
    planning = System.currentTimeMillis() - planning;

    long scheduling = System.currentTimeMillis();
    while(sfs.ready()) {
	String src = sfs.readWord();
	String dst = sfs.readWord();
	String deptTime = sfs.readWord();

	System.out.println(">> Source:"+src+" Destination:"+dst
		+" Start_Time:"+deptTime);

	Itinerary ticket = planner.Schedule(src,dst,deptTime);
	if (ticket.isFound()) numItineraries++;
	numCustomers++;

	ticket.print();
    }
    scheduling = System.currentTimeMillis() - scheduling;

    System.out.println();
    System.out.println("Failed to find a flight schedule for "
	+(numCustomers-numItineraries)+" out of "+numCustomers+" customers");

    System.out.println("Elapsed times: "+planning+" (millsec) for planning, "
	+scheduling+" (millisec) for scheduling");

    Runtime runtime = Runtime.getRuntime();
    System.out.println("Memory consumption: "
                + (runtime.totalMemory() - runtime.freeMemory()) + " bytes");

    ThreadMXBean TMB = ManagementFactory.getThreadMXBean();
    if (TMB.isThreadCpuTimeSupported()) {
	long cputime;
	cputime = TMB.getCurrentThreadCpuTime();
	System.out.println("Total CPU time consumed by the thread: "
		+ (cputime/1000000)+" (millsec)");
    }
  }
}
