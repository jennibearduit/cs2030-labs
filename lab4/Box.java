/**
 * CS2030 Lab 4: Box.java
 * This class represents a generic box.
 *
 * @author Jennifer (B03)
 */
class Box<T> {
  /** The content of this box. */
  private final T content;

  /** An empty box */
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  /**
   * The constructor for box.
   *
   * @param content the content of this box. 
   */
  private Box(T content) {
    this.content = content;
  }

  /**
   * Returns a box with specified content.
   *
   * @param content The content of the box.
   * @return An empty box if content is null; otherwise, a box with content.
   */
  public static <T> Box<T> ofNullable(T content) {
    if (content == null) {
      return empty();
    }
    return of(content);
  }

  /**
   * Returns a box with specified content.
   *
   * @param content The content of the box.
   * @return Null if content is null; otherwise, a box with content.
   */
  public static <T> Box<T> of(T content) {
    if (content == null) {
      return null;
    }
    return new Box<>(content);
  }

  /**
   * Returns an empty box.
   *
   * @return An empty box.
   */
  public static <T> Box<T> empty() {
    @SuppressWarnings("unchecked")
    Box<T> emptyBox = (Box<T>) EMPTY_BOX;
    return emptyBox;
  }

  /**
   * Checks if this box is empty.
   *
   * @return true if box is not empty; false otherwise.
   */
  public boolean isPresent() {
    return !(this.content == null);
  }

  /**
   * Checks if box fulfils condition
   *
   * @return Empty box if box is empty or condition is not fulfilled;
   * otherwise, the same box is returned.
   */
  public Box<T> filter(BooleanCondition<? super T> condition) {
    if (!this.isPresent() || !condition.test(this.content)) {
      return empty();
    }
    return (Box<T>) this;
  }

  /**
   * Transforms box into another type.
   */
  public <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
    if (!this.isPresent()) {
      return empty();
    }
    return Box.ofNullable(transformer.transform(this.content));
  }
  
  /**
   * Checks if this box is equals to a box.
   *
   * @param other The box to be compared with this box.
   * @return true if this box is equals to other box; false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    } 
    if (other instanceof Box<?>) {
      Box<?> otherBox = (Box<?>) other;
      if (!this.isPresent() ||  !otherBox.isPresent()) {
        return false;
      } else if (this.content == otherBox.content) {
        return true;
      }
      return this.content.equals(otherBox.content);
    }
    return false;
  }

  /**
   * Returns the string representation of this box.
   *
   * @return The string representation of this box.
   */
  @Override
  public String toString() {
    if (!isPresent()) {
      return "[]";
    }
    return "[" + content.toString() + "]";
  }
}
