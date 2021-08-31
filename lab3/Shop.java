/** 
 * CS2030 Lab 3: Shop.java
 * This class represents a shop consisting of service counters and a queue.
 *
 * @author Jennifer (B03)
 */
class Shop {
  /** The number of counters in this shop. */
  private int numOfCounters;

  /** The collection of counters in this shop. */
  private Array<Counter> counters;

  /** The queue in this shop. */
  private Queue<Customer> queue;
  
  /**
   * Constructor for a Shop.
   *
   * @param numOfCounters The  total number of counters in the shop.
   * @param maxQLength The maximum number of customers the queue can take in.
   */
  public Shop(int numOfCounters, int maxCounterQLength, int maxShopQLength) {
    this.numOfCounters = numOfCounters;
    this.counters = new Array<Counter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      Counter counter = new Counter(i, maxCounterQLength);
      this.counters.set(i, counter);
    }
    this.queue = new Queue<Customer>(maxShopQLength);
  }

  /** 
   * Adds a customer into the entrance queue.
   * 
   * @param customer The customer to be added into the entrance queue.
   */
  public void queue(Customer customer) {
    this.queue.enq(customer);
  }
  
  /**
   * Returns the first customer in the queue.
   *
   * @return The first customer in the queue.
   */
  public Customer dequeue() {
    return this.queue.deq();
  }

  /**
   * Checks if the entrance queue is full.
   *
   * @return true if the entrance queue is full; false otherwise;
   */
  public boolean isShopQueueFull() {
    return this.queue.isFull();
  }
  
  /**
   * Checks if the counters are full.
   *
   * @return true if counters are full.
   */
  public boolean areCountersFull() {
    return (this.getFirstCounter() == null);
  }

  /**
   * Return the first counter that the customer should join.
   *
   * @return The first counter that the customer should join.
   */
  public Counter getFirstCounter() {
    Counter counter = counters.min();
    if (counter == null) {
      return null;
    } else if (!counter.isAvailable() && counter.isCounterQueueFull()) {
      return null;
    }
    return counter;
  }

  /**
   * Retrieves the string representation of this shop.
   * 
   * @return The string representation of this shop.
   */
  @Override
  public String toString() {
    return this.queue.toString();
  }
}
