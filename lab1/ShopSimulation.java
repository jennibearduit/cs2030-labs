import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Jennifer (B03)
 * @version CS2030 AY20/21 ST1
 */

class ShopSimulation extends Simulation {

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;

  /**
   * A collection of service counters.
   */
  private Shop shop;


  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public ShopSimulation(Scanner sc) {
    int numOfCustomers = sc.nextInt();
    this.initEvents = new Event[numOfCustomers];

    int numOfCounters = sc.nextInt();
    this.shop = new Shop(numOfCounters);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      Customer customer = new Customer(id, serviceTime);
      initEvents[id] = new Arrival(arrivalTime, this.shop, customer);
      id++;
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
