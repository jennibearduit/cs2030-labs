/**
 * CS2030 Lab 2: JoinQueue.java
 * This class represents a join queue event in a shop simulation.
 *
 * @author Jennifer (B03)
 */
class JoinQueue extends Event {
  /** The customer associated with this join queue event. */
  private Customer customer;

  /** The shop associated with this join queue event. */
  private Shop shop;

  /**
   * Constructor for a join queue event.
   * 
   * @param time The time this join queue event occurs.
   * @param customer The customer associated with this arrival event.
   */
  public JoinQueue(double time, Shop shop, Customer customer) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }
  
  /**
   * Retrieve an array of events for the simulator.
   *
   * @return An array of events for the simulator.
   */
  @Override 
  public Event[] simulate() {
    this.shop.queue(this.customer);
    return new Event[] { };
  }

  /**
   * Return the string representation of this join queue event.
   *
   * @return The string representation of this join queue event.
   */
  @Override
  public String toString() {
    return super.toString() + ": " + this.customer.toString()
        + " joined queue " + this.shop.toString();
  }
}   
