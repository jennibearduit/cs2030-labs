/**
 * CS2030 Lab 1: Arrival.java
 * This class represents an arrival event in shop simulation.
 * 
 * @author Jennifer (B03)
 */
class Arrival extends Event {
  /** The shop associated with this arrival event. */
  private Shop shop;

  /** The customer associated with this arrival event. */
  private Customer customer;
  
  /** 
   * Constructor for an arrival event. 
   *
   * @param time The time this arrival event occurs.
   * @param shop The shop associated with this arrival event.
   * @param customer The customer associated with this arrival event.
   */
  public Arrival(double time, Shop shop, Customer customer) {
    super(time);
    this.shop = shop;
    this.customer = customer;
  }

  /**
   * Retrieve an array of events for the simulator.
   *
   * @return An array of event for the simulator.
   */
  @Override
  public Event[] simulate() {
    if (this.shop.isShopFull()) {
      return new Event[] {
        new Departure(this.getTime(), this.customer)
      };
    }
    Counter counter = this.shop.getAvailableCounter();
    return new Event[] {
      new ServiceBegin(this.getTime(), this.customer, counter)
    };
  }
  
  /**
   * Return the string representation of this arrival event.
   *
   * @return The string representation of this arrival event.
   */
  @Override
  public String toString() {
    return super.toString() + ": " + this.customer.toString() + " arrives";
  }
}
