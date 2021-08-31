/**
 * CS2030 Lab 4: LongerThan.java
 * This is a LongerThan class, which implements BooleanCondition<String> interface.
 *
 * @author Jennifer (B03)
 */
class LongerThan implements BooleanCondition<String> {
  /** The limit of string length. */
  private int limit;

  /**
   * Constructor for this LongerThan class.
   *
   * @param limit The limit of the length of a string.
   */
  public LongerThan(int limit) {
    this.limit = limit;
  }
  
  /**
   * Determines if a string is longer than limit.
   *
   * @param string The string to be checked.
   */
  public boolean test(String string) {
    return (string.length() > this.limit);
  }
}
