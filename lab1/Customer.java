/**
 * CS2030 Lab 1: Customer.java
 * This class represents a customer in a shop simulation.
 *
 * @author Jennifer (B03)
 */
class Customer {
  /** The id of this customer. */
  private int customerId;

  /** The service time associated with this customer. */
  private double serviceTime;
  
  /**
   * Constructor for a customer. 
   *
   * @param customerId The id of this customer
   * @param serviceTime The time required to serve this customer.
   */
  public Customer(int customerId, double serviceTime) {
    this.customerId = customerId;
    this.serviceTime = serviceTime;
  }

  /**
   * Retreives the time required to serve this customer.
   *
   * @return The time required to serve this customer.
   */
  public double getServiceTime() {
    return this.serviceTime;
  }

  /** 
   * Returns the string representation of this customer.
   *
   * @return The string representation of this customer.
   */
  @Override
  public String toString() {
    return String.format("Customer %d", this.customerId);
  }
}
