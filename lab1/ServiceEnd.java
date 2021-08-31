/**
 * CS2030 Lab 1: ServiceEnd.java
 * This class represents a service end event in shop simulation.
 *
 * @author Jennifer (B03)
 */
class ServiceEnd extends Event {
  /** The customer associated with this service end event. */
  private Customer customer;

  /** The counter associated with this service end event. */
  private Counter counter;
  
  /** 
   * Constructor for a service end event.
   *
   * @param time The time this service end event occurs.
   * @param customer The customer associated with this service end event.
   * @param counter The counter associated with this service end event.
   */
  public ServiceEnd(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }
  
  /**
   * Retrieve an array of events for the simulator.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] simulate(){
    this.counter.endService();
    return new Event[] {
      new Departure(this.getTime(), this.customer)
    };
  }

  /**
   * Return the string representation of this service end event.
   *
   * @ return The string representation of this service end event.
   */
  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " service done (by " + counter.toString() + ")";  
  }

}
