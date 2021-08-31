package cs2030.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * CS2030 Lab 7: InfiniteList.java
 * This is an infinite list class which consists of a head and a tail.
 *
 * @author Jennifer (B03)
 */
public class InfiniteList<T> {
  /** This is an instance of an empty list. */
  private static final InfiniteList<?> EMPTY_LIST = new EmptyList();

  /** The head of an infinite list. */
  private final Lazy<Maybe<T>> head;

  /** The tail of an infinite list. */
  private final Lazy<InfiniteList<T>> tail;

  /*
  // Used for skeleton stubs
  InfiniteList() { 
    head = null; 
    tail = null;
  }
  */
  
  /**
   * Factory method to generate infinite list lazily.
   *
   * @param producer The supplier of the infinite list.
   * @param <T> The type required for the producer class.
   * @return The lazily generated infinite list.
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<T>(Lazy.of(() -> Maybe.some(producer.produce())), 
        Lazy.of(() -> InfiniteList.generate(producer)));
  }
 
  /**
   * Lazily iterate through this infinite list.
   * 
   * @param seed The first element of the iteration.
   * @param next The transformer which specifies how iterated elements will be transformed.
   * @param <T> The type required for the seed and transformer.
   * @return The lazily iterated infinite list.
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<T>(seed, () -> InfiniteList.iterate(next.transform(seed), next));
  }
  
  /**
   * Constructor for infinite list. 
   *
   * @param tail A producer for the tail of the infinite list.
   * @param head The head of the infinite list.
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head)); 
    this.tail = Lazy.of(tail);
  }
 
  /**
   * Constructor for the infinite list.
   *
   * @param head The head of the infinite list.
   * @param tail The tail of the infinite list.
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }
  
  /**
   * Retrieves the first element of the infinite list.
   *
   * @return The first element of the infinite list.
   */
  public T head() {
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }
  
  /**
   * Retrieves the last element of the infinite list.
   *
   * @return The last element of the infinite list.
   */
  public InfiniteList<T> tail() {
    return this.head.get().map(h -> this.tail.get())
        .orElseGet(() -> this.tail.get().tail());
  }
  
  /**
   * Maps this infinite list of type T to another infinite list of type R.
   *
   * @param mapper This specifies how the infinite list will be transformed.
   * @param <R> This is the resulting type of the infinite list after mapping.
   * @return The mapped infinite list.
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<R>(this.head.map(h -> h.map(mapper)), 
        this.tail.map(t -> t.map(mapper)));
  }
  
  /**
   * Lazily filter the infinite list using a predicate. Lazily removes elements 
   * that does not fulfil the predicate.
   * 
   * @param predicate The condition used to filter the infinite list.
   * @return The lazily filtered infinite list.
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<T>(this.head.map(m -> m.filter(predicate)), 
        this.tail.map(m -> m.filter(predicate)));
  }
  
  /**
   * This is a nested empty list class, which inherits from infinite list.
   */
  private static class EmptyList extends InfiniteList<Object> { 
    /**
     * Constructor for an empty list.
     */
    private EmptyList() {
      super(Lazy.of(() -> Maybe.none()), Lazy.of(() -> new EmptyList()));
    }
    
    /**
     * Method to retrieve head of an infinite list. For empty list, this 
     * method will return a NoSuchElementException.
     *
     * @return A NoSuchElementException.
     */
    @Override
    public Object head() {
      throw new NoSuchElementException();
    }

    /** 
     * Method to retrieve the tail of an infinite list. For empty list, this
     * method will return an empty list.
     * 
     * @return An empty list.
     */
    @Override
    public InfiniteList<Object> tail() {
      return InfiniteList.empty();
    }
    
    /**
     * Method to map an infinite list to another type. For empty list, this
     * method will return an empty list.
     * 
     * @return An empty list.
     */
    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return InfiniteList.empty();
    }
    
