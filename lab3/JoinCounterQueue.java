/**
 * CS2030 Lab 3: JoinCounterQueue.java
 * This class represents a join counter queue event in a shop simulation.
 *
 * @author Jennifer (B03)
 */
class JoinCounterQueue extends Event {
  /** The customer associated with this join counter queue event. */
  private Customer customer;

  /** The counter associated with this join counter queue event. */
  private Counter counter;

  /** The shop associated with this join counter queue event. */
  private Shop shop;

  /**
   * Constructor for a join counter queue event.
   * 
   * @param time The time this join counter queue event occurs.
   * @param shop The customer associated with this join counter queue event.
   * @param customer The customer associated with this join counter queue event.
   * @param counter The counter associated with this join counter queue event.
   */
  public JoinCounterQueue(double time, Customer customer, Counter counter) {
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
  public Event[] simulate() {
    this.counter.queue(this.customer);
    return new Event[] { };
  }

  /**
   * Return the string representation of this join counter queue event.
   *
   * @return The string representation of this join counter queue event.
   */
  @Override
  public String toString() {
    return super.toString() + ": " + this.customer.toString()
        + " joined counter queue (at " + this.counter.toString() + ")";
  }
}   
