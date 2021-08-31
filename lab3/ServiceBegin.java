/** 
 * CS2030 Lab 3: ServiceBegin.java
 * This class represents a service begin event in shop simulation.
 *
 * @author Jennifer (B03)
 */
public class ServiceBegin extends Event {
  /** The shop associated with this service begin event. */
  private Shop shop;

  /** The customer associated with this service begin event. */
  private Customer customer;

  /** The counter asssociated with this service begin event. */
  private Counter counter;

  /**
   * Constructor for a service begin event.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param counter The counter assigned to the customer.
   */
  public ServiceBegin(double time, Shop shop, Customer customer, Counter counter) {
    super(time);
    this.shop = shop;
    this.customer = customer;
    this.counter = counter;
  }
  
  /**
   * Retrieve an array of events for the simulator.
   * 
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] simulate() {
    this.counter.beginService();
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] {
      new ServiceEnd(endTime, this.shop, this.customer, this.counter)
    };
  }
  
  /**
   * Return the string representation of this service begin event.
   *
   * @return The string representation of this service begin event.
   */
  @Override 
  public String toString() {
    return super.toString() + ": " + this.customer.toString() 
        + " service begin (by " + this.counter.toString() + ")";
  }
}