    /**
     * Method to filter an infinite list lazily. For empty list, this
     * method will return an empty list.
     *
     * @return An empty list.
     */
    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return InfiniteList.empty();
    }
    
    /**
     * Method to truncate an infinite list to finite list. For empty list, this
     * method will return an empty list.
     *
     * @return An empty list.
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.empty();
    }
    
    /** 
     * Method to check whether an infinite list is empty. For empty list, this
     * method will return true.
     * 
     * @return true if instance of empty list; false otherwise.
     */
    @Override
    public boolean isEmpty() {
      return true;
    }
    
    /**
     * Method to truncate an infinite list once predicate is violated. For empty list,
     * this method will return an empty list.
     *
     * @return An empty list.
     */
    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return InfiniteList.empty();
    }
  }
  
  /**
   * Returns an empty list.
   *
   * @param <T> The type of the infinite list.
   * @return An empty list.
   */
  public static <T> InfiniteList<T> empty() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> emptyList = (InfiniteList<T>) EMPTY_LIST;
    return emptyList;
  }
  
  /** 
   * Truncates an infinite list into a finite list of size n.
   *
   * @param n The size of the truncated list.
   * @return The truncated list.
   */
  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return InfiniteList.empty();
    }
    return new InfiniteList<>(this.head, Lazy.of(() -> 
        head.get().map(x -> tail.get().limit(n - 1))
        .orElseGet(() -> tail.get().limit(n))));
  }
  
  /**
   * Iterates through an infinite list and truncates it once an element is evaluated to false.
   *
   * @param predicate The condition used to truncate the list.
   * @return The truncated list.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {  
    Lazy<Boolean> isHeadNone = this.head.filter(h -> h.map(x -> false).orElse(true));
    Lazy<Boolean> isHeadFail = this.head.filter(h -> h.filter(predicate)
        .map(x -> false).orElse(true));

    Lazy<Boolean> headFilter = isHeadNone.combine(isHeadFail, (n, f) -> !n && !f);
    Lazy<Boolean> tailFilter = isHeadNone.combine(isHeadFail, (n, f) -> n || !f);
    
    return new InfiniteList<>(
        headFilter.map(h -> h ? this.head.get() : Maybe.none()),
        tailFilter.map(l -> l ? this.tail.map(t -> t.takeWhile(predicate)).get()
            : InfiniteList.empty())); 
  }
  
  /**
   * Returns true if list is empty; false otherwise.
   *
   * @return true if list is empty; false otherwise.
   */
  public boolean isEmpty() {
    return false;
  }

  /**
   * A terminal operation that reduces an infinite list to a single value.
   * 
   * @param identity The starting value for the reduce operation.
   * @param accumulator This defines how the list will be mapped into a single value.
   * @param <U> The type to which the infinite list will be reduced to.
   * @return The value obtained from reducing the infinite list.
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    InfiniteList<T> infiniteList = this;
    U result = identity;
    while (!infiniteList.isEmpty()) {
      U temp = result;
      result = infiniteList.head.get()
          .map(x -> accumulator.combine(temp, x)).orElse(temp);
      infiniteList = infiniteList.tail.get();
    }
    return result;
  }

  /**
   * A terminal operation that counts the number of elements in the infinite
   * list.
   *
   * @return The number of elements in the list.
   */
  public long count() {
    return this.reduce(0, (x, y) -> x + 1);
  }

  /**
   * Returns a list containing the elements inside the infinite list.
   *
   * @return A list containing the elements in the infinite list.
   */
  public List<T> toList() {
    InfiniteList<T> infiniteList = this;
    List<T> list = new ArrayList<>();
    while (!infiniteList.isEmpty()) {
      infiniteList.head.get().map(x -> list.add(x));
      infiniteList = infiniteList.tail.get();
    }
    return list;
  }

  /**
   * Returns the string representation of the infinite list.
   *
   * @return The string representation of the infinite list.
   */
  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
