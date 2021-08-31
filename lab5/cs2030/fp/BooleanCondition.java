package cs2030.fp; 

/**
 * CS2030 Lab 5: BooleanCondition.java
 * AY20/21 Special Term 1
 * This is a boolean condition interface.
 *
 * @author Jennifer (B03)
 */
public interface BooleanCondition<T> {
  /** This is an abstract test method. */
  boolean test(T argument);
}
