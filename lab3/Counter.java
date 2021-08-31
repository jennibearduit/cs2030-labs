/**
 * CS2030 Lab 3: Counter.java
 * This class represents a service counter.
 *
 * @author Jennifer (B03)
 */
public class Counter implements Comparable<Counter> {
  /** The id of this counter. */
  private int counterId;

  /** The availability of this service counter. */
  private boolean available;

  /** The queue at this counter. */
  private Queue<Customer> queue;
  
  /** The maximum number of customers at this counter queue. */
  private int maxCounterQLength;

  /**
   * Constructor for a counter.
   *
   * @param counterId The id of this counter. 
   * @param maxCounterQLength The maximum number of customers at this counter queue. 
   */
  public Counter(int counterId, int maxCounterQLength) {
    this.counterId = counterId;
    this.available = true;
    this.queue = new Queue<Customer>(maxCounterQLength);
  }

  /** Sets the availability of this service counter to false. */
  public void beginService() {
    this.available = false;
  }

  /** Sets the availability of this service counter to true. */
  public void endService() {
    this.available = true;
  }
  
  /** 
   * Adds the customer into the queue of this counter. 
   * 
   * @param customer The customer to be added into the queue of this counter.
   */
  public void queue(Customer customer) {
    this.queue.enq(customer);
  }

  /** 
   * Returns the fist customer in the queue of this counter.
   *
   * @return The first customer in the queue of this counter.
   */
  public Customer dequeue() {
    return this.queue.deq();
  }

  /** 
   * Returns the availability of this service counter.
   * 
   * @return The availability of this service counter.
   */
  public boolean isAvailable() {
    return this.available;
  }

  /**
   * Returns true if counter queue is full.
   *
   * @return true if counter queue is full; false otherwise.
   */
  public boolean isCounterQueueFull() {
    return this.queue.isFull();
  }

  /** 
   * Returns the string representation of this service counter. 
   *
   * @return The string representation of this service counter. 
   */
  @Override
  public String toString() {
    return String.format("S%d %s", this.counterId, this.queue.toString());
  }

  /** 
   * Compares this counter to another based on queue length, counterId 
   * and availability. 
   */
  @Override
  public int compareTo(Counter other) {
    if (this.queue.length() == 0 && other.queue.length() == 0) {
      if (this.available && other.available) {
        return this.counterId - other.counterId;
      } else if (this.available) {
        return -1;
      } 
      return 1;
    }  
    return this.queue.length() - other.queue.length();
  } 
}
