/**
 * CS2030 Lab 4: Transformer.java
 * This is a transformer interface.
 *
 * @author Jennifer (B03)
 */
interface Transformer<T, U> {
  /** This is an abstract transform method. */
  public abstract U transform(T box);
}
