import cs2030.fp.Combiner;
import cs2030.fp.Lazy;
import cs2030.fp.Transformer;
import java.util.ArrayList;
import java.util.List;

/**
 * Test 5 for CS2030 Lab 5 (AY20/21 Special Term 1).  
 * Tests LazyList.
 *
 * @author Ooi Wei Tsang
 */
class Test5 {
  /**
   * Main method for Test5.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030Test i = new CS2030Test();
    List<Integer> numOfEval = new ArrayList<>();
    Transformer<Integer, Integer> incr = x -> {
      numOfEval.add(1);
      return x + 1;
    };
    LazyList<Integer> l = LazyList.generate(4, 0, incr);
    i.expect("An initial LazyList only has a single evaluated element",
        l.toString(), "[0, ?, ?, ?]");
    l.indexOf(2);
    i.expect("Looking for 2 causes two evaluations",
        numOfEval.size(), 2);
    l.indexOf(1);
    i.expect("Looking for 1 does not need any evaluation",
        numOfEval.size(), 2);
    l.get(1);
    i.expect("Retrieving 1 does not need any evaluation",
        numOfEval.size(), 2);
    l.get(3);
    i.expect("Retrieving 3 causes one more evaluation",
        numOfEval.size(), 3);
  }
}
