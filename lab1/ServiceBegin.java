/**
 * CS2030 Lab 1: ServiceBegin.java
 * This class represents a service begin event in shop simulation.
 *
 * @author Jennifer (B03)
 */
class ServiceBegin extends Event {
  /** The customer associated with this service begin event. */
  private Customer customer;

  /** The counter associated with this service begin event. */
  private Counter counter;

  /**
   * Constructor for a service begin event.
   *
   * @param time The time this service begin event occurs.
   * @param customer The customer associated with this service begin event.
   * @param counter The counter associated with this service begin event.
   */
  public ServiceBegin(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  /**
   * Retrieve an array of events for the simulator.
   * 
   * @return An array of event for the simulator.
   */
  @Override
  public Event[] simulate() {
   this.counter.beginService();
   double endTime = this.getTime() + this.customer.getServiceTime();
   return new Event[] {
     new ServiceEnd(endTime, this.customer, this.counter)
   };
  }
  
  /**
   * Return the string representation of this service begin event.
   *
   * @return The string representation of this service begin event.
   */
  @Override 
  public String toString() {
    return super.toString() + ": " + customer.toString() + " service begin (by " + counter.toString() + ")";
  }

}
