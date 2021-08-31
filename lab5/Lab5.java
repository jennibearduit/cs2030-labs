import cs2030.fp.Maybe;
import cs2030.fp.Transformer;
import java.util.Map;

/** CS2030 Lab 5: Lab5.java
 * @author Jennifer (B03)
 */
class Lab5 {
  /** Retrieves the grade of a student in a specific assessment of a module. 
   * 
   * @param student The student associated with the grade.
   * @param module The module associated with the grade.
   * @param assessment The assessment associated with the grade.
   * @param map The map storing the relevant information.
   * @return The grade of the student in a specific module's assessment.
   */
  public static String getGrade(String student, String module, String assessment,
      Map<String, Map<String, Map<String, String>>> map) {

    Transformer<Map<String, Map<String, String>>, Maybe<Map<String, String>>> getModule 
        = new Transformer<>() {
            public Maybe<Map<String, String>> transform(Map<String, Map<String, String>> modules) {
            return Maybe.of(modules.get(module));
            }
          };

    Transformer<Map<String, String>, Maybe<String>> getAssessment = new Transformer<>() {
      public Maybe<String> transform(Map<String, String> assessments) {
        return Maybe.of(assessments.get(assessment));
      }
    };

    return Maybe.of(map.get(student)).flatMap(getModule)
        .flatMap(getAssessment).orElse("No such entry"); 
  }

  public static void main(String[] args) {
    Map<String, Map<String, Map<String, String>>> students =
        Map.of(
            "Steve", Map.of(
                "CS2030", Map.of(
                        "lab1", "A",
                        "lab2", "A-",
                        "lab3", "A+",
                        "lab4", "B",
                        "pe1", "C"),
                "CS2040", Map.of(
                        "lab1", "A",
                        "lab2", "A+",
                        "lab3", "A+",
                        "lab4", "A",
                        "midterm", "A+")),
            "Tony", Map.of(
                "CS2030", Map.of(
                    "lab1", "C",
                    "lab2", "C",
                    "lab3", "B-",
                    "lab4", "B+",
                    "pe1", "A")));

    System.out.println(getGrade("Steve", "CS2030", "lab1", students));
    System.out.println(getGrade("Steve", "CS2030", "lab2", students));
    System.out.println(getGrade("Steve", "CS2040", "lab3", students));
    System.out.println(getGrade("Steve", "CS2040", "lab4", students));
    System.out.println(getGrade("Tony", "CS2030", "lab1", students));
    System.out.println(getGrade("Tony", "CS2030", "midterm", students));
    System.out.println(getGrade("Tony", "CS2040", "lab4", students));
    System.out.println(getGrade("Bruce", "CS2040", "lab4", students));
  }
}
