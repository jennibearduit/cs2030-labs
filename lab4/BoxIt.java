/**
 * CS2030 Lab 4: BoxIt.java
 * This is a generic BoxIt class, which implemments Transformer interface.
 *
 * @author Jennifer (B03)
 */
class BoxIt<T> implements Transformer<T, Box<T>> {
  /**
   * Return a box containing specified item.
   *
   * @param item The item to be placed in the box.
   * @return The box containing the item.
   */
  public Box<T> transform(T item) {
    return Box.ofNullable(item);
  }
}
