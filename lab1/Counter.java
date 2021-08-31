/**
 * CS2030 Lab 1: Counter.java
 * This class represents a service counter in a shop simulation.
 *
 * @author Jennifer (B03)
 */
class Counter {
  /** The id of this service counter. */
  private int counterId;

  /** The availability of this service counter .*/
  private boolean available;
  
  /**
   * Constructor for a service counter.
   *
   * @param counterId The id of this service counter.
   */
  public Counter(int counterId) {
    this.counterId = counterId;
    this.available = true;
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
   * Retrieves the availability of this service counter.
   *
   * @return true if service counter is available; false otherwise.
   */
  public boolean isAvailable() {
    return this.available;
  }

  /**
   * Returns the string representation of a service counter.
   *
   * @return The string representation of a service counter.
   */
  @Override
  public String toString() {
    return String.format("Counter %d", this.counterId);
  }
}
