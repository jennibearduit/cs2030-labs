/**
 * CS2030 Lab 4: DivisibleBy.java
 * This represents a DivisibleBy class, which implements the interface BooleanCondition<Integer>.
 *
 * @author Jennifer (B03)
 */

class DivisibleBy implements BooleanCondition<Integer> {
  /** An integer value */
  private int integer;
  
  /** 
   * Constructor for this DivisbleBy class.
   *
   * @param integer An integer value.
   */
  public DivisibleBy(int integer) {
    this.integer = integer;
  }

  /** 
   * Determines whether one integer value is divisible by another.
   *
   * @param otherInteger Another integer value.
   * @return boolean true if one integer value is divisible by another; false otherwise.
   */
  public boolean test(Integer otherInteger) {
    return (integer % otherInteger == 0 || otherInteger % integer == 0);
  }
}
