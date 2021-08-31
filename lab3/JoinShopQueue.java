/**
 * CS2030 Lab 3 JoinShopQueue.java
 * This class represents a join shop queue event in a shop simulation.
 *
 * @author Jennifer (B03)
 */
class JoinShopQueue extends Event {
  /** The customer associated with this join shop queue event. */
  private Customer customer;

  /** The shop associated with this join shop queue event. */
  private Shop shop;

  /**
   * Constructor for a join queue event.
   * 
   * @param time The time this join shop queue event occurs.
   * @param shop The shop associated with this join shop queue event.
   * @param customer The customer associated with this join shop queue event.
   */
  public JoinShopQueue(double time, Shop shop, Customer customer) {
    super(time);
    this.shop = shop;
    this.customer = customer;
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
        + " joined shop queue " + this.shop.toString();
  }
}   
