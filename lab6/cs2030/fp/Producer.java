package cs2030.fp;

/**
 * Represent a function that produce a value.
 * CS2030 Lab 5
 * AY20/21 Special Term 1
 *
 * @param <T> The type of the value produced.
 */
@FunctionalInterface
public interface Producer<T> {
  /**
   * The functional method to produce a value.
   *
   * @return The value produced.
   */
  T produce();
}
