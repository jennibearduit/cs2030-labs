/**
 * CS2030 Lab 3: Departure.java
 * This class represents a departure event in shop simulation.
 *
 * @author Jennifer (B03)
 */
public class Departure extends Event {
  /** The customer associated with this departure event. */ 
  private Customer customer;
  
  /**
   * Constructor for a departure event.
   *
   * @param time The time at which this departure event occurs.
   * @param customer The customer associated with this departure event.
   */
  public Departure(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }
  
  /**
   * Retrieve an array of events for the simulator.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] simulate() { 
    return new Event[] { };
  }

  /** 
   * Return the string representation of this departure event.
   *
   * @return The string representation of this departure event.
   */
  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " departed";
  }
  
}
