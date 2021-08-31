/**
 * CS2030 Lab 4: LastDigitsOfHashCode.java
 * This is a LastDigitsOfHashCode class, which implements Transformer interface.
 *
 * @author Jennifer (B03)
 */
class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  /** The number of digits to take from the hashcode. */
  private int numOfDigits;

  /** 
   * The constructor for LastDigitsOfHashCode.
   *
   * @param numOfDigits the number of digits to take from the hashcode.
   */
  public LastDigitsOfHashCode(int numOfDigits) {
    this.numOfDigits = numOfDigits;
  }

  /**
   * Returns the last few digits of hashcode, where number of digits
   * is specified by the numOfDigits.
   *
   * @param item The object to derive the hashcode from.
   * @return The last few digits of the item's hashcode.
   */
  public Integer transform(Object item) {
    int digits = (int) Math.pow(10, this.numOfDigits);
    return Math.abs(item.hashCode() % digits);
  }
}
