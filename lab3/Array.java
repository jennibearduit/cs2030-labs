/**
 * CS2030 Lab 3: Array.java
 * This is a generic array class.
 *
 * @author Jennifer (B03)
 */
class Array<T extends Comparable<T>> {
  /** A generic array. */
  private T[] array;
  
  /** Constructor for the array class. 
   * 
   * @param size The size of the array.
   */
  Array(int size) {
    @SuppressWarnings("unchecked")
    T[] a = (T[]) new Comparable[size];
    this.array = a;
  }

  /** 
   * Sets array[index] to be equals to item.
   *
   * @param index The index where the item will be set in the array.
   * @param item The item to be set in the array.
   */
  public void set(int index, T item) {
    this.array[index] = item;
  }

  /**
   * Get item from array at a specific index.
   *
   * @param index The index where the item will be obtained from array.
   * @return Item obtained from the array at the specified index.
   */
  public T get(int index) {
    return this.array[index];
  }
  
  /**
   * Retrieves the minimum element of the array.
   *
   * @return The minimum element of the array.
   */
  public T min() {
    T min = array[0];
    for (T t : array) {
      if (t.compareTo(min) < 0 || min == null) {
        min = t;
      }
    }
    return min;
  }
}
