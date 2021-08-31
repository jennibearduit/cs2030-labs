package cs2030.fp;

import java.util.NoSuchElementException;

/**
 * CS2030 Lab 5: Maybe.java
 * AY20/21 Special Term 1
 * This is an abstract generic maybe class.
 *
 * @author Jennifer (B03)
 */
public abstract class Maybe<T> {
  /** The content of this Maybe instance. */
  protected T content;

  /**
   * This is a constructor for the Maybe class.
   * 
   * @param content The content of this Maybe instance.
   */
  public Maybe(T content) {
    this.content = content;
  }
  
  /** This is an abstract get method. */
  protected abstract T get();

  /** This is an abstract filter method. */
  public abstract Maybe<T> filter(BooleanCondition<? super T> condition);

  /** This is an abstract map method. */
  public abstract <S> Maybe<S> map(Transformer<? super T, ? extends S> transformer);

  /** This is an abstract flat map method. */
  public abstract <S> Maybe<S> flatMap(Transformer<? super T, 
      ? extends Maybe<? extends S>> transfomer);
  
  /** This is an abstract or else method. */
  public abstract T orElse(T alternativeValue);

  /** This is an abstract or else get method. */
  public abstract T orElseGet(Producer<? extends T> producer);
  
  /** A single instance of None. */
  private static final Maybe<?> NONE = new None();

  /** This is a nested none class, which inherits from maybe. */
  private static class None extends Maybe<Object> {
    /** The constructor for none. */
    private None() {
      super(null);
    }

    /**
     * Returns an alternative value specified by the producer for the case of None.
     *
     * @param producer produces the alternative value if None.
     * @return Value produced by producer.
     */
    @Override
    public Object orElseGet(Producer<? extends Object> producer) {
      return producer.produce();
    }

    /**
     * Returns the alternative value for the case of None.
     *
     * @param alternativeValue The value to be returned if None.
     * @return The specified alternative value.
     */
    @Override
    public Object orElse(Object alternativeValue) {
      return alternativeValue;
    }

    /**
     * The overriden method flatMap() returns a None instance.
     * 
     * @param transformer This specifies how a content should be transformed.
     * @return A None instance.
     */
    @Override
    public <S> Maybe<S> flatMap(Transformer<? super Object, 
        ? extends Maybe<? extends S>> transformer) {
      return none();
    }

    /**
     * The overriden method map() returns a None instance. 
     *
     * @param transformer This specifies how the content should be transformed.
     * @return A None instance.
     */
    @Override 
    public <S> Maybe<S> map(Transformer<? super Object, ? extends S> transformer) {
      return none();
    }

    /**
     * The overriden method filter() returns a None instance.
     *
     * @param condition The condition to be fulfilled.
     * @return a None instance.
     */
    @Override 
    public Maybe<Object> filter(BooleanCondition<? super Object> condition) {
      return none();
    }
    
    /** Using get() on a none returns a NoSuchElementException.*/
    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    /**
     * Retrieves the string representation for None.
     *
     * @return The string representation of None.
     */
    @Override
    public String toString() {
      return "[]";
    }
    
    /** 
     * Checks if this None instance is equal to another object.
     * 
     * @param other The other object to be checked with this None instance.
     * @return True if other is a None instance; false otherwise.
     */
    @Override
    public boolean equals(Object other) {
      if (this == other) {
        return true;
      }
      return false;
    }
  }
  
  /** This is a nested some class, which inherits from maybe. */
  private static final class Some<T> extends Maybe<T> { 
    /**
     * The constructor for some.
     *
     * @param content The content of this some instance.
     * @return An instance of Some wrapped around content (may be null).
     */
    private Some(T content) {
      super(content);
    }

    /**
     * Returns the content of this Some instance.
     *
     * @param producer To produce alternative value if None.
     * @return The content of this Some instance.
     */
    @Override
    public T orElseGet(Producer<? extends T> producer) {
      return this.content;
    }
    
    /**
     * Returns the content of the Some instance.
     *
     * @param alternativeValue The value to be returned if None.
     * @return The content of the Some instance.
     */
    @Override
    public T orElse(T alternativeValue) {
      return this.content;
    }
    
    /**
     * Transforms a Some instance and return the content of the transformed instance.
     *
     * @param trasnformer This specifies how the content will be transformed.
     * @return The content of the transformed Some instance.
     */
    @Override
    public <S> Maybe<S> flatMap(Transformer<? super T, ? extends Maybe<? extends S>> transformer) {
      @SuppressWarnings("unchecked")
      Maybe<S> outputValue = (Maybe<S>) transformer.transform(this.content);
      return outputValue;
    }

    /**
     * Transforms the content inside this Some instance.
     *
     * @param transformer This specifies how the content will be transformed.
     * @return The transformed Some instance
     */
    @Override 
    public <S> Maybe<S> map(Transformer<? super T, ? extends S> transformer) {
      S transformedValue = transformer.transform(this.content);
      return some(transformedValue);
    }

    /**
     * Checks if a Some instance fulfills a condition.
     *
     * @param condition The condition a Some instance needs to fulfill.
     * @return None if value is not null AND failed the test; otherwise leaves Maybe untouched.
     */
    @Override
    public Maybe<T> filter(BooleanCondition<? super T> condition) {
      if (this.content != null && !condition.test(this.content)) {
        return none();
      }
      return this;
    }

    /** 
     * Retrieve the content of this Some instance.
     *
     * @return The content of this Some instance.\
     */
    @Override
    protected T get() {
      return this.content;
    }

    /**
     * Retrieves the string representation for Some.
     *
     * @return The string representation of Some.
     */
    @Override
    public String toString() {
      String contentString = content == null ? "null" : content.toString();
      return "[" + contentString + "]";
    }
    
    /** 
     * Checks whether this Some instance is equals to another object.
     *
     * @param other The other object to be checked with this Some instance.
     * @return true if content of 2 Some instances are equal; false otherwise.
     */
    @Override
    public boolean equals(Object other) {
      if (this == other) {
        return true;
      } 
      if (other instanceof Some) {
        Some<?> otherSome = (Some<?>) other;
        if (otherSome.content == this.content) {
          return true;
        } else if (this.content == null) {
          return false;
        }
        return this.content.equals(otherSome.content);
      }
      return false;
    }
  }

  /**
   * Factory method, which returns a Some if input is not null,
   * otherwise, return None.
   *
   * @param content The content of the Maybe class.
   * @return A Some instance if input is not null; None otherwise.
   */
  public static <T> Maybe<T> of(T content) {
    if (content == null) {
      return none();
    }
    return some(content);
  }
  
  
  /** Factory method, which returns a None instance. */
  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> maybeNone = (Maybe<T>) Maybe.NONE;
    return maybeNone;
  }
  

  /** Factory method, which returns a Some instance.
   *
   * @param content The content of this some instance.
   * @return A Some instance wrapped aroundt the content.
   */
  public static <T> Maybe<T> some(T content) {
    return (Maybe<T>) new Some<>(content);
  }
}
