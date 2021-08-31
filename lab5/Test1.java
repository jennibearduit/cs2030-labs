import cs2030.fp.Maybe;

class Test1 {
  public static void main(String[] args) {
    CS2030Test i = new CS2030Test();

    i.expectCompile("Maybe<Object> m = Maybe.none()",
        "cs2030.fp.Maybe<Object> m = cs2030.fp.Maybe.none()", true);
    i.expect("Maybe.none()",
        Maybe.none().toString(), "[]");
    i.expectCompile("Maybe<Integer> m = Maybe.some(null)",
        "cs2030.fp.Maybe<Integer> m = cs2030.fp.Maybe.some(null)", true);
    i.expect("Maybe.some(null)",
        Maybe.some(null).toString(), "[null]");
    i.expectCompile("Maybe<Integer> m = Maybe.some(4)",
        "cs2030.fp.Maybe<Integer> m = cs2030.fp.Maybe.some(4)", true);
    i.expect("Maybe.some(4)",
        Maybe.some(4).toString(), "[4]");
    i.expect("Maybe.none() == Maybe.none()",
        Maybe.none() == Maybe.none(), true);
    i.expect("Maybe.none().equals(Maybe.none())",
        Maybe.none().equals(Maybe.none()), true);
    i.expect("Maybe.none().equals(Maybe.some(\"day\"))",
        Maybe.none().equals(Maybe.some("day")), false);
    i.expect("Maybe.none().equals(Maybe.some(null))",
        Maybe.none().equals(Maybe.some(null)), false);
    i.expect("Maybe.some(\"day\").equals(Maybe.some(\"day\"))",
        Maybe.some("day").equals(Maybe.some("day")), true);
    i.expect("Maybe.some(null).equals(Maybe.some(\"day\"))",
        Maybe.some(null).equals(Maybe.some("day")), false);
    i.expect("Maybe.some(null).equals(Maybe.some(null))",
        Maybe.some(null).equals(Maybe.some(null)), true);
    i.expect("Maybe.some(null).equals(Maybe.none())",
        Maybe.some(null).equals(Maybe.none()), false);
    i.expect("Maybe.some(null).equals(null)",
        Maybe.some(null).equals(null), false);
    i.expect("Maybe.none().equals(null)",
        Maybe.none().equals(null), false);

    i.expect("Maybe.of(null).equals(Maybe.none())",
        Maybe.of(null).equals(Maybe.none()), true);
    i.expect("Maybe.of(null).equals(Maybe.some(null))",
        Maybe.of(null).equals(Maybe.some(null)), false);
    i.expect("Maybe.of(4).equals(Maybe.none())",
        Maybe.of(4).equals(Maybe.none()), false);
    i.expect("Maybe.of(4).equals(Maybe.some(4))",
        Maybe.of(4).equals(Maybe.some(4)), true);
  }
}
