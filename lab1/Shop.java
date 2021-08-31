/**
 * CS2030 Lab 1: Shop.java
 * This class represents a shop consisting of service counters.
 *
 * @author Jennifer (B03)
 */

class Shop {
  /** The number of service counters in the shop. */
  private int numOfCounters;

  /** An array of service counters in the shop. */
  private Counter[] counters;
  
  /**
   * Constructor for a shop.
   * 
   * @param numOfCounters The total number of counters in the shop.
   */
  public Shop(int numOfCounters) {
    this.numOfCounters = numOfCounters;
    this.initializeCounters();
  }

  /**
   * Checks if shop is full (no available service counter).
   * 
   * @return true if shop is full; false otherwise.
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
    for (int i = 0; i < this.numOfCounters; i++) {
      if (this.counters[i].isAvailable()) {
        return this.counters[i];
      }
    }
    return null;
 }

  /** Initializes the service counters in this shop. */
  private void initializeCounters() {
    this.counters = new Counter[this.numOfCounters];
    for (int i = 0; i < this.numOfCounters; i++) {
      this.counters[i] = new Counter(i);
    }
  }
}
