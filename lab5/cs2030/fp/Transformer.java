package cs2030.fp;

/**
 * CS2030 Lab 5: Transformer.java
 * AY20/21 Special Term 1
 * This is a transformer interface.
 *
 * @author Jennifer (B03)
 */
public interface Transformer<T, U> {
  /** This is an abstract transform method. */
  U transform(T item);
}
