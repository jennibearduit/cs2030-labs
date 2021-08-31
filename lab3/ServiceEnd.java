/**
 * CS2030 Lab 3: ServiceEnd.java
 * This class represents a service end event in a shop simulation.
 * 
 * @author Jennifer (B03)
 */
public class ServiceEnd extends Event {
  /** The shop associated with this service end event. */  
  private Shop shop;

  /** The customer associated with this service end event. */
  private Customer customer;

  /** The counter associated with this service end event. */
  private Counter counter;

  /**
   * Constructor for a service end event.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param counter The counter associated with this event.  
   */
  public ServiceEnd(double time, Shop shop, Customer customer, Counter counter) {
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
    this.counter.endService();
    Customer shopCustomer = this.shop.dequeue();
    Customer counterCustomer = this.counter.dequeue();
    if (counterCustomer != null) {
      if (shopCustomer != null) {
        return new Event[] {
          new Departure(this.getTime(), this.customer),
          new ServiceBegin(this.getTime(), this.shop, counterCustomer, this.counter),
          new JoinCounterQueue(this.getTime(), shopCustomer, this.counter)
        };
      } 
      return new Event[] {
        new Departure(this.getTime(), this.customer),
        new ServiceBegin(this.getTime(), this.shop, counterCustomer, this.counter)
      };
    } else if (shopCustomer != null) {
      return new Event[] {
        new Departure(this.getTime(), this.customer),
        new ServiceBegin(this.getTime(), this.shop, shopCustomer, this.counter)
      };
    }
    return new Event[] {
      new Departure(this.getTime(), this.customer)
    };
  }
  
  /**
   * Return the string representation of this service end event.
   *
   * @return The string reporesentation of this service end event.
   */
  @Override
  public String toString() {
    return super.toString() + ": " +  customer.toString() 
        + " service done (by " + counter.toString() + ")";  
  }
}
