/** 
 * CS2030 Lab 2: Shop.java
 * This class represents a shop consisting of service counters and a queue.
 *
 * @author Jennifer (B03)
 */
class Shop {
  /** The number of counters in this shop. */
  private int numOfCounters;

  /** The collection of counters in this shop. */
  private Counter[] counters;

  /** The queue in this shop. */
  private Queue queue;
  
  /**
   * Constructor for a Shop.
   *
   * @param numOfCounters The  total number of counters in the shop.
   * @param maxQLength The maximum number of customers the queue can take in.
   */
  public Shop(int numOfCounters, int maxQLength) {
    this.numOfCounters = numOfCounters;
    this.counters = new Counter[this.numOfCounters];
    for (int i = 0; i < this.numOfCounters; i++) {
      this.counters[i] = new Counter(i);
    }
    this.queue = new Queue(maxQLength);
  }

  /** 
   * Adds a customer into the queue.
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
    return (Customer) this.queue.deq();
  }

  /**
   * Checks if the queue is full.
   *
   * @return true if the queue is full; false otherwise;
   */
  public boolean isQueueFull() {
    if (this.queue.isFull()) {
      return true;
    }
    return false;
  }

  /**
   * Checks if shop is full (no available service counter).
   *
   * @return true if there is no available service counter; false otherwise.
   */
  public boolean isShopFull() {
    if (this.getAvailableCounter() == null) {
      return true;
    }
    return false;
  }

  /**
   * Retrieves the first available service counter.
   *
   * @return The first available service counter.
   */
  public Counter getAvailableCounter() {
    for (int i = 0; i < numOfCounters; i++) {
      if (this.counters[i].isAvailable()) {
        return this.counters[i];
      }
    }
    return null;
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
