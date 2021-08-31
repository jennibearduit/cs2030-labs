/**
 * CS2030 Lab 2: Counter.java
 * This class represents a service counter.
 *
 * @author Jennifer (B03)
 */
public class Counter {
  /** The id of this counter. */
  private int counterId;

  /** The availability of this service counter. */
  private boolean available;

  /**
   * Constructor for a counter.
   *
   * @param counterId The id of this counter. 
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
   * Returns the availability of this service counter.
   * 
   * @return The availability of this service counter.
   */
  public boolean isAvailable() {
    return this.available;
  }

  /** 
   * Returns the string representation of this service counter. 
   *
   * @return The string representation of this service counter. 
   */
  @Override
  public String toString() {
    return String.format("S%d", this.counterId);
  }
}
