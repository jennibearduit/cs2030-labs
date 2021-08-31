package cs2030.fp;

/**
 * CS2030 Lab 6: Lazy.java
 * This Lazy class encapsulates value and allow evaluation of an expression
 * exactly once.
 *
 * @author Jennifer (B03)
 */
public class Lazy<T> {
  /** Produces a value when needed. */
  private Producer<T> producer;

  /** A value wrapped around by Maybe. */
  private Maybe<T> value;

  /** 
   * A constructor for this Lazy class, given a value.
   *
   * @param value The input which will be wrapped by a Maybe class in Lazy.
   */
  private Lazy(T value) {
    this.producer = () -> value;
    this.value = Maybe.some(value);
  }

  /**
   * A constructor for this Lazy class, given a producer.
   *  
   * @param producer This produces a value when needed.
   */
  private Lazy(Producer<T> producer) {
    this.producer = producer;
    this.value = Maybe.none();
  }
  
  /**
   * A factory method which returns a Lazy instance with the given value.
   *
   * @param value The input which will be wrapped around by a Maybe class in Lazy.
   * @param <T> The type of the value input.
   * @return The Lazy instance with the given value.
   */
  public static <T> Lazy<T> of(T value) {
    return new Lazy<>(value);
  }

  /**
   * A factory method which returns a Lazy instance with a producer that produces
   * a value when needed.
   *
   * @param producer This produces a value when needed.
   * @param <T> The type of value of the output Lazy object produced by the producer.
   * @return The Lazy instance with the specified producer.
   */
  public static <T> Lazy<T> of(Producer<T> producer) {
    return new Lazy<>(producer);
  }

  /**
   * Returns the value. If the value is available, return that value.
   * Otherwise, compute the value and return it. The computation should
   * only be done once for the same value.
   *
   * @return A value if available; otherwise, compute the value then return.
   */
  public T get() {
    T content =  this.value.orElseGet(this.producer);
    this.value = Maybe.some(content);
    return content;
  }

  /**
   * Transform a Lazy instance and returns a Lazy with the new value.
   *
   * @param transformer Determines how the value inside Lazy will be transformed.
   * @param <S> The type of the transformed value inside Lazy.
   * @return A Lazy containing the transformed value.
   */
  public <S> Lazy<S> map(Transformer<? super T, ? extends S> transformer) {
    return new Lazy<S>(() -> transformer.transform(this.get()));
  }
  
  /**
   * Similar to map, but prevents a value from being wrapped multiple times by Lazy.
   *
   * @param transformer Determine how the value inside Lazy will be transformed.
   * @param <S> The type of the transformed value inside Lazy.
   * @return A Lazy containing the transformed value.
   */
  public <S> Lazy<S> flatMap(Transformer<? super T, ? extends Lazy<? extends S>> transformer) {
    return new Lazy<S>(() -> transformer.transform(this.get()).get());
  }

  /**
   * Takes in a BooleanCondition and lazily tests if the value passes the tests or not. The boolean
   * condition must be executed at most once.
   *
   * @param condition Functional interface that determines whether a condition/test is fulfilled.
   * @return a Boolean value wrapped around by Lazy.
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> condition) {
    return new Lazy<Boolean>(() -> condition.test(this.get()));
  }
  
  /**
   * Combine two values into a result.
   *
   * @param otherLazy The other Lazy object to be combined with this Lazy instance.
   * @param <R> The type of the value in the other Lazy object to be combined with this Lazy object.
   * @param combiner Functional interface to combine two values into one result.
   * @param <S> The type of the value inside the combined Lazy object.
   * @return A Lazy object containing the value of the combination of the two Lazy inputs.
   */
  public <S, R> Lazy<S> combine(Lazy<R> otherLazy, 
      Combiner<? super T, ? super  R, ? extends S> combiner) {
    return new Lazy<S>(() -> combiner.combine(this.get(), otherLazy.get()));
  }

  /**
   * Determine if this Lazy object is equal to another object.
   * 
   * @param other The other object to be evaluated against this Lazy object.
   * @return true if equal; false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other instanceof Lazy) {
      Lazy<?> otherLazy = (Lazy<?>) other;
      if (this.get().equals(otherLazy.get())) {
        return true;
      }
    }
    return false;
  }

  /** 
   * Returns the string representation of this Lazy instance.
   *
   * @return The string representation of this Lazy instance.
   */
  @Override
  public String toString() {
    return this.value.map(x -> String.valueOf(x)).orElse("?");
  }
}
